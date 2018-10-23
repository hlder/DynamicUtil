package com.dynamicutils;

import android.app.SharedElementCallback;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.dynamicutils.internal.Parant;


/**
 * 所有插件的activity都需要继承自此类
 * 需要注意的是
 * 1.context不能使用this，需要使用getContext
 * 2.资源文件名不能和宿主文件名冲突
 * 3.layout.xml布局文件中不能使用自定义控件，包括系统自定义控件，若要使用，请在java中new
 * 其他一切OK
 * @author hld
 */
public class DynamicParantActivity implements Parant {

    DynamicChildActivity DynamicActivity;

    DynamicLayoutInflater dynamicLayoutInflater;

    private String apkPath;

    public void setApkPath(String apkPath){
        this.apkPath=apkPath;
    }
    public void setDynamicActivity(DynamicChildActivity context){
        this.DynamicActivity=context;
    }
    public Context getContext(){
        if(DynamicActivity!=null){
            return DynamicActivity;
        }
        return null;
    }
    public DynamicChildActivity getActivity(){
        if(DynamicActivity!=null){
            return DynamicActivity;
        }
        return null;
    }

    @Override
    public Resources getResources() {
        return getContext().getResources();
    }

    @Override
    public Context getBaseContext() {
        if(DynamicActivity!=null){
            return DynamicActivity.getBaseContext();
        }
        return null;
    }

    @Override
    public Context getApplicationContext() {
        if(DynamicActivity!=null){
            return DynamicActivity.getApplicationContext();
        }
        return null;
    }


    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        if(DynamicActivity!=null){
            if(dynamicLayoutInflater==null){
                dynamicLayoutInflater=new DynamicLayoutInflater(DynamicActivity);
                dynamicLayoutInflater.setLayoutInflater(DynamicActivity.getLayoutInflater());
            }
            return dynamicLayoutInflater;
        }else{
            return null;
        }
    }

    @Override
    public void setContentView(View view) {
        if(DynamicActivity!=null){
            DynamicActivity.setContentView(view);
        }
    }
    @Override
    public void setContentView(int layoutResID) {
        if(DynamicActivity!=null){
            View view=LayoutInflater.from(getContext()).inflate(DynamicActivity.getmResources().getLayout(layoutResID),null);
            DynamicActivity.setContentView(view);
        }
    }
    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {

        if(DynamicActivity!=null){
            DynamicActivity.setContentView(view,params);
        }
    }

    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params) {

        if(DynamicActivity!=null){
            DynamicActivity.addContentView(view,params);
        }
    }

    @Override
    public <T extends View> T findViewById(int id) {
        if(DynamicActivity!=null){
            return DynamicActivity.findViewById(id);
        }
        return null;
    }


    @Override
    public void setTheme(int resid) {
        if(DynamicActivity!=null){
            DynamicActivity.setTheme(resid);
        }
    }


    @Override
    public void setExitSharedElementCallback(SharedElementCallback callback) {
        if(DynamicActivity!=null){
            DynamicActivity.setExitSharedElementCallback(callback);
        }
    }

    @Override
    public void setEnterSharedElementCallback(SharedElementCallback callback) {
        if(DynamicActivity!=null){
            DynamicActivity.setEnterSharedElementCallback(callback);
        }
    }

    @Override
    public void setContentTransitionManager(TransitionManager tm) {
        if(DynamicActivity!=null){
            DynamicActivity.setContentTransitionManager(tm);
        }
    }

    @Override
    public void setVisible(boolean visible) {
        if(DynamicActivity!=null){
            DynamicActivity.setVisible(visible);
        }
    }

    @Override
    public void setTitle(int titleId) {
        if(DynamicActivity!=null){
            DynamicActivity.setTitle(titleId);
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        if(DynamicActivity!=null){
            DynamicActivity.setTitle(title);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O_MR1)
    @Override
    public void setTurnScreenOn(boolean turnScreenOn) {
        if(DynamicActivity!=null){
            DynamicActivity.setTurnScreenOn(turnScreenOn);
        }
    }

    @Override
    public void setIntent(Intent newIntent) {
        if(DynamicActivity!=null){
            DynamicActivity.setIntent(newIntent);
        }
    }

    @Override
    public void setActionBar(@Nullable Toolbar toolbar) {
        if(DynamicActivity!=null){
            DynamicActivity.setActionBar(toolbar);
        }
    }

    @Override
    public void setFinishOnTouchOutside(boolean finish) {
        if(DynamicActivity!=null){
            DynamicActivity.setFinishOnTouchOutside(finish);
        }
    }






    @Override
    public void startActivity(Intent intent) {
        chanageIntent(intent);
        getContext().startActivity(intent);
    }

    @Override
    public void startActivity(Intent intent, @Nullable Bundle options) {
        chanageIntent(intent);
        getContext().startActivity(intent, options);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        chanageIntent(intent);
        DynamicActivity.startActivityForResult(intent, requestCode);
    }

    //转换指向,将所有跳转到DynamicChildActivity
    private void chanageIntent(Intent intent){
        if(DynamicActivity==null){
            return;
        }

        String action=intent.getAction();
        if(action!=null&&!"".equals(action)){
            if(action.equals(Intent.ACTION_VIEW)||action.equals(Intent.ACTION_MAIN)||action.equals(Intent.ACTION_ATTACH_DATA)||action.equals(Intent.ACTION_EDIT)
                    ||action.equals(Intent.ACTION_PICK)||action.equals(Intent.ACTION_CHOOSER)||action.equals(Intent.ACTION_GET_CONTENT)||action.equals(Intent.ACTION_DIAL)
                    ||action.equals(Intent.ACTION_SEND)||action.equals(Intent.ACTION_SENDTO)||action.equals(Intent.ACTION_ANSWER)
                    ||action.equals(Intent.ACTION_INSERT)||action.equals(Intent.ACTION_DELETE)||action.equals(Intent.ACTION_RUN)
                    ||action.equals(Intent.ACTION_SYNC)||action.equals(Intent.ACTION_PICK_ACTIVITY)||action.equals(Intent.ACTION_SEARCH)
                    ||action.equals(Intent.ACTION_WEB_SEARCH)||action.equals(Intent.ACTION_FACTORY_TEST)){
                return;
            }
        }


        String className=intent.getComponent().getClassName();
        if("".equals(className)||className==null){
            return;
        }

        intent.putExtra("DynamicApkPath",""+apkPath);
        intent.putExtra("DynamicActivityClassName",className);
        intent.putExtra("testClassName",className);
        intent.setClass(DynamicActivity,DynamicChildActivity.class);
        Log.d("dddd",""+intent);
    }




    @Override
    public void finish() {
        if(DynamicActivity!=null){
            DynamicActivity.finish();
        }

    }


    @Override
    public ComponentName startService(Intent service) {
        return getContext().startService(service);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public ComponentName startForegroundService(Intent service) {
        return DynamicActivity.startForegroundService(service);
    }



    @Override
    public SharedPreferences getSharedPreferences(String name, int mode) {
        if(DynamicActivity!=null){
            return DynamicActivity.getSharedPreferences(name,mode);
        }
        return null;
    }




    /**
     *
     * @param intent
     * @param requestCode
     * @param options
     * @hide 隐藏
     */
    @Override
    @Deprecated
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {}








    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void onRestart() {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return true;
    }
}
