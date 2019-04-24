package com.cybt.test.coolweather.util;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 服务器交互代码
 */
public class HttpUtil {
    public static void sendOkHttpRequest(String address, okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();//实例化OkHttpClient
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
