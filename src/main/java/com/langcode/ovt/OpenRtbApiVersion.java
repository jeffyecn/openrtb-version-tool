package com.langcode.ovt;

public enum OpenRtbApiVersion {

    VER_2_5(25),
    VER_2_4(24),
    VER_2_3(23);

    private final int value;

    OpenRtbApiVersion(int value) {
        this.value = value;
    }
}
