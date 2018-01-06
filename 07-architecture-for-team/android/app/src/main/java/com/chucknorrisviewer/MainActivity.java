package com.chucknorrisviewer;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chucknorrisviewer.react.MyReactActivity;
import com.chucknorrisviewer.react.ReactConst;

public class MainActivity extends AppCompatActivity {
    private final int OVERLAY_PERMISSION_REQ_CODE = 9000;
    private final int REACT_REQ_CODE = 9001;
    TextView result;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button searchChuck = findViewById(R.id.search_chuck);
        searchChuck.setOnClickListener(v ->
            startActivityForResult(MyReactActivity.createIntent(this, "Search"), REACT_REQ_CODE));

        result = findViewById(R.id.result);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && BuildConfig.DEBUG) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case OVERLAY_PERMISSION_REQ_CODE:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (!Settings.canDrawOverlays(this)) {
                        Toast.makeText(this, "You need permission for overlay", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case REACT_REQ_CODE:
                if (resultCode == RESULT_CANCELED) break;
                String selected = data.getStringExtra(ReactConst.ITEM_SELECTED);
                if (!TextUtils.isEmpty(selected)) {
                    result.setText(selected);
                }
                break;
        }

    }
}
