package com.dynamicutils;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xmlpull.v1.XmlPullParser;

public class DynamicLayoutInflater extends LayoutInflater {
    private LayoutInflater layoutInflater;
    private Resources mResources;

    public DynamicLayoutInflater(Context context) {
        super(context);
    }


    @Override
    public LayoutInflater cloneInContext(Context newContext) {
        return null;
    }


    public void setLayoutInflater(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    public void setmResources(Resources mResources) {
        this.mResources = mResources;
    }

    @Override
    public View inflate(int resource, @Nullable ViewGroup root) {
        if(mResources!=null){
            inflate( mResources.getLayout(resource),root);
        }
        return layoutInflater.inflate(resource, root);
    }

    @Override
    public View inflate(XmlPullParser parser, @Nullable ViewGroup root) {
        return layoutInflater.inflate(parser, root);
    }

    @Override
    public View inflate(int resource, @Nullable ViewGroup root, boolean attachToRoot) {
        if(mResources!=null){
            inflate( mResources.getLayout(resource),root,attachToRoot);
        }
        return layoutInflater.inflate(resource, root, attachToRoot);
    }

    @Override
    public View inflate(XmlPullParser parser, @Nullable ViewGroup root, boolean attachToRoot) {
        return layoutInflater.inflate(parser, root, attachToRoot);
    }

}
