package com.langcode.ovt;

import com.google.openrtb.OpenRtb;
import com.langcode.tools.RtbLoader;
import org.junit.Assert;
import org.junit.Test;

public class TestVersion24 {

    @Test
    public void testMatchReq() {
        RtbLoader loader = new RtbLoader("req24.json");

        OpenRtb.BidRequest request = loader.loadAsRequest();

        Version24 version24 = new Version24();

        Assert.assertTrue(version24.matchRequest(request));
    }

    @Test
    public void testNoMatchReq() {
        RtbLoader loader = new RtbLoader("req23.json");

        OpenRtb.BidRequest request = loader.loadAsRequest();

        Version24 version24 = new Version24();

        Assert.assertFalse(version24.matchRequest(request));
    }

    @Test
    public void testDowngradeReq() throws DowngradeError {
        RtbLoader loader = new RtbLoader("req24.json");

        OpenRtb.BidRequest request = loader.loadAsRequest();

        OpenRtb.BidRequest.Builder builder = OpenRtb.BidRequest.newBuilder(request);

        Version24 version24 = new Version24();

        Assert.assertTrue(version24.downgradeRequest(builder));

        Assert.assertFalse(version24.matchRequest(builder));
    }

    @Test
    public void testMatchResp() {
        RtbLoader loader = new RtbLoader("resp24.json");

        OpenRtb.BidResponse response = loader.loadAsResponse();

        Version24 version24 = new Version24();

        Assert.assertTrue(version24.matchResponse(response));
    }

    @Test
    public void testNoMatchResp() {
        RtbLoader loader = new RtbLoader("resp23.json");

        OpenRtb.BidResponse response = loader.loadAsResponse();

        Version24 version24 = new Version24();

        Assert.assertFalse(version24.matchResponse(response));
    }

    @Test
    public void testDowngradeResp() throws DowngradeError {
        RtbLoader loader = new RtbLoader("resp24.json");

        OpenRtb.BidResponse response = loader.loadAsResponse();

        OpenRtb.BidResponse.Builder builder = OpenRtb.BidResponse.newBuilder(response);

        Version24 version24 = new Version24();

        Assert.assertTrue(version24.downgradeResponse(builder));

        Assert.assertFalse(version24.matchResponse(builder));
    }


}
