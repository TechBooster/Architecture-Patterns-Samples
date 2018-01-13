package com.chucknorrisviewer.nativeModule;


import android.support.annotation.Nullable;

import com.facebook.react.bridge.ReadableMap;

public class ReactEvent {
    public final String eventName;
    public final ReadableMap data;

    ReactEvent(String eventName, @Nullable ReadableMap data) {
        this.eventName = eventName;
        this.data = data;
    }
}
