package com.example.administrator.easyshopdemo.main.me;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.easyshopdemo.R;
import com.example.administrator.easyshopdemo.commons.ActivityUtils;
import com.example.administrator.easyshopdemo.user.LoginActivity;
import com.pkmmte.view.CircularImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment {


    @BindView(R.id.iv_user_head)
    CircularImageView ivUserHead;
    @BindView(R.id.tv_logReg)
    TextView tvLogReg;
    @BindView(R.id.tv_person)
    TextView tvPerson;
    @BindView(R.id.tv_myGoods)
    TextView tvMyGoods;
    @BindView(R.id.tv_goodsUp)
    TextView tvGoodsUp;
private ActivityUtils activityUtils;
    private View view;
    public MeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUtils=new ActivityUtils(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (view==null) {
            view = inflater.inflate(R.layout.fragment_me, container, false);
            ButterKnife.bind(this, view);
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        //判断是否登录，以便更改头像和用户名
    }

    @OnClick({R.id.iv_user_head, R.id.tv_logReg, R.id.tv_person, R.id.tv_myGoods, R.id.tv_goodsUp})
    public void onClick(View view) {
        activityUtils.startActivity(LoginActivity.class);
    }
}
