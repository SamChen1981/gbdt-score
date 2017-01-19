package com.apachee.gbdt.tree;

import java.util.Map;

/**
 * @author chen lu
 * @date 2017/1/10
 * @time 14:18
 */
public class RegTree {

    private Map<Integer, RegTreeNode> rtNodeIndexMap;

    private int treeIndex = 0;

    private double missing;

    public RegTree(Map<Integer, RegTreeNode> rtNodeIndexMap,
                   int treeIndex, double missing) {
        this.rtNodeIndexMap = rtNodeIndexMap;
        this.treeIndex = treeIndex;
        this.missing = missing;
    }

    public double predict(double[] features) {
        return this.predict(features, rtNodeIndexMap.get(0));
    }

    public double predict(double[] features, RegTreeNode rtNode) {
        if(rtNode.getLeft() == -1 && rtNode.getRight() == -1) {
            return rtNode.getValue();
        }
        double feature = features[rtNode.getPointer()];
        if(feature == missing) {
            return predict(features, rtNodeIndexMap.get(rtNode.getDefaultNode()));
        }
        if(feature < rtNode.getValue()) {
            return predict(features, rtNodeIndexMap.get(rtNode.getLeft()));
        } else {
            return predict(features, rtNodeIndexMap.get(rtNode.getRight()));
        }
    }

    public int getTreeIndex() {
        return treeIndex;
    }
}
