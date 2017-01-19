package com.apachee.gbdt.archive;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class BinaryInputArchive {
    private RandomAccessFile in;
    
    static public BinaryInputArchive getArchive(RandomAccessFile file) {
        return new BinaryInputArchive(file);
    }

    /** Creates a new instance of BinaryInputArchive */
    public BinaryInputArchive(RandomAccessFile file) {
        this.in = file;
    }

    public void skipBytes(int num) throws IOException {
        this.in.skipBytes(num);
    }
    
    public int readInt(String tag) throws IOException {
        int ch4 = this.in.read();
        int ch3 = this.in.read();
        int ch2 = this.in.read();
        int ch1 = this.in.read();
        if ((ch1 | ch2 | ch3 | ch4) < 0) {
            throw new EOFException();
        }

        return ((ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0));
    }

    public long readLong(String tag) throws IOException {
        return ((readInt(tag) & 0xFFFFFFFFL) + ((long)(readInt(tag)) << 32));
    }

    public float readFloat(String tag) throws IOException {
        return Float.intBitsToFloat(readInt(tag));
    }

    public long readUnsingnedInt(String tag) throws IOException {
        return readInt(tag) & 0xFFFFFFFFL;
    }

    public static void main(String[] args) {
        System.out.println(0xFFFFFFF0L);
    }

    public String readBinf(String tag) throws IOException {
        StringBuilder binf = new StringBuilder();
        for(int i = 0; i < 4; i++) {
            byte b = in.readByte();
            binf.append(String.valueOf((char) b));
        }
        return binf.toString();
    }
}