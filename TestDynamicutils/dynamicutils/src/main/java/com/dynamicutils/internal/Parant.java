package com.dynamicutils.internal;

import android.app.SharedElementCallback;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.transition.TransitionManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

public interface Parant {

     Resources getResources();

    Context getBaseContext();

    Context getApplicationContext();

    LayoutInflater getLayoutInflater();
    void setContentView(View view);
    void setContentView(int layoutResID);
    void setContentView(View view, ViewGroup.LayoutParams params);

    void addContentView(View view, ViewGroup.LayoutParams params);

    <T extends View> T findViewById(int id);

    void setTheme(int resid);


    void setExitSharedElementCallback(SharedElementCallback callback);

    void setEnterSharedElementCallback(SharedElementCallback callback);
    void setContentTransitionManager(TransitionManager tm);

    void setVisible(boolean visible);

    void setTitle(int titleId);

    void setTitle(CharSequence title);

    void setTurnScreenOn(boolean turnScreenOn);

    void setIntent(Intent newIntent);

    void setActionBar(@Nullable Toolbar toolbar);

    void setFinishOnTouchOutside(boolean finish);


    void startActivity(Intent intent) ;
    void startActivity(Intent intent, @Nullable Bundle options);
    void startActivityForResult(Intent intent, int requestCode);


    ComponentName startService(Intent service);
    ComponentName startForegroundService(Intent service) ;

    void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options);


    void onCreate(@Nullable Bundle savedInstanceState);
    void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState);
    void onPause();
    void onResume();
    void onStart();

    void onStop();

    void onDestroy();
    void onRestart();
    void onActivityResult(int requestCode, int resultCode, Intent data);

    boolean onKeyUp(int keyCode, KeyEvent event);
    void finish();
    SharedPreferences getSharedPreferences(String name, int mode);
}
