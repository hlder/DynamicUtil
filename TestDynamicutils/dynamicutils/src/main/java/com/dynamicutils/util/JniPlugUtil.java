package com.dynamicutils.util;


import android.content.Context;
import android.os.Build;
import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import dalvik.system.DexFile;
import dalvik.system.PathClassLoader;

/**
 * 将so文件目录添加到系统扫描目录,
 * 使用方法，先将需要加载so文件copy到data/data/packagename/下，然后调用JniPlugUtil.installSoDir就可以了
 * Created by EX-HANLIANGDONG001 on 2018/8/20.
 */

public class JniPlugUtil {

    /**
     * 将 so所在的目录放入PathClassLoader里的nativeLibraryDirectories中
     * @param context
     */
    public static void installSoDir(Context context) {
        //安卓4.0以下不维护
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            return ;
        }
//        File soDirFile = context.getDir("lib", Context.MODE_PRIVATE);
//        File soDirFile = context.getDir("lib", Context.MODE_PRIVATE);
//
        String path="/data/data/"+context.getPackageName()+"/app_lib";
        File soDirFile = new File(path);
//        File soDirFile = new File(soPath);
        if(!soDirFile.exists()) {
            soDirFile.mkdirs();
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            v23Install(soDirFile, context);
//            v14Install(soDirFile, context);
        } else {
            v14Install(soDirFile, context);
        }
    }

    private static void v14Install(File soDirFile, Context context) {
        PathClassLoader pathClassLoader = (PathClassLoader) context.getClassLoader();
        Object pathList = getPathList(pathClassLoader);
        if(pathList != null) {
            //获取当前类的属性
            try {
                Field nativeLibraryDirectoriesField = pathList.getClass().getDeclaredField("nativeLibraryDirectories");
                nativeLibraryDirectoriesField.setAccessible(true);
                Object list = nativeLibraryDirectoriesField.get(pathList);
                if(list instanceof List) {
                    ((List) list).add(soDirFile);
                } else if(list instanceof File[]) {
                    File[] newList = new File[((File[]) list).length + 1];
                    System.arraycopy(list, 0 , newList, 0, ((File[]) list).length);
                    newList[((File[]) list).length] = soDirFile;
                    nativeLibraryDirectoriesField.set(pathList, newList);
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private static void v23Install(File soDirFile, Context context) {
        PathClassLoader pathClassLoader = (PathClassLoader) context.getClassLoader();
        Object pathList = getPathList(pathClassLoader);
        if(pathList != null) {
            //获取当前类的属性
            try {
                Field nativeLibraryPathField = pathList.getClass().getDeclaredField("nativeLibraryPathElements");
                nativeLibraryPathField.setAccessible(true);
                Object list = nativeLibraryPathField.get(pathList);

                Class<?> elementType = nativeLibraryPathField.getType().getComponentType();
//                Constructor[] cons = elementType.getDeclaredConstructors();
//                Log.d("dddd",cons+"");
                Constructor<?> constructor = elementType.getConstructor(File.class, boolean.class, File.class, DexFile.class);
//                Constructor<?> constructor = elementType.getConstructor(File.class);

                constructor.setAccessible(true);
                Object element = constructor.newInstance(soDirFile, true, null, null);
//                Object element = constructor.newInstance(soDirFile);
                if(list instanceof List) {
                    ((List) list).add(element);
                } else if(list instanceof Object[]) {
                    Class<?> cls=list.getClass().getComponentType();
                    Object[] newList = (Object[]) Array.newInstance(cls, ((Object[]) list).length + 1);

                    System.arraycopy(list, 0 , newList, 0, ((Object[]) list).length);
                    newList[((Object[]) list).length] = element;
                    nativeLibraryPathField.set(pathList, newList);
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
    private static Object getPathList(Object classLoader) {
        Class cls = null;
        String pathListName = "pathList";
        try {
            cls = Class.forName("dalvik.system.BaseDexClassLoader");
            Field declaredField = cls.getDeclaredField(pathListName);
            declaredField.setAccessible(true);
            return declaredField.get(classLoader);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
