package com.langcode.ovt;

import com.google.openrtb.OpenRtb;

public class Version24 extends VersionDefination {

    @Override
    boolean matchRequest(OpenRtb.BidRequestOrBuilder request) {
        return false;
    }

    @Override
    OpenRtb.BidRequest.Builder downgradeRequest(OpenRtb.BidRequest.Builder requestBuilder) {
        return null;
    }

    @Override
    boolean matchResponse(OpenRtb.BidResponseOrBuilder response) {
        return false;
    }

    @Override
    OpenRtb.BidResponse.Builder downgradeResponse(OpenRtb.BidResponse.Builder responseBuilder) {
        return null;
    }
}
