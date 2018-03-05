package com.langcode.ovt;

import com.google.openrtb.OpenRtb;

public class Version24 extends VersionDefination {

    @Override
    public boolean matchRequest(OpenRtb.BidRequestOrBuilder request) {
        return false;
    }

    @Override
    public boolean downgradeRequest(OpenRtb.BidRequest.Builder requestBuilder) {
        return false;
    }

    @Override
    public boolean matchResponse(OpenRtb.BidResponseOrBuilder response) {
        return false;
    }

    @Override
    public boolean downgradeResponse(OpenRtb.BidResponse.Builder responseBuilder) {
        return false;
    }
}
