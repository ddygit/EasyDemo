package com.example.administrator.easyshopdemo.user.login;

import com.example.administrator.easyshopdemo.model.CachePreferences;
import com.example.administrator.easyshopdemo.model.User;
import com.example.administrator.easyshopdemo.model.UserResult;
import com.example.administrator.easyshopdemo.networks.EasyTaoClient;
import com.example.administrator.easyshopdemo.networks.UICallBack;
import com.google.gson.Gson;
import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/11/23.
 */

public class LoginPresenter extends MvpNullObjectBasePresenter<LoginView>{

    private Call call;

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (call!=null)call.cancel();
    }
    public void login(String username,String password){
        getView().showPgd();

        call = EasyTaoClient.getInstance().login(username, password);
        call.enqueue(new UICallBack() {
            @Override
            public void onFailureUI(Call call, IOException e) {
                getView().hidePgd();
                getView().showMsg(e.getMessage());

            }

            @Override
            public void onResponseUI(Call call, String body) {
                getView().hidePgd();
                UserResult userResult = new Gson().fromJson(body, UserResult.class);
                if (userResult.getCode()==1){
                    getView().showMsg("登录成功");
                    User data = userResult.getData();
                    CachePreferences.setUser(data);
                    getView().loginSuccess();
                }else if(userResult.getCode()==2){
                    getView().showMsg(userResult.getMessage());
                    getView().loginFailed();
                }else{
                    getView().showMsg("未知错误！");
                    getView().loginFailed();
                }

            }
        });
    }
}
