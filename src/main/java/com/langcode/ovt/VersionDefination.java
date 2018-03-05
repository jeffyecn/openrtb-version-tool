package com.langcode.ovt;

import com.google.openrtb.OpenRtb;

abstract public class VersionDefination {

    abstract public boolean matchRequest(OpenRtb.BidRequestOrBuilder request);

    abstract public boolean downgradeRequest(OpenRtb.BidRequest.Builder requestBuilder) throws DowngradeError;

    abstract public boolean matchResponse(OpenRtb.BidResponseOrBuilder response);

    abstract public boolean downgradeResponse(OpenRtb.BidResponse.Builder responseBuilder) throws  DowngradeError;
}
