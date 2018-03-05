package com.langcode.ovt;

import com.google.openrtb.OpenRtb;

import java.util.HashSet;
import java.util.List;

public class Version25 extends VersionDefination {

    @Override
    public boolean matchRequest(OpenRtb.BidRequestOrBuilder request) {
        if (request.getBseatCount() > 0) {
            return true;
        }
        if (request.getWlangCount() > 0) {
            return true;
        }
        if (request.hasSource()) {
            return true;
        }
        for (OpenRtb.BidRequest.Imp imp : request.getImpList()) {
            if (imp.getMetricCount() > 0) {
                return true;
            }
            if (imp.hasBanner()) {
                OpenRtb.BidRequest.Imp.Banner banner = imp.getBanner();
                if (banner.hasVcm()) {
                    return true;
                }
                if (checkApi(banner.getApiList())) {
                    return true;
                }
            }
            if (imp.hasVideo()) {
                OpenRtb.BidRequest.Imp.Video video = imp.getVideo();
                if (video.hasPlacement()) {
                    return true;
                }
                if (video.hasPlaybackend()) {
                    return true;
                }
                if (checkApi(video.getApiList())) {
                    return true;
                }
                if (checkPlaybackMethod(video.getPlaybackmethodList())) {
                    return true;
                }
            }
            if (imp.hasAudio()) {
                OpenRtb.BidRequest.Imp.Audio audio = imp.getAudio();
                if (checkApi(audio.getApiList())) {
                    return true;
                }
            }
            if (imp.hasNative()) {
                OpenRtb.BidRequest.Imp.Native nativeImp = imp.getNative();
                if (checkApi(nativeImp.getApiList())) {
                    return true;
                }
            }
        }
        if (request.hasDevice()) {
            OpenRtb.BidRequest.Device device = request.getDevice();
            if (device.hasMccmnc()) {
                return true;
            }
        }
        return false;
    }

    private boolean checkApi(List<OpenRtb.APIFramework> apis) {
        for (OpenRtb.APIFramework api : apis) {
            if (api == OpenRtb.APIFramework.MRAID_3) {
                return true;
            }
        }
        return false;
    }

    private boolean downgradeBannerApi(OpenRtb.BidRequest.Imp.Banner.Builder bannerBuilder) {
        boolean changed = false;
        if (bannerBuilder.getApiCount() > 0) {
            HashSet<OpenRtb.APIFramework> values = new HashSet<>();
            for (OpenRtb.APIFramework api : bannerBuilder.getApiList()) {
                if (api == OpenRtb.APIFramework.MRAID_3) {
                    changed = true;
                } else {
                    values.add(api);
                }
            }
            bannerBuilder.clearApi();
            if (!values.isEmpty()) {
                bannerBuilder.addAllApi(values);
            }
        }
        return changed;
    }

    private boolean downgradeVideoApi(OpenRtb.BidRequest.Imp.Video.Builder videoBuilder) {
        boolean changed = false;
        if (videoBuilder.getApiCount() > 0) {
            HashSet<OpenRtb.APIFramework> values = new HashSet<>();
            for (OpenRtb.APIFramework api : videoBuilder.getApiList()) {
                if (api == OpenRtb.APIFramework.MRAID_3) {
                    changed = true;
                } else {
                    values.add(api);
                }
            }
            videoBuilder.clearApi();
            if (!values.isEmpty()) {
                videoBuilder.addAllApi(values);
            }
        }
        return changed;
    }

    private boolean downgradeAudioApi(OpenRtb.BidRequest.Imp.Audio.Builder audioBuilder) {
        boolean changed = false;
        if (audioBuilder.getApiCount() > 0) {
            HashSet<OpenRtb.APIFramework> values = new HashSet<>();
            for (OpenRtb.APIFramework api : audioBuilder.getApiList()) {
                if (api == OpenRtb.APIFramework.MRAID_3) {
                    changed = true;
                } else {
                    values.add(api);
                }
            }
            audioBuilder.clearApi();
            if (!values.isEmpty()) {
                audioBuilder.addAllApi(values);
            }
        }
        return changed;
    }

    private boolean downgradeNativeApi(OpenRtb.BidRequest.Imp.Native.Builder nativeBuilder) {
        boolean changed = false;
        if (nativeBuilder.getApiCount() > 0) {
            HashSet<OpenRtb.APIFramework> values = new HashSet<>();
            for (OpenRtb.APIFramework api : nativeBuilder.getApiList()) {
                if (api == OpenRtb.APIFramework.MRAID_3) {
                    changed = true;
                } else {
                    values.add(api);
                }
            }
            nativeBuilder.clearApi();
            if (!values.isEmpty()) {
                nativeBuilder.addAllApi(values);
            }
        }
        return changed;
    }

    private boolean checkPlaybackMethod(List<OpenRtb.PlaybackMethod> methods) {
        for (OpenRtb.PlaybackMethod method : methods) {
            if (method.getNumber() >= OpenRtb.PlaybackMethod.ENTER_SOUND_ON.getNumber()) {
                return true;
            }
        }
        return false;
    }

