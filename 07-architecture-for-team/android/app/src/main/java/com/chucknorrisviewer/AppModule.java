package com.chucknorrisviewer;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final MainApplication app;

    AppModule(MainApplication app) {
        this.app = app;
    }

    @Singleton
    @Provides
    MainApplication provideMainApplication() {
        return app;
    }
}
