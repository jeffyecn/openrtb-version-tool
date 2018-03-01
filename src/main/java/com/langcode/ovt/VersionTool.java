package com.langcode.ovt;

import com.google.openrtb.OpenRtb;

public class VersionTool {

    private final static VersionDefination version24 = new Version24();

    private final static VersionDefination version25 = new Version25();

    private OpenRtbApiVersion nextLowerVersion(OpenRtbApiVersion ver) {
        switch (ver) {
            case VER_2_5:
                return OpenRtbApiVersion.VER_2_4;
            case VER_2_4:
                return OpenRtbApiVersion.VER_2_3;
            default:
                return null;
        }
    }

    public OpenRtbApiVersion detectMinimumVersionOfRequest(OpenRtb.BidRequestOrBuilder request) {
        if ( version25.matchRequest(request) ) {
            return OpenRtbApiVersion.VER_2_5;
        }
    }
}
