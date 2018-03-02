package com.langcode.ovt;

import com.google.openrtb.OpenRtb;

abstract public class VersionDefination {

    abstract boolean matchRequest(OpenRtb.BidRequestOrBuilder request);

    abstract boolean downgradeRequest(OpenRtb.BidRequest.Builder requestBuilder);

    abstract boolean matchResponse(OpenRtb.BidResponseOrBuilder response);

    abstract boolean downgradeResponse(OpenRtb.BidResponse.Builder responseBuilder);
}
