package com.example.administrator.easyshopdemo.main.me.personInfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.easyshopdemo.R;
import com.example.administrator.easyshopdemo.commons.ActivityUtils;
import com.example.administrator.easyshopdemo.components.ProgressDialogFragment;
import com.example.administrator.easyshopdemo.main.MainActivity;
import com.example.administrator.easyshopdemo.model.CachePreferences;
import com.example.administrator.easyshopdemo.model.PersonItem;
import com.example.administrator.easyshopdemo.model.User;
import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.pkmmte.view.CircularImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonActivity extends MvpActivity<PersonView, PersonPresenter> implements PersonView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_user_head)
    CircularImageView ivUserHead;
    @BindView(R.id.listView)
    ListView listView;
    private ActivityUtils activityUtils;
    private List<PersonItem> list;
    private PersonAdapter adapter;
private ProgressDialogFragment dialogFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        ButterKnife.bind(this);

        init();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        activityUtils = new ActivityUtils(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        list = new ArrayList<>();
        adapter = new PersonAdapter(list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(listener);

        //获取用户头像
        // TODO: 2016/11/23 为实现。无效
        updataAvatar(CachePreferences.getUser().getHead_Image());
    }

    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        initList();
        adapter.notifyDataSetChanged();
    }

    private void initList() {
        User user = CachePreferences.getUser();
        list.add(new PersonItem(getResources().getString(R.string.username), user.getName()));
        list.add(new PersonItem(getResources().getString(R.string.nickname), user.getNick_name()));
        list.add(new PersonItem(getResources().getString(R.string.hx_id), user.getHx_Id()));
    }

    private AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                case 0:
                    activityUtils.showToast(getResources().getString(R.string.username_update));
                    break;
                case 1:
//                    activityUtils.startActivity("昵称页面");
                    break;
                case 2:
                    activityUtils.showToast(getResources().getString(R.string.id_update));
                    break;

            }
        }
    };

    @NonNull
    @Override
    public PersonPresenter createPresenter() {
        return new PersonPresenter();
    }

    @Override
    public void showPrb() {
        if (dialogFragment==null)dialogFragment=new ProgressDialogFragment();
        if (dialogFragment.isVisible())return;
        dialogFragment.show(getSupportFragmentManager(),"progressDialog");

    }

    @Override
    public void hidePrb() {
dialogFragment.dismiss();
    }

    @Override
    public void showMsg(String msg) {
activityUtils.showToast(msg);
    }

    @Override
    public void updataAvatar(String url) {
        // TODO: 2016/11/23 设置头像
    }

    @OnClick({R.id.iv_user_head, R.id.btn_login_out})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_user_head:
                // TODO: 2016/11/23 0023 上传待实现
                activityUtils.showToast("未实现");
                break;
            case R.id.btn_login_out:
                // TODO: 2016/11/23 0023 环信的退出登录
                //清空本地配置
                CachePreferences.clearAllData();
                //                //清除所有旧的activtiy
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
        }
    }
}
