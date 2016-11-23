package com.example.administrator.easyshopdemo.user.register;

import com.example.administrator.easyshopdemo.model.CachePreferences;
import com.example.administrator.easyshopdemo.model.User;
import com.example.administrator.easyshopdemo.model.UserResult;
import com.example.administrator.easyshopdemo.networks.EasyTaoClient;
import com.example.administrator.easyshopdemo.networks.UICallBack;
import com.example.administrator.easyshopdemo.user.RegisterView;
import com.google.gson.Gson;
import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/11/23.
 */

public class RegisterPresenter extends MvpNullObjectBasePresenter<RegisterView> {
    private Call call;

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (call != null) call.cancel();
    }

    public void register(String username, String password) {
        getView().showPgd();
        call = EasyTaoClient.getInstance().register(username, password);
        call.enqueue(new UICallBack() {
            @Override
            public void onFailureUI(Call call, IOException e) {
                getView().hidePgd();
                getView().showMsg(e.getMessage());
            }

            @Override
            public void onResponseUI(Call call, String body) {
                getView().hidePgd();
                //拿到返回的结果
                UserResult userResult = new Gson().fromJson(body, UserResult.class);
                //根据结果码处理不同情况
                if (userResult.getCode() == 1) {
                    getView().showMsg("注册成功");
                    //拿到用户的实体类
                    User user = userResult.getData();

                    //将用户信息保存到本地配置里
                    //                    CachePreferences.init(RegisterActivity.this);
                    CachePreferences.setUser(user);
                    getView().registerSuccess();
                    // TODO: 2016/11/21 0021 页面跳转实现，使用eventbus
                    // TODO: 2016/11/21 0021 还需要登录环信，待实现
                } else if (userResult.getCode() == 2) {
                    getView().showMsg(userResult.getMessage());
                    getView().registerFailed();
                } else {
                    getView().showMsg("未知错误！");
                    getView().registerFailed();
                }
            }
        });
    }

}
