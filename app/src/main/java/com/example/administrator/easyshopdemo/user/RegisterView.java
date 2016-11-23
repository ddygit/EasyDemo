package com.example.administrator.easyshopdemo.user;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by Administrator on 2016/11/23.
 */

public interface RegisterView extends MvpView{
    void showPgd();
    void hidePgd();
    //注册失败
    void registerFailed();
    //注册成功
    void registerSuccess();

    void showMsg(String msg);
    //用户名和密码不符合要求
    void showUserPasswordError(String msg);
}
