package com.apachee.gbdt.parse;

import com.apachee.gbdt.archive.BinaryInputArchive;
import com.apachee.gbdt.jute.GBTreeModelParam;
import com.apachee.gbdt.jute.TreeNode;
import com.apachee.gbdt.jute.TreeParam;
import com.apachee.gbdt.tree.GBTree;
import com.apachee.gbdt.tree.RegTreeNode;
import com.apachee.gbdt.tree.RegTree;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chen lu
 * @date 2017/1/11
 * @time 21:26
 */
public class ModelParser {

    private double missing;
    public ModelParser(double missing) {
        this.missing = missing;
    }

    public GBTree parse(String filePath) throws IOException {
        File modelFile = new File(filePath);
        if(!modelFile.exists() || !modelFile.isFile()) {
            throw new IOException(filePath + " is not a file path.");
        }

        GBTree gbTree = null;
        RandomAccessFile accessFile = null;
        try{
            accessFile = new RandomAccessFile(modelFile, "r");
            BinaryInputArchive inputArchive
                    = BinaryInputArchive.getArchive(accessFile);
            skipHeader(inputArchive);
            gbTree = buildGBTree(inputArchive);
        } finally {
            if(accessFile != null) accessFile.close();
        }

        return gbTree;
    }

    private void skipHeader(BinaryInputArchive inputArchive)
            throws IOException {
        String binf = inputArchive.readBinf("");
        if(binf.equals("binf")) {
            inputArchive.skipBytes(136);
        } else {
            inputArchive.skipBytes(132);
        }
        long len = inputArchive.readLong("");
        inputArchive.skipBytes((int)len);//此处有判断
        long strLen = inputArchive.readLong("");
        inputArchive.skipBytes((int)strLen);
    }

    private GBTree buildGBTree(BinaryInputArchive inputArchive) throws IOException {
        GBTreeModelParam gbTreeModelParam = new GBTreeModelParam();
        gbTreeModelParam.deserialize(inputArchive, "TreeModel");

        // each for iterator calculate one tree
        List<RegTree> regTrees = new ArrayList<RegTree>(gbTreeModelParam.getNumTrees());
        for(int i = 0; i < gbTreeModelParam.getNumTrees(); i++) {
            TreeParam treeParam = new TreeParam();
            treeParam.deserialize(inputArchive, "TreeParam");

            regTrees.add(buildTree(inputArchive, treeParam, i));
            inputArchive.skipBytes(treeParam.getNumNodes() * 16
                    + treeParam.getSizeLeafVector() * 4);
        }

        GBTree gbTree = new GBTree(regTrees, gbTreeModelParam.getNumTrees(),
                gbTreeModelParam.getNumFeature());
        return gbTree;
    }

    private RegTree buildTree(BinaryInputArchive inputArchive,
                                      TreeParam treeParam,
                              int treeIndex) throws IOException {
        int numNodes = treeParam.getNumNodes();

        Map<Integer, RegTreeNode> rtNodeIndexMap = new HashMap<Integer, RegTreeNode>(numNodes);
        for(int i = 0; i < numNodes; i++) {
            TreeNode node = new TreeNode();
            node.deserialize(inputArchive, "TreeNode");
            RegTreeNode rtNode = new RegTreeNode();
            rtNode.setLeft(node.getCleft());
            rtNode.setRight(node.getCright());
            rtNode.setPointer(node.getIndex() & 0x7fffffff);
            rtNode.setDefaultLeft((node.getIndex() >>> 31) == 1);
            rtNode.setValue(node.getLeafValue());
            rtNode.setIndex(i);
            rtNodeIndexMap.put(i, rtNode);
        }
        RegTree regTree = new RegTree(rtNodeIndexMap, treeIndex, this.missing);
        return regTree;
    }
}
