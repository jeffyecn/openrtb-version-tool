package com.langcode.ovt;

import com.google.openrtb.OpenRtb;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Version24 extends VersionDefination {

    @Override
    public boolean matchRequest(OpenRtb.BidRequestOrBuilder request) {
        if (request.getBappCount() > 0) {
            return true;
        }
        for (OpenRtb.BidRequest.Imp imp : request.getImpList()) {
            if (imp.hasAudio()) {
                return true;
            }
            if (imp.hasClickbrowser()) {
                return true;
            }
            if (imp.hasExp()) {
                return true;
            }
            if (imp.hasBanner()) {
                OpenRtb.BidRequest.Imp.Banner banner = imp.getBanner();
                if (banner.getFormatCount() > 0) {
                    return true;
                }
                if (banner.getBattrCount() > 0) {
                    if (checkCreativeAttributes(banner.getBattrList())) {
                        return true;
                    }
                }
            }
            if (imp.hasVideo()) {
                OpenRtb.BidRequest.Imp.Video video = imp.getVideo();
                if (video.hasSkip()) {
                    return true;
                }
                if (video.hasSkipmin()) {
                    return true;
                }
                if (video.hasSkipafter()) {
                    return true;
                }
                if (video.getBattrCount() > 0) {
                    if (checkCreativeAttributes(video.getBattrList())) {
                        return true;
                    }
                }
                if (video.getProtocolsCount() > 0) {
                    if (checkProtocols(video.getProtocolsList())) {
                        return true;
                    }
                }
            }
            if (imp.hasNative()) {
                OpenRtb.BidRequest.Imp.Native nativeImp = imp.getNative();
                if (nativeImp.getBattrCount() > 0) {
                    if (checkCreativeAttributes(nativeImp.getBattrList())) {
                        return true;
                    }
                }
            }
        }
        if (request.hasSite()) {
            OpenRtb.BidRequest.Site site = request.getSite();
            if (site.hasContent()) {
                if (checkContent(site.getContent())) {
                    return true;
                }
            }
        }
        if (request.hasApp()) {
            OpenRtb.BidRequest.App app = request.getApp();
            if (app.hasContent()) {
                if (checkContent(app.getContent())) {
                    return true;
                }
            }
        }
        if (request.hasDevice()) {
            OpenRtb.BidRequest.Device device = request.getDevice();
            if (device.hasGeofetch()) {
                return true;
            }

            if (device.hasGeo()) {
                if (checkGeo(device.getGeo())) {
                    return true;
                }
            }
        }
        if (request.hasUser()) {
            OpenRtb.BidRequest.User user = request.getUser();

            if (user.hasGeo()) {
                if (checkGeo(user.getGeo())) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean checkContent(OpenRtb.BidRequest.Content content) {
        if (content.hasArtist()) {
            return true;
        }
        if (content.hasGenre()) {
            return true;
        }
        if (content.hasAlbum()) {
            return true;
        }
        if (content.hasIsrc()) {
            return true;
        }
        if (content.hasProdq()) {
            return true;
        }
        return false;
    }

    private boolean downgradeContent(OpenRtb.BidRequest.Content.Builder builder) {
        boolean changed = false;
        if (builder.hasArtist()) {
            builder.clearArtist();
            changed = true;
        }
        if (builder.hasGenre()) {
            builder.clearGenre();
            changed = true;
        }
        if (builder.hasAlbum()) {
            builder.clearAlbum();
            changed = true;
        }
        if (builder.hasIsrc()) {
            builder.clearIsrc();
            changed = true;
        }
        if (builder.hasProdq()) {
            builder.clearProdq();
            changed = true;
        }
        return changed;
    }

    private boolean checkGeo(OpenRtb.BidRequest.Geo geo) {
        if (geo.hasAccuracy()) {
            return true;
        }
        if (geo.hasLastfix()) {
            return true;
        }
        if (geo.hasIpservice()) {
            return true;
        }
        return false;
    }

    private boolean downgradeGeo(OpenRtb.BidRequest.Geo.Builder builder) {
        boolean changed = false;
        if (builder.hasAccuracy()) {
            builder.clearAccuracy();
            changed = true;
        }
        if (builder.hasLastfix()) {
            builder.clearLastfix();
            changed = true;
        }
        if (builder.hasIpservice()) {
            builder.clearIpservice();
            changed = true;
        }
        return changed;
    }

    private boolean checkCreativeAttributes(List<OpenRtb.CreativeAttribute> battrs) {
        for (OpenRtb.CreativeAttribute attr : battrs) {
            if (attr == OpenRtb.CreativeAttribute.FLASH) {
                return true;
            }
        }
        return false;
    }

    private boolean downgradeBannerBattr(OpenRtb.BidRequest.Imp.Banner.Builder builder) {
        boolean changed = false;
        HashSet<OpenRtb.CreativeAttribute> newAttrs = new HashSet<>();
        for (OpenRtb.CreativeAttribute attr : builder.getBattrList()) {
            if (attr == OpenRtb.CreativeAttribute.FLASH) {
                changed = true;
                continue;
            } else {
                newAttrs.add(attr);
            }
        }
        builder.clearBattr();
        if (!newAttrs.isEmpty()) {
            builder.addAllBattr(newAttrs);
        }
        return changed;
    }

    private boolean downgradeVideoBattr(OpenRtb.BidRequest.Imp.Video.Builder builder) {
        boolean changed = false;
        HashSet<OpenRtb.CreativeAttribute> newAttrs = new HashSet<>();
        for (OpenRtb.CreativeAttribute attr : builder.getBattrList()) {
            if (attr == OpenRtb.CreativeAttribute.FLASH) {
                changed = true;
                continue;
            } else {
                newAttrs.add(attr);
            }
        }
        builder.clearBattr();
        if (!newAttrs.isEmpty()) {
            builder.addAllBattr(newAttrs);
        }
        return changed;
    }

    private boolean downgradeNativeBattr(OpenRtb.BidRequest.Imp.Native.Builder builder) {
        boolean changed = false;
        HashSet<OpenRtb.CreativeAttribute> newAttrs = new HashSet<>();
        for (OpenRtb.CreativeAttribute attr : builder.getBattrList()) {
            if (attr == OpenRtb.CreativeAttribute.FLASH) {
                changed = true;
                continue;
            } else {
                newAttrs.add(attr);
            }
        }
        builder.clearBattr();
        if (!newAttrs.isEmpty()) {
            builder.addAllBattr(newAttrs);
        }
        return changed;
    }

    private boolean checkProtocols(List<OpenRtb.Protocol> protocols) {
        for (OpenRtb.Protocol protocol : protocols) {
            if (protocol.getNumber() >= OpenRtb.Protocol.VAST_4_0_VALUE) {
                return true;
            }
        }

        return false;
    }

    private boolean downgradeVideoProtocol(OpenRtb.BidRequest.Imp.Video.Builder builder) {
        boolean changed = false;
        HashSet<OpenRtb.Protocol> newProtocols = new HashSet<>();
        for (OpenRtb.Protocol protocol : builder.getProtocolsList()) {
            switch (protocol) {
                case VAST_4_0:
                    changed = true;
                    newProtocols.add(OpenRtb.Protocol.VAST_3_0);
                    break;
                case VAST_4_0_WRAPPER:
                    changed = true;
                    newProtocols.add(OpenRtb.Protocol.VAST_3_0_WRAPPER);
                    break;
                case DAAST_1_0:
                case DAAST_1_0_WRAPPER:
                    changed = true;
                    break;
                default:
                    newProtocols.add(protocol);
            }
        }
        builder.clearProtocols();
        if ( ! newProtocols.isEmpty() ) {
            builder.addAllProtocols(newProtocols);
        }
        return changed;
    }

    @Override
    public boolean downgradeRequest(OpenRtb.BidRequest.Builder builder) throws DowngradeError {
        boolean changed = false;

        if (builder.getBappCount() > 0) {
            builder.clearBapp();
            changed = true;
        }
        ArrayList<OpenRtb.BidRequest.Imp.Builder> newImpBuilderList = new ArrayList<>();
        for (OpenRtb.BidRequest.Imp.Builder impBuilder : builder.getImpBuilderList()) {
            if (impBuilder.hasAudio()) {
                changed = true;
                continue;
            }
            if (impBuilder.hasClickbrowser()) {
                impBuilder.clearClickbrowser();
                changed = true;
            }
            if (impBuilder.hasExp()) {
                impBuilder.clearExp();
                changed = true;
            }
            if (impBuilder.hasBanner()) {
                OpenRtb.BidRequest.Imp.Banner.Builder bannerBuilder = impBuilder.getBannerBuilder();
                if (bannerBuilder.getFormatCount() > 0) {
                    changed = true;
                    if (!(bannerBuilder.hasW() && bannerBuilder.hasH())) {
                        OpenRtb.BidRequest.Imp.Banner.Format format = bannerBuilder.getFormat(0);
                        if (format.hasW() && format.hasH()) {
                            bannerBuilder.setW(format.getW());
                            bannerBuilder.setH(format.getH());
                        } else {
                            continue;
                        }
                    }
                }
                if (bannerBuilder.getBattrCount() > 0) {
                    if (downgradeBannerBattr(bannerBuilder)) {
                        changed = true;
                    }
                }
            }
            if (impBuilder.hasVideo()) {
                OpenRtb.BidRequest.Imp.Video.Builder videoBuilder = impBuilder.getVideoBuilder();
                if (videoBuilder.hasSkip()) {
                    videoBuilder.clearSkip();
                    changed = true;
                }
                if (videoBuilder.hasSkipmin()) {
                    videoBuilder.clearSkipmin();
                    changed = true;
                }
                if (videoBuilder.hasSkipafter()) {
                    videoBuilder.clearSkipafter();
                    changed = true;
                }
                if (videoBuilder.getBattrCount() > 0) {
                    if (downgradeVideoBattr(videoBuilder)) {
                        changed = true;
                    }
                }
                if (videoBuilder.getProtocolsCount() > 0) {
                    if (downgradeVideoProtocol(videoBuilder)) {
                        if (videoBuilder.getProtocolsCount() == 0) {
                            continue;
                        }
                        changed = true;
                    }
                }
            }
            if (impBuilder.hasNative()) {
                OpenRtb.BidRequest.Imp.Native.Builder nativeBuilder = impBuilder.getNativeBuilder();
                if (nativeBuilder.getBattrCount() > 0) {
                    if (downgradeNativeBattr(nativeBuilder)) {
                        changed = true;
                    }
                }
            }
            newImpBuilderList.add(impBuilder);
        }
        builder.clearImp();
        if (newImpBuilderList.isEmpty()) {
            throw new DowngradeError("Empty imp list after downgrade");
        }
        for (OpenRtb.BidRequest.Imp.Builder impBuilder : newImpBuilderList) {
            builder.addImp(impBuilder);
        }

        if (builder.hasSite()) {
            OpenRtb.BidRequest.Site.Builder siteBuilder = builder.getSiteBuilder();
            if (siteBuilder.hasContent()) {
                if (downgradeContent(siteBuilder.getContentBuilder())) {
                    changed = true;
                }
            }
        }
        if (builder.hasApp()) {
            OpenRtb.BidRequest.App.Builder appBuilder = builder.getAppBuilder();
            if (appBuilder.hasContent()) {
                if (downgradeContent(appBuilder.getContentBuilder())) {
                    changed = true;
                }
            }
        }
        if (builder.hasDevice()) {
            OpenRtb.BidRequest.Device.Builder deviceBuilder = builder.getDeviceBuilder();
            if (deviceBuilder.hasGeofetch()) {
                deviceBuilder.clearGeofetch();
                changed = true;
            }

            if (deviceBuilder.hasGeo()) {
                if (downgradeGeo(deviceBuilder.getGeoBuilder())) {
                    changed = true;
                }
            }
        }
        if (builder.hasUser()) {
            OpenRtb.BidRequest.User.Builder userBuilder = builder.getUserBuilder();

            if (userBuilder.hasGeo()) {
                if (downgradeGeo(userBuilder.getGeoBuilder())) {
                    changed = true;
                }
            }
        }

        return changed;
    }

    @Override
    public boolean matchResponse(OpenRtb.BidResponseOrBuilder response) {
        if ( response.getSeatbidCount() > 0 ) {
            for(OpenRtb.BidResponse.SeatBid seatBid : response.getSeatbidList() ) {
                if ( seatBid.getBidCount() > 0 ) {
                    for (OpenRtb.BidResponse.SeatBid.Bid bid : seatBid.getBidList() ) {
                        if ( bid.hasApi() ) {
                            return true;
                        }
                        if ( bid.hasProtocol() ) {
                            return true;
                        }
                        if ( bid.hasQagmediarating() ) {
                            return true;
                        }
                        if ( bid.hasExp() ) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean downgradeResponse(OpenRtb.BidResponse.Builder builder) throws DowngradeError {
        boolean changed = false;
        if ( builder.getSeatbidCount() > 0 ) {
            for(OpenRtb.BidResponse.SeatBid.Builder seatBidBuilder : builder.getSeatbidBuilderList() ) {
                if ( seatBidBuilder.getBidCount() > 0 ) {
                    for (OpenRtb.BidResponse.SeatBid.Bid.Builder bidBuilder : seatBidBuilder.getBidBuilderList() ) {
                        if ( bidBuilder.hasApi() ) {
                            changed = true;
                            bidBuilder.clearApi();
                        }
                        if ( bidBuilder.hasProtocol() ) {
                            changed = true;
                            bidBuilder.clearProtocol();
                        }
                        if ( bidBuilder.hasQagmediarating() ) {
                            changed = true;
                            bidBuilder.clearQagmediarating();
                        }
                        if ( bidBuilder.hasExp() ) {
                            changed = true;
                            bidBuilder.clearExp();
                        }
                    }
                }
            }
        }
        return changed;
    }
}
