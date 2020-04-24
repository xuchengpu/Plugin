package com.xuchengpu.myplugin;

import android.os.Bundle;

import com.xuchengpu.pluginlibrary.PluginActivity;

public class MainActivity extends PluginActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
