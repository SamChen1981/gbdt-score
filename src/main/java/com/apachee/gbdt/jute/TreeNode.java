package com.apachee.gbdt.jute;

import com.apachee.gbdt.archive.BinaryInputArchive;

import java.io.IOException;

/**
 * @author chen lu
 * @date 2017/1/11
 * @time 16:48
 */
public class TreeNode {
    private int parent;
    private int cleft;
    private int cright;
    private int index;
    private double leafValue;

    public void deserialize(BinaryInputArchive archive, String tag) throws IOException {
        this.parent = archive.readInt("numRoots") & ((1 << 31) - 1) ;
        this.cleft = archive.readInt("numNodes");
        this.cright = archive.readInt("numDeleted");
        this.index = archive.readInt("maxDepth");
        this.leafValue = (double)archive.readFloat("numFeature");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("parent:").append(parent).append(";");
        sb.append("cleft:").append(cleft).append(";");
        sb.append("cright:").append(cright).append(";");
        sb.append("index:").append(index).append(";");
        sb.append("leafValue:").append(leafValue).append(";");
        return sb.toString();
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getCleft() {
        return cleft;
    }

    public void setCleft(int cleft) {
        this.cleft = cleft;
    }

    public int getCright() {
        return cright;
    }

    public void setCright(int cright) {
        this.cright = cright;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getLeafValue() {
        return leafValue;
    }

    public void setLeafValue(double leafValue) {
        this.leafValue = (double)leafValue;
    }
}
