package com.example.administrator.easyshopdemo.networks;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/11/19.
 */

public class MyInter implements Interceptor{
//    X-Bmob-Application-Id: Your Application ID
//    X-Bmob-REST-API-Key: Your REST API Key
//    Content-Type: application/json
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        builder.addHeader("Content-Type","application/json");
        builder.addHeader("X-Bmob-Application-Id","623aaef127882aed89b9faa348451da3");
        builder.addHeader("X-Bmob-REST-API-Key","c00104962a9b67916e8cbcb9157255de");
        Request request1 = builder.build();
        return chain.proceed(request1);
    }
}
