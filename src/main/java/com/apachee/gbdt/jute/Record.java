package com.apachee.gbdt.jute;

import com.apachee.gbdt.archive.BinaryInputArchive;

import java.io.IOException;

public interface Record {
    public void deserialize(BinaryInputArchive archive, String tag)
        throws IOException;
}