package com.dynamicutils;

import android.content.Context;
import android.content.Intent;

/**
 * 操作类
 * 目前只支持加载activity，service之类的还未支持
 */
public class DynamicUtil {
    /**
     * 启动插件中Activity
     * @param context
     * @param apkPath
     * @param className
     */
    public static void startDynamicActivity(Context context,String apkPath,String className){
        Intent intent=new Intent();
        startDynamicActivity(context,intent,apkPath,className);
    }

    public static void startDynamicActivity(Context context,Intent intent,String apkPath,String className){
        intent.setClass(context,DynamicChildActivity.class);
        intent.putExtra("DynamicApkPath",apkPath);
        intent.putExtra("DynamicActivityClassName",className);
        context.startActivity(intent);
    }

    /**
     * 开发的时候无需打包成插件运行，直接调用此方法便可以运行
     * 记住必须将DynamicChildActivity配置到manifest中
     */
    public static void startTestActivity(Context context,Class<?> cls){
        Intent intent=new Intent(context,DynamicChildActivity.class);
        intent.setClass(context,DynamicChildActivity.class);
        intent.putExtra("testClassName",cls.getName());
        context.startActivity(intent);
    }
    public static void startTestActivity(Context context,Intent intent,Class<?> cls){
        intent.setClass(context,DynamicChildActivity.class);
        intent.putExtra("testClassName",cls.getName());
        context.startActivity(intent);
    }

}
