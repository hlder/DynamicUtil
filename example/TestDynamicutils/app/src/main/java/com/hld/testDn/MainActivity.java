package com.hld.testDn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dynamicutils.DynamicUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //在执行之前，需要先将plugA打包成apk，并放入手机/模拟器的"/data/data/com.hld.testDn/files"下,否则将无法访问
        //启动插件A中的MainActivity
        DynamicUtil.startDynamicActivity(this,"/data/data/com.hld.testDn/files/a.apk","pluga.hld.com.pluga.MainActivity");
    }
}
