package com.chucknorrisviewer.react;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.chucknorrisviewer.MainApplication;
import com.chucknorrisviewer.R;
import com.chucknorrisviewer.nativeModule.ReactEvent;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class MyReactActivity extends AppCompatActivity implements DefaultHardwareBackBtnHandler {
    private ReactRootView reactRootView;
    private final static String KEY_SCENE = "key_scene";
    private ReactInstanceManager reactInstanceManager;
    private Disposable disposable;

    @Inject
    ReactService reactService;

    public static Intent createIntent(Context context, String scene) {
        Intent intent = new Intent(context, MyReactActivity.class);
        intent.putExtra(KEY_SCENE, scene);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Dependency Injection
        ((MainApplication) getApplication()).getAppComponent().inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_react_search);
        reactRootView = findViewById(R.id.rootView);

        // bundle file to pass React Native
        Bundle bundle = new Bundle();
        bundle.putString(ReactConst.INITIAL_SCENE, getIntent().getStringExtra(KEY_SCENE));

        reactService.observeReactInstanceManager()
                .firstElement()
                .subscribe(instanceManager -> {
                            reactInstanceManager = instanceManager;
                            reactRootView.startReactApplication(
                                    instanceManager,
                                    "ChuckNorrisViewer",
                                    bundle);
                        },
                        Throwable::printStackTrace);

    }

    @Override
    public void invokeDefaultOnBackPressed() {
        //super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Observe event
        disposable = reactService.observeEvent().subscribe(this::handleEvent);

        if (reactInstanceManager != null) {
            reactInstanceManager.onHostResume(this, this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (reactInstanceManager != null) {
            reactInstanceManager.onHostPause(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (reactInstanceManager != null) {
            reactInstanceManager.onHostDestroy(this);
        }
    }

    @Override
    public void onBackPressed() {
        if (reactInstanceManager != null) {
            reactInstanceManager.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU && reactInstanceManager != null) {
            reactInstanceManager.showDevOptionsDialog();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    private void handleEvent(ReactEvent event) {
        switch (event.eventName) {
            case ReactConst.NATIVE_BACK:
                finish();
                break;

            case ReactConst.SELECTED:
                Intent data = new Intent();

                if (event.data != null) {
                    data.putExtra(ReactConst.ITEM_SELECTED, event.data.getString(ReactConst.ITEM));
                }
                setResult(RESULT_OK, data);
                finish();
                break;
        }
    }
}
