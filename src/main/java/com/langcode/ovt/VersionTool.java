package com.langcode.ovt;

import com.google.openrtb.OpenRtb;

public class VersionTool {

    private final static VersionDefination version24 = new Version24();

    private final static VersionDefination version25 = new Version25();

    private VersionDefination getVersionDefination(OpenRtbApiVersion ver) {
        switch (ver) {
            case VER_2_5:
                return version25;
            case VER_2_4:
                return version24;
            default:
                return null;
        }
    }

    public OpenRtbApiVersion detectMinimumVersionOfRequest(OpenRtb.BidRequestOrBuilder request) {
        if ( version25.matchRequest(request) ) {
            return OpenRtbApiVersion.VER_2_5;
        }
        if ( version24.matchRequest(request) ) {
            return OpenRtbApiVersion.VER_2_4;
        }
        return OpenRtbApiVersion.VER_2_3;
    }

    public OpenRtbApiVersion detectMinimumVersionOfReply(OpenRtb.BidResponseOrBuilder response) {
        if ( version25.matchResponse(response) ) {
            return OpenRtbApiVersion.VER_2_5;
        }
        if ( version24.matchResponse(response) ) {
            return OpenRtbApiVersion.VER_2_4;
        }
        return OpenRtbApiVersion.VER_2_3;
    }

    public boolean ensureRequestCompatible(OpenRtb.BidRequest.Builder request, OpenRtbApiVersion targetVersion) {
        return ensureRequestCompatible(request, targetVersion, OpenRtbApiVersion.VER_2_5);
    }

    public boolean ensureRequestCompatible(OpenRtb.BidRequest.Builder request, OpenRtbApiVersion targetVersion, OpenRtbApiVersion currentVersion) {
        if ( targetVersion.isLowerThan(currentVersion) ) {
            boolean changed = getVersionDefination(currentVersion).downgradeRequest(request);
            OpenRtbApiVersion nextVersion = currentVersion.nextLowerVersion();
            return ensureRequestCompatible(request, targetVersion, nextVersion) || changed;
        }
        return false;
    }

    public boolean ensureResponseCompatile(OpenRtb.BidResponse.Builder response, OpenRtbApiVersion targetVersion) {
        return ensureResponseCompatile(response, targetVersion, OpenRtbApiVersion.VER_2_5);
    }

    public boolean ensureResponseCompatile(OpenRtb.BidResponse.Builder response, OpenRtbApiVersion targetVersion, OpenRtbApiVersion currentVersion) {
        if ( targetVersion.isLowerThan(currentVersion) ) {
            boolean changed = getVersionDefination(currentVersion).downgradeResponse(response);
            OpenRtbApiVersion nextVersion = currentVersion.nextLowerVersion();
            return ensureResponseCompatile(response, targetVersion, nextVersion) || changed;
        }
        return false;
    }
}
