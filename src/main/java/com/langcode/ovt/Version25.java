package com.langcode.ovt;

import com.google.openrtb.OpenRtb;

public class Version25 extends VersionDefination {

    @Override
    boolean matchRequest(OpenRtb.BidRequestOrBuilder request) {
        if ( request.getBseatCount() > 0 ) {
            return true;
        }
        if ( request.getWlangCount() > 0 ) {
            return true;
        }
        if ( request.hasSource() ) {
            return true;
        }
        for(OpenRtb.BidRequest.Imp imp : request.getImpList() ) {
            if ( imp.getMetricCount() > 0 ) {
                return true;
            }
            if ( imp.hasBanner() ) {
                OpenRtb.BidRequest.Imp.Banner banner = imp.getBanner();
                if ( banner.hasVcm() ) {
                    return true;
                }
            }
            if ( imp.hasVideo() ) {
                OpenRtb.BidRequest.Imp.Video video = imp.getVideo();
                if ( video.hasPlacement() ) {
                    return true;
                }
                if ( video.hasPlaybackend() ) {
                    return true;
                }
            }
        }
        if ( request.hasDevice() ) {
            OpenRtb.BidRequest.Device device = request.getDevice();
            if ( device.hasMccmnc() ) {
                return true;
            }
        }
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
