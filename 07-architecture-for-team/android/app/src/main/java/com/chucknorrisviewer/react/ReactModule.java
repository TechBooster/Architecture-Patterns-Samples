package com.chucknorrisviewer.react;

import com.chucknorrisviewer.MainApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ReactModule {
    @Provides
    @Singleton
    ReactService provideReactService(MainApplication app, ReactRepository reactRepository) {
        return new ReactService(app, reactRepository);
    }

    @Provides
    @Singleton
    ReactRepository provideReactRepository() {
        return new ReactRepository();
    }
}
