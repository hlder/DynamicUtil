package com.hld.testDn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;

import com.dynamicutils.DynamicUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //在执行之前，需要先将plugA打包成apk，并放入手机/模拟器的"/data/data/com.hld.testDn/files"下,否则将无法访问
        //启动插件A中的MainActivity

//        PermissionUtils.verifyStoragePermissions(this);
        DynamicUtil.startDynamicActivity(MainActivity.this,
                "/sdcard/HldSimpleApps/SearchBaidu.apk",
                        "com.hld.searchbd.MainActivity");
//                "com.hld.searchbd.TestActivity");

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DynamicUtil.startDynamicActivity(MainActivity.this,
                        "/sdcard/HldSimpleApps/SearchBaidu.apk",
//                        "com.hld.searchbd.MainActivity");
                        "com.hld.searchbd.TestActivity");
            }
        });
//        DynamicUtil.startDynamicActivity(this,"/data/data/com.hld.testDn/files/a.apk","pluga.hld.com.pluga.MainActivity");
    }
}
