package com.example.administrator.easyshopdemo.networks;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/11/19.
 */

public class EasyTaoClient {
    private static EasyTaoClient easyTaoClient;
    private OkHttpClient okHttpClient;
    private Retrofit retrofit;

    private EasyTaoClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        MyInter myInter = new MyInter();
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(myInter)
                .addInterceptor(interceptor)
                .build();
        GsonConverterFactory converter = GsonConverterFactory.create();

        retrofit = new Retrofit.Builder()
                .baseUrl(EasyDemoApi.BASL_URL)
                .addConverterFactory(converter)
                .client(okHttpClient)
                .build();
        easyDemoApi = retrofit.create(EasyDemoApi.class);
    }

    public static EasyTaoClient getInstance() {

        if (easyTaoClient == null) {
            easyTaoClient = new EasyTaoClient();
        }
        return easyTaoClient;
    }

    public EasyDemoApi getEasyDemoApi() {
        return easyDemoApi;
    }

    private EasyDemoApi easyDemoApi;

}
