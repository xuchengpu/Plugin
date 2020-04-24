package com.xuchengpu.pluginlibrary;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * Created by 许成谱 on 2019/7/2 22:51.
 * qq:1550540124
 * for:热爱生活每一天！
 */
public class PluginManager {
    private Context mContext;
    private PluginApk mPluginApk;
    private static PluginManager mInstance;

    private PluginManager() {
    }

    public static PluginManager getInstance() {
        if (mInstance == null) {
            synchronized (PluginManager.class) {
                if (mInstance == null) {
                    mInstance = new PluginManager();
                }
            }
        }
        return mInstance;
    }

    public void init(Context context) {
        mContext = context;
    }

    public PluginApk getPluginApk() {
        return mPluginApk;
    }

    /**
     * 加载插件apk
     * @param apkPath
     */
    public void loadPluginApk(String apkPath) {
        PackageInfo packageInfo = getPackageInfo(apkPath);
        if(packageInfo== null) {
            return;
        }
        DexClassLoader dexClassLoader = createDexClassLoader(apkPath);
        AssetManager assetManager = createAssetManager(apkPath);
        Resources resources = createRescource(assetManager);
        mPluginApk = new PluginApk(dexClassLoader, resources, assetManager, packageInfo);
    }

    private PackageInfo getPackageInfo(String apkPath) {
        return mContext.getPackageManager().getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES | PackageManager.GET_SERVICES);
    }

    private AssetManager createAssetManager(String apkPath) {

        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method method = assetManager.getClass().getDeclaredMethod("addAssetPath", String.class);
            method.invoke(assetManager,apkPath);
            return assetManager;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Resources createRescource(AssetManager assetManager) {
        Resources resources = mContext.getResources();
        return new Resources(assetManager,resources.getDisplayMetrics(),resources.getConfiguration());
    }


    private DexClassLoader createDexClassLoader(String apkPath) {
        File dex = mContext.getDir("dex", Context.MODE_PRIVATE);
        return new DexClassLoader(apkPath,dex.getAbsolutePath(),null,mContext.getClassLoader());
    }
}
