package com.chucknorrisviewer.nativeModule;

import android.support.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;


public class ReactEventHook extends ReactContextBaseJavaModule {

    private final EventProcessor eventProcessor;
    public ReactEventHook(ReactApplicationContext reactContext, EventProcessor eventProcessor) {
        super(reactContext);
        this.eventProcessor = eventProcessor;
    }

    @Override
    public String getName() {
        return "EventHook";
    }

    @ReactMethod
    public void sendEvent(String name, @Nullable ReadableMap data) {
        // receive parameters from react module
        eventProcessor.getEventProcessor().onNext(new ReactEvent(name,data));
    }
}
