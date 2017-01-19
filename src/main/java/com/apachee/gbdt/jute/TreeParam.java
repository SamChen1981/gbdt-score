package com.apachee.gbdt.jute;

import com.apachee.gbdt.archive.BinaryInputArchive;

import java.io.IOException;

/**
 * @author chen lu
 * @date 2017/1/10
 * @time 14:14
 */
public class TreeParam{
    private int numRoots;
    private int numNodes;
    private int numDeleted;
    private int maxDepth;
    private int numFeature;
    private int sizeLeafVector;
    private int[] reserved = new int[31];

    public void deserialize(BinaryInputArchive archive, String tag) throws IOException {
        this.numRoots = archive.readInt("numRoots");
        this.numNodes = archive.readInt("numNodes");
        this.numDeleted = archive.readInt("numDeleted");
        this.maxDepth = archive.readInt("maxDepth");
        this.numFeature = archive.readInt("numFeature");
        this.sizeLeafVector = archive.readInt("sizeLeafVector");
        int length = reserved.length;
        for(int i = 0; i < length; i++) {
            reserved[i] = archive.readInt("reserved");
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("numRoots=").append(numRoots).append(";");
        sb.append("numNodes=").append(numNodes).append(";");
        sb.append("numDeleted=").append(numDeleted).append(";");
        sb.append("maxDepth=").append(maxDepth).append(";");
        sb.append("numFeature=").append(numFeature).append(";");
        sb.append("sizeLeafVector=").append(sizeLeafVector).append(";");
        return sb.toString();
    }

    public int getNumRoots() {
        return numRoots;
    }

    public int getNumNodes() {
        return numNodes;
    }

    public int getNumDeleted() {
        return numDeleted;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public int getNumFeature() {
        return numFeature;
    }

    public int getSizeLeafVector() {
        return sizeLeafVector;
    }

    public int[] getReserved() {
        return reserved;
    }
}
