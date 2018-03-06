package com.langcode.ovt;

import com.google.openrtb.OpenRtb;
import com.langcode.tools.RtbLoader;
import org.junit.Assert;
import org.junit.Test;

public class TestVersion25 {

    @Test
    public void testMatchReq() {
        RtbLoader loader = new RtbLoader("req25.json");

        OpenRtb.BidRequest request = loader.loadAsRequest();

        Version25 version25 = new Version25();

        Assert.assertTrue(version25.matchRequest(request));
    }

    @Test
    public void testNoMatchReq() {
        RtbLoader loader = new RtbLoader("req24.json");

        OpenRtb.BidRequest request = loader.loadAsRequest();

        Version25 version25 = new Version25();

        Assert.assertFalse(version25.matchRequest(request));
    }

    @Test
    public void testDowngradeReq() throws DowngradeError {
        RtbLoader loader = new RtbLoader("req25.json");

        OpenRtb.BidRequest request = loader.loadAsRequest();

        OpenRtb.BidRequest.Builder builder = OpenRtb.BidRequest.newBuilder(request);

        Version25 version25 = new Version25();

        Assert.assertTrue(version25.downgradeRequest(builder));

        Assert.assertFalse(version25.matchRequest(builder));
    }

    @Test
    public void testMatchResp() {
        RtbLoader loader = new RtbLoader("resp25.json");

        OpenRtb.BidResponse response = loader.loadAsResponse();

        Version25 version25 = new Version25();

        Assert.assertTrue(version25.matchResponse(response));
    }

    @Test
    public void testNoMatchResp() {
        RtbLoader loader = new RtbLoader("resp24.json");

        OpenRtb.BidResponse response = loader.loadAsResponse();

        Version25 version25 = new Version25();

        Assert.assertFalse(version25.matchResponse(response));
    }

    @Test
    public void testDowngradeResp() throws DowngradeError {
        RtbLoader loader = new RtbLoader("resp25.json");

        OpenRtb.BidResponse response = loader.loadAsResponse();

        OpenRtb.BidResponse.Builder builder = OpenRtb.BidResponse.newBuilder(response);

        Version25 version25 = new Version25();

        Assert.assertTrue(version25.downgradeResponse(builder));

        Assert.assertFalse(version25.matchResponse(builder));
    }
}
