package com.xuchengpu.pluginlibrary;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

/**
 * Created by 许成谱 on 2019/7/2 23:25.
 * qq:1550540124
 * for:热爱生活每一天！
 */
public class ProxyActivity extends Activity  {

    private PluginApk pluginApk;
    private String className;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        className = getIntent().getStringExtra("className");
        pluginApk = PluginManager.getInstance().getPluginApk();
        launchActivity();
    }

    private void launchActivity() {
        if(pluginApk==null) {
            Toast.makeText(ProxyActivity.this, "未找到插件apk", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            Class<?> clazz = pluginApk.dexClassLoader.loadClass(className);
            Object instance = clazz.newInstance();
            if(instance instanceof IPlugin) {
                IPlugin plugin= (IPlugin) instance;
                plugin.attach(this);
                Bundle bundle=new Bundle();
                bundle.putString("FROM",IPlugin.FROM_EXTERNAL);
                plugin.onCreate(bundle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Resources getResources() {
        return pluginApk==null?super.getResources():pluginApk.resources;
    }

    @Override
    public AssetManager getAssets() {
        return pluginApk==null?super.getAssets():pluginApk.assetManager;
    }

    @Override
    public ClassLoader getClassLoader() {
        return pluginApk==null?super.getClassLoader():pluginApk.dexClassLoader;
    }
}
