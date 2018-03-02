package com.langcode.ovt;

public enum OpenRtbApiVersion {

    VER_2_5(25),
    VER_2_4(24),
    VER_2_3(23);

    private final int value;

    OpenRtbApiVersion(int value) {
        this.value = value;
    }

    public boolean isLowerThan(OpenRtbApiVersion other) {
        return value < other.value;
    }

    public boolean isHigherThan(OpenRtbApiVersion other) {
        return value > other.value;
    }

    public OpenRtbApiVersion nextLowerVersion() {
        switch(value) {
            case 25:
                return VER_2_4;
            case 24:
                return VER_2_3;
            default:
                return null;
        }
    }
}
