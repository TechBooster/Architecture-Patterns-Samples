package com.chucknorrisviewer;

import com.chucknorrisviewer.react.MyReactActivity;
import com.chucknorrisviewer.react.ReactModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { AppModule.class, ReactModule.class })
public interface AppComponent {
    void inject(MainApplication app);
    void inject(MyReactActivity myReactActivity);
}
