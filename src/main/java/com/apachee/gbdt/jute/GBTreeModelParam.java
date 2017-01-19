package com.apachee.gbdt.jute;

import com.apachee.gbdt.archive.BinaryInputArchive;

import java.io.IOException;

/**
 * @author chen lu
 * @date 2017/1/10
 * @time 15:23
 */
public class GBTreeModelParam implements Record {
    protected int numTrees;
    protected int numRoots;
    protected int numFeature;
    protected int pad32Bits;
    protected long numPbufferDeprecated;
    protected int numOutputGroup;
    protected int sizeLeadVector;
    protected int[] reserved = new int[32];

    public void deserialize(BinaryInputArchive archive, String tag) throws IOException {
        this.numTrees = archive.readInt("numTrees");
        this.numRoots = archive.readInt("numRoots");
        this.numFeature = archive.readInt("numFeature");
        this.pad32Bits = archive.readInt("pad32Bits");
        this.numPbufferDeprecated = archive.readLong("numPbufferDeprecated");
        this.numOutputGroup = archive.readInt("numOutputGroup");
        this.sizeLeadVector = archive.readInt("sizeLeadVector");
        int length = reserved.length;
        for(int i = 0; i < length; i++) {
            reserved[i] = archive.readInt("reserved");
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("numTrees=").append(numTrees).append(";");
        sb.append("numRoots=").append(numRoots).append(";");
        sb.append("numFeature=").append(numFeature).append(";");
        sb.append("pad32Bits=").append(pad32Bits).append(";");
        sb.append("numPbufferDeprecated=").append(numPbufferDeprecated).append(";");
        sb.append("numOutputGroup=").append(numOutputGroup).append(";");
        sb.append("sizeLeadVector=").append(sizeLeadVector).append(";");
        return sb.toString();
    }

    public int getNumTrees() {
        return numTrees;
    }

    public int getNumRoots() {
        return numRoots;
    }

    public int getNumFeature() {
        return numFeature;
    }

    public int getPad32Bits() {
        return pad32Bits;
    }

    public long getNumPbufferDeprecated() {
        return numPbufferDeprecated;
    }

    public int getNumOutputGroup() {
        return numOutputGroup;
    }

    public int getSizeLeadVector() {
        return sizeLeadVector;
    }

    public int[] getReserved() {
        return reserved;
    }
}
