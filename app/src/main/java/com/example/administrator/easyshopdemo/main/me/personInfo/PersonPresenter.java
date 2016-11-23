package com.example.administrator.easyshopdemo.main.me.personInfo;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/11/23.
 */

public class PersonPresenter extends MvpNullObjectBasePresenter<PersonView>{

    private Call call;

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (call!=null)call.cancel();
    }
    //上传头像
    public void upDataAvatar(){

    }
}
