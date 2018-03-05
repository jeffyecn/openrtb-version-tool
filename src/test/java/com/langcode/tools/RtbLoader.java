package com.langcode.tools;

import com.google.openrtb.OpenRtb;
import com.google.openrtb.json.OpenRtbJsonFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class RtbLoader {

    private final File file;

    public RtbLoader(String name) {
        ClassLoader classLoader = getClass().getClassLoader();
        file = new File(classLoader.getResource(name).getFile());
        if ( ! file.exists() ) {
            System.err.println("Resource " + file.getAbsolutePath() + " not exists");
        }
    }

    public OpenRtb.BidRequest loadAsRequest() {
        try {
            return OpenRtbJsonFactory.create().newReader().readBidRequest(new FileInputStream(file));
        } catch ( FileNotFoundException ex ) {
            System.err.println("resource file not exists");
            ex.printStackTrace(System.err);
        } catch ( IOException ex ) {
            System.err.println("can not read resouce");
            ex.printStackTrace(System.err);
        }
        return null;
    }

    public OpenRtb.BidResponse loadAsResponse() {
        try {
            return OpenRtbJsonFactory.create().newReader().readBidResponse(new FileInputStream(file));
        } catch ( FileNotFoundException ex ) {
            System.err.println("resource file not exists");
            ex.printStackTrace(System.err);
        } catch ( IOException ex ) {
            System.err.println("can not read resouce");
            ex.printStackTrace(System.err);
        }
        return null;
    }
}
