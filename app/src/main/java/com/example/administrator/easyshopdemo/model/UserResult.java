package com.example.administrator.easyshopdemo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/11/21.
 */

public class UserResult {

    /**
     * code : 1
     * msg : succeed
     * data : {"name":"ytd70aa402693e4333a6318933226d0276","uuid":"939913BF5BEB46A29047BE66399BC1A0","username":"android"}
     */


    private int code;

    @SerializedName("msg")
    private String message;

    private User data;

    //alt + insert

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public User getData() {
        return data;
    }
}
