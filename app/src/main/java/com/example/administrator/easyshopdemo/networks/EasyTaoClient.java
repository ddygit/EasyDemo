package com.example.administrator.easyshopdemo.networks;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Administrator on 2016/11/19.
 */

public class EasyTaoClient {
    private static EasyTaoClient easyTaoClient;
    private OkHttpClient okHttpClient;
//    private Retrofit retrofit;

    private EasyTaoClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

    }

    public static EasyTaoClient getInstance() {

        if (easyTaoClient == null) {
            easyTaoClient = new EasyTaoClient();
        }
        return easyTaoClient;
    }

//    public EasyDemoApi getEasyDemoApi() {
//        return easyDemoApi;
//    }
//
//    private EasyDemoApi easyDemoApi;
public Call register(String username, String password) {
    //表单形式构建请求体
    RequestBody requestBody = new FormBody.Builder()
            .add("username", username)
            .add("password", password)
            .build();

    //构建请求
    Request request = new Request.Builder()
            .url(EasyDemoApi.BASE_URL + EasyDemoApi.REGISTER)
            .post(requestBody) //ctrl+p查看参数
            .build();

    return okHttpClient.newCall(request);
}


    /**
     * 登录
     * <p>
     * post
     *
     * @param username 用户名
     * @param password 密码
     */

    public Call login(String username, String password) {
        //表单形式构建请求体
        RequestBody requestBody = new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .build();

        //构建请求
        Request request = new Request.Builder()
                .url(EasyDemoApi.BASE_URL + EasyDemoApi.LOGIN)
                .post(requestBody) //ctrl+p查看参数
                .build();

        return okHttpClient.newCall(request);
    }

}
