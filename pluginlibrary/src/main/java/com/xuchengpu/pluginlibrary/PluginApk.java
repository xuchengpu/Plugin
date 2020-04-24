package com.xuchengpu.pluginlibrary;

import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;

import dalvik.system.DexClassLoader;

/**
 * Created by 许成谱 on 2019/7/2 22:48.
 * qq:1550540124
 * for:热爱生活每一天！
 * 用于封装插件apk的相关信息
 */
public class PluginApk {
    public DexClassLoader dexClassLoader;
    public Resources resources;
    public AssetManager assetManager;
    public PackageInfo packageInfo;

    public PluginApk(DexClassLoader dexClassLoader, Resources resources, AssetManager assetManager, PackageInfo packageInfo) {
        this.dexClassLoader = dexClassLoader;
        this.resources = resources;
        this.assetManager = assetManager;
        this.packageInfo = packageInfo;
    }
}