    private boolean downgradePlaybackMethod(OpenRtb.BidRequest.Imp.Video.Builder videoBuilder) {
        boolean changed = false;
        if (videoBuilder.getPlaybackmethodCount() > 0) {
            HashSet<OpenRtb.PlaybackMethod> values = new HashSet<>();
            for (OpenRtb.PlaybackMethod method : videoBuilder.getPlaybackmethodList()) {
                switch (method) {
                    case ENTER_SOUND_ON:
                        changed = true;
                        break;
                    case ENTER_SOUND_OFF:
                        changed = true;
                        break;
                    default:
                        values.add(method);
                }
            }
            videoBuilder.clearPlaybackmethod();
            if ( ! values.isEmpty() ) {
                videoBuilder.addAllPlaybackmethod(values);
            }
        }
        return changed;
    }

    @Override
    public boolean downgradeRequest(OpenRtb.BidRequest.Builder builder) {
        boolean changed = false;
        if (builder.getBseatCount() > 0) {
            builder.clearBseat();
            changed = true;
        }
        if (builder.getWlangCount() > 0) {
            builder.clearWlang();
            changed = true;
        }
        if (builder.hasSource()) {
            builder.clearSource();
            changed = true;
        }
        for (OpenRtb.BidRequest.Imp.Builder impBuilder : builder.getImpBuilderList()) {
            if (impBuilder.getMetricCount() > 0) {
                impBuilder.clearMetric();
                changed = true;
            }
            if (impBuilder.hasBanner()) {
                OpenRtb.BidRequest.Imp.Banner.Builder bannerBuilder = impBuilder.getBannerBuilder();
                if (bannerBuilder.hasVcm()) {
                    bannerBuilder.clearVcm();
                    changed = true;
                }
                if (downgradeBannerApi(bannerBuilder)) {
                    changed = true;
                }
            }
            if (impBuilder.hasVideo()) {
                OpenRtb.BidRequest.Imp.Video.Builder videoBuilder = impBuilder.getVideoBuilder();
                if (videoBuilder.hasPlacement()) {
                    videoBuilder.clearPlacement();
                    changed = true;
                }
                if (videoBuilder.hasPlaybackend()) {
                    videoBuilder.clearPlaybackend();
                    changed = true;
                }
                if (downgradeVideoApi(videoBuilder)) {
                    changed = true;
                }
                if (downgradePlaybackMethod(videoBuilder)) {
                    changed = true;
                }
            }
            if (impBuilder.hasAudio()) {
                OpenRtb.BidRequest.Imp.Audio.Builder audioBuilder = impBuilder.getAudioBuilder();
                if (downgradeAudioApi(audioBuilder)) {
                    changed = true;
                }
            }
            if (impBuilder.hasNative()) {
                OpenRtb.BidRequest.Imp.Native.Builder nativeBuilder = impBuilder.getNativeBuilder();
                if (downgradeNativeApi(nativeBuilder)) {
                    changed = true;
                }
            }
        }
        if (builder.hasDevice()) {
            OpenRtb.BidRequest.Device.Builder deviceBuilder = builder.getDeviceBuilder();
            if (deviceBuilder.hasMccmnc()) {
                deviceBuilder.clearMccmnc();
                changed = true;
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
                        if ( bid.hasBurl() ) {
                            return true;
                        }
                        if ( bid.hasLurl() ) {
                            return true;
                        }
                        if ( bid.hasTactic() ) {
                            return true;
                        }
                        if ( bid.hasWratio() ) {
                            return true;
                        }
                        if ( bid.hasHratio() ) {
                            return true;
                        }
                        if ( bid.hasApi() ) {
                            if ( bid.getApi() == OpenRtb.APIFramework.MRAID_3 ) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        if ( response.hasNbr() ) {
            switch(response.getNbr()) {
                case DAILY_READER_CAP:
                    return true;
                case DAILY_DOMAIN_CAP:
                    return true;
            }
        }

        return false;
    }

    @Override
    public boolean downgradeResponse(OpenRtb.BidResponse.Builder builder) {
        boolean changed = false;
        if ( builder.getSeatbidCount() > 0 ) {
            for(OpenRtb.BidResponse.SeatBid.Builder seatBidBuilder : builder.getSeatbidBuilderList() ) {
                if ( seatBidBuilder.getBidCount() > 0 ) {
                    for (OpenRtb.BidResponse.SeatBid.Bid.Builder bidBuilder : seatBidBuilder.getBidBuilderList() ) {
                        if ( bidBuilder.hasBurl() ) {
                            bidBuilder.clearBurl();
                            changed =  true;
                        }
                        if ( bidBuilder.hasLurl() ) {
                            bidBuilder.clearLurl();
                            changed = true;
                        }
                        if ( bidBuilder.hasTactic() ) {
                            bidBuilder.clearTactic();
                            changed = true;
                        }
                        if ( bidBuilder.hasWratio() ) {
                            bidBuilder.clearWratio();
                            changed = true;
                        }
                        if ( bidBuilder.hasHratio() ) {
                            bidBuilder.clearHratio();
                            changed = true;
                        }
                        if ( bidBuilder.hasApi() ) {
                            if ( bidBuilder.getApi() == OpenRtb.APIFramework.MRAID_3 ) {
                                bidBuilder.setApi(OpenRtb.APIFramework.MRAID_2);
                                changed = true;
                            }
                        }
                    }
                }
            }
        }
        if ( builder.hasNbr() ) {
            switch(builder.getNbr()) {
                case DAILY_READER_CAP:
                    builder.clearNbr();
                    changed = true;
                    break;
                case DAILY_DOMAIN_CAP:
                    builder.clearNbr();
                    changed = true;
                    break;
            }
        }

        return changed;
    }
}
