package com.chucknorrisviewer.react;

import java.io.File;

import io.reactivex.Flowable;
import io.reactivex.processors.BehaviorProcessor;

/**
 * Repository class that stores react bundle files
 */
class ReactRepository {
    private final BehaviorProcessor<ReactBundleFile> bundleFilePath = BehaviorProcessor.create();

    ReactRepository() {
        // Set path to downloaded file
        File jsFile = new File("path/to/downloaded/js/file");
        if(jsFile.exists() && jsFile.isFile()) {
            bundleFilePath.onNext(new ReactBundleFile(jsFile.getPath(), false));
        } else {
            // Asset file
            // name
            bundleFilePath.onNext(new ReactBundleFile("index.android.bundle", true));
        }
    }

    void setJSPath(String jsPath, boolean isAsset) {
        bundleFilePath.onNext(new ReactBundleFile(jsPath, isAsset));
    }

    Flowable<ReactBundleFile> observeJSBundle() {
        return bundleFilePath;
    }

    public static class ReactBundleFile {

        private String path;
        private boolean isAssets;

        public ReactBundleFile(String path, boolean isAssets) {
            this.path = path;
            this.isAssets = isAssets;
        }

        public String getPath() {
            return path;
        }

        public boolean isAssets() {
            return isAssets;
        }
    }
}
