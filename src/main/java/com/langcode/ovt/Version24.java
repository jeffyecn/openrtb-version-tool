package com.langcode.ovt;

import com.google.openrtb.OpenRtb;

public class Version24 extends VersionDefination {

    @Override
    boolean matchRequest(OpenRtb.BidRequestOrBuilder request) {
        return false;
    }

    @Override
    boolean downgradeRequest(OpenRtb.BidRequest.Builder requestBuilder) {
        return false;
    }

    @Override
    boolean matchResponse(OpenRtb.BidResponseOrBuilder response) {
        return false;
    }

    @Override
    boolean downgradeResponse(OpenRtb.BidResponse.Builder responseBuilder) {
        return false;
    }
}
