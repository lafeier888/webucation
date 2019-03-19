package com.webucation.impl;

import okhttp3.OkHttpClient;

import java.net.InetSocketAddress;
import java.net.Proxy;

public class OkHttpClientFactory {
    private static OkHttpClient client = null;
    //代理，用于抓包测试
    private static Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.140.1", 8888));


    private OkHttpClientFactory(){

    }
    public static OkHttpClient getInstance(){
        if (client==null) client  = new OkHttpClient.Builder().proxy(proxy).followRedirects(false).build();
        return client;
    }
}
