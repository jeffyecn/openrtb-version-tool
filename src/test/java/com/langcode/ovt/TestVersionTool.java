package com.langcode.ovt;

import com.google.openrtb.OpenRtb;
import com.langcode.tools.RtbLoader;
import org.junit.Assert;
import org.junit.Test;

public class TestVersionTool {

    @Test
    public void testMatchReq() {
        doTestMatchReq("req23.json", OpenRtbApiVersion.VER_2_3);
        doTestMatchReq("req24.json", OpenRtbApiVersion.VER_2_4);
        doTestMatchReq("req25.json", OpenRtbApiVersion.VER_2_5);
    }

    private void doTestMatchReq(String name, OpenRtbApiVersion expect) {
        RtbLoader loader = new RtbLoader(name);

        OpenRtb.BidRequest req = loader.loadAsRequest();

        VersionTool tool = new VersionTool();

        Assert.assertSame(tool.detectMinimumVersionOfRequest(req), expect);
    }

    @Test
    public void testDowngradeReq() throws DowngradeError {
        doTestDowngradeReq("req25.json", OpenRtbApiVersion.VER_2_5);
        doTestDowngradeReq("req25.json", OpenRtbApiVersion.VER_2_4);
        doTestDowngradeReq("req25.json", OpenRtbApiVersion.VER_2_3);
        doTestDowngradeReq("req24.json", OpenRtbApiVersion.VER_2_5);
        doTestDowngradeReq("req24.json", OpenRtbApiVersion.VER_2_4);
        doTestDowngradeReq("req24.json", OpenRtbApiVersion.VER_2_3);
        doTestDowngradeReq("req23.json", OpenRtbApiVersion.VER_2_5);
        doTestDowngradeReq("req23.json", OpenRtbApiVersion.VER_2_4);
        doTestDowngradeReq("req23.json", OpenRtbApiVersion.VER_2_3);
    }

    private void doTestDowngradeReq(String name, OpenRtbApiVersion expect) throws DowngradeError {
        RtbLoader loader = new RtbLoader(name);

        OpenRtb.BidRequest req = loader.loadAsRequest();

        OpenRtb.BidRequest.Builder builder = OpenRtb.BidRequest.newBuilder(req);

        VersionTool tool = new VersionTool();

        tool.ensureRequestCompatible(builder, expect);

        Assert.assertFalse(tool.detectMinimumVersionOfRequest(builder).isHigherThan(expect));
    }
}
