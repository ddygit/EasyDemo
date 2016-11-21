package com.example.administrator.easyshopdemo.networks;

import com.example.administrator.easyshopdemo.model.Easy;
import com.example.administrator.easyshopdemo.model.EasyDemoResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2016/11/19.
 */

public interface EasyDemoApi {
    static String BASL_URL = "https://api.bmob.cn";

    @POST("/1/users")
    Call<EasyDemoResult> register(@Body Easy easy);
}
