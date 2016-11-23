package com.example.administrator.easyshopdemo.user.login;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by Administrator on 2016/11/23.
 */

public interface LoginView extends MvpView {
    void showPgd();
    void hidePgd();
    void showMsg(String msg);
    void loginSuccess();
    void loginFailed();
}
