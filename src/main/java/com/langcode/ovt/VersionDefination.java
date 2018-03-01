package com.langcode.ovt;

import com.google.openrtb.OpenRtb;

abstract public class VersionDefination {

    abstract boolean matchRequest(OpenRtb.BidRequestOrBuilder request);

    abstract OpenRtb.BidRequest.Builder downgradeRequest(OpenRtb.BidRequest.Builder requestBuilder);

    abstract boolean matchResponse(OpenRtb.BidResponseOrBuilder response);

    abstract OpenRtb.BidResponse.Builder downgradeResponse(OpenRtb.BidResponse.Builder responseBuilder);
}
