package com.chucknorrisviewer.react;

import com.chucknorrisviewer.nativeModule.EventProcessor;
import com.chucknorrisviewer.nativeModule.ReactEventHook;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MyReactPackage implements ReactPackage {
    private EventProcessor event;
    MyReactPackage(EventProcessor event) {
        this.event = event;
    }

    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        List<NativeModule> modules = new ArrayList<>();
        modules.add(new ReactEventHook(reactContext, event));
        return modules;
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }
}
