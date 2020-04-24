package com.xuchengpu.pluginlibrary;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by 许成谱 on 2019/7/2 23:43.
 * qq:1550540124
 * for:热爱生活每一天！
 */
public class PluginActivity extends Activity implements IPlugin {

    private Activity mProxyActivity;
    private String from;

    @Override
    public void attach(ProxyActivity proxyActivity) {
        this.mProxyActivity=proxyActivity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if(savedInstanceState!=null) {
            from = savedInstanceState.getString("FROM");
        }
        if(from.equals(IPlugin.FROM_INTERNAL)) {
            super.onCreate(savedInstanceState);
            mProxyActivity=this;
        }

    }

    @Override
    public void setContentView(int layoutResID) {
        if(from.equals(IPlugin.FROM_INTERNAL)) {
            super.setContentView(layoutResID);
        }else{
            mProxyActivity.setContentView(layoutResID);
        }

    }
}
