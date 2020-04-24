package com.xuchengpu.pluginlibrary;

import android.os.Bundle;

/**
 * Created by 许成谱 on 2019/7/2 23:21.
 * qq:1550540124
 * for:热爱生活每一天！
 */
public interface IPlugin {
    String FROM_EXTERNAL = "FROM_EXTERNAL";
    String FROM_INTERNAL = "FROM_INTERNAL";
    void attach(ProxyActivity proxyActivity);
    void onCreate(Bundle savedInstanceState);
}
