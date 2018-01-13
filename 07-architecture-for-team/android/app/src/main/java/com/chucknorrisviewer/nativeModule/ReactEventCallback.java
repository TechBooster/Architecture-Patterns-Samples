package com.chucknorrisviewer.nativeModule;


import android.support.annotation.Nullable;

import com.facebook.react.bridge.ReadableMap;

/** Use EventProcessor instead **/
@Deprecated
public interface ReactEventCallback {
    void onEventSent(String name, @Nullable ReadableMap map);
}
