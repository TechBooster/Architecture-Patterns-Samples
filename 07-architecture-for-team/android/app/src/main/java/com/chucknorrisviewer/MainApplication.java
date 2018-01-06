package com.chucknorrisviewer;

import android.app.Application;

import com.chucknorrisviewer.react.ReactModule;
import com.chucknorrisviewer.react.ReactService;

import javax.inject.Inject;

public class MainApplication extends Application {

    private AppComponent appComponent;

    @Inject
    ReactService reactService;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .reactModule(new ReactModule())
                .build();
        appComponent.inject(this);

        // Usually fetch JS file from your server in background thread
        reactService.fetchJSFile();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
