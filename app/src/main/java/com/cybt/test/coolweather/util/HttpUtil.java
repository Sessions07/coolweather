package com.cybt.test.coolweather.util;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 服务器交互代码
 */
public class HttpUtil {
    public static void sendOkHttpRequest(String address, okhttp3.Callback callback){ //异步方法：callback回调机制
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);//为什么没看到被回调的方法？是回调到此为止？不用调？调了没看见？
    }
}