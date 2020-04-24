package com.xuchengpu.plugin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xuchengpu.pluginlibrary.PluginManager;
import com.xuchengpu.pluginlibrary.ProxyActivity;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView mTvLoad;
    private TextView mTvGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvLoad = findViewById(R.id.tv_load);
        mTvGo = findViewById(R.id.tv_go);
        PluginManager.getInstance().init(this);
        mTvLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String path = FileUtils.copyAssetsSingleFile(MainActivity.this, "plugin2.apk");
                if(path==null) {
                    Toast.makeText(MainActivity.this, "路径为null", Toast.LENGTH_SHORT).show();
                }else{
                    PluginManager.getInstance().loadPluginApk(path);
                    Toast.makeText(MainActivity.this, "加载成功path=="+path, Toast.LENGTH_SHORT).show();
                }
            }
        });
        mTvGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProxyActivity.class);
                intent.putExtra("className","com.xuchengpu.myplugin.MainActivity");
                startActivity(intent);
            }
        });

    }
}
