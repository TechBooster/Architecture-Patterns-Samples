package com.chucknorrisviewer.react;

import android.app.Application;

import com.chucknorrisviewer.BuildConfig;
import com.chucknorrisviewer.nativeModule.EventProcessor;
import com.chucknorrisviewer.nativeModule.ReactEvent;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactInstanceManagerBuilder;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.shell.MainReactPackage;

import io.reactivex.Flowable;
import io.reactivex.processors.BehaviorProcessor;

/**
 * Service class which manages ReactInstanceManager and event listener
 * <p>
 * Once ReactInstanceManager is created, it is reused while the application is alive
 */
public class ReactService {
    private BehaviorProcessor<ReactInstanceManager> cache
            = BehaviorProcessor.create();
    private EventProcessor eventProcessor = new EventProcessor();

    private final ReactRepository reactRepository;

    public ReactService(Application application, ReactRepository reactRepository) {
        this.reactRepository = reactRepository;
        // Load js bundle file from repository and cache ReactInstanceManager
        reactRepository.observeJSBundle()
                .subscribe(jsBundle -> {
                    ReactInstanceManagerBuilder builder = ReactInstanceManager.builder()
                            .setApplication(application)
                            .setJSMainModulePath("index")
                            .addPackage(new MainReactPackage())
                            .addPackage(new MyReactPackage(eventProcessor))
                            .setUseDeveloperSupport(BuildConfig.DEBUG)
                            .setInitialLifecycleState(LifecycleState.RESUMED);
                    if (jsBundle.isAssets()) {
                        builder.setBundleAssetName(jsBundle.getPath());
                    } else {
                        builder.setJSBundleFile(jsBundle.getPath());
                    }
                    cache.onNext(builder.build());
                });
    }

    Flowable<ReactInstanceManager> observeReactInstanceManager() {
        return cache;
    }

    Flowable<ReactEvent> observeEvent() {
        return eventProcessor.getEventProcessor();
    }

    public void fetchJSFile() {
        // Here, usually download the latest JS file from server.
        // if the file exists, update repository
        //reactRepository.setJSPath(jsPath, false);
    }

}
