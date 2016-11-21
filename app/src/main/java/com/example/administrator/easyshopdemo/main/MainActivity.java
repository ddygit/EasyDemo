package com.example.administrator.easyshopdemo.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.easyshopdemo.R;
import com.example.administrator.easyshopdemo.commons.ActivityUtils;
import com.example.administrator.easyshopdemo.main.me.MeFragment;
import com.example.administrator.easyshopdemo.main.shop.ShopFragment;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tb_main)
    Toolbar tbMain;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindViews({R.id.tv_shop, R.id.tv_msg, R.id.tv_mail, R.id.tb_me})
    TextView[] textViews;
    @BindView(R.id.ll_main)
    LinearLayout llMain;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;

    private Unbinder unbinder;
    private boolean isExist = false;
    private ActivityUtils activityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        activityUtils = new ActivityUtils(this);
                setSupportActionBar(tbMain);
                getSupportActionBar().setTitle("");
        init();
    }

    private void init() {
        textViews[0].setSelected(true);
        viewPager.setAdapter(unLoginAdapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (TextView textView : textViews) {
                    textView.setSelected(false);
                }
                textViews[position].setSelected(true);
                tvTitle.setText(textViews[position].getText());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private FragmentStatePagerAdapter unLoginAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            switch (position) {

                case 0:
                    return new ShopFragment();
                case 1:
                    return new UnLoginFragment();
                case 2:
                    return new UnLoginFragment();
                case 3:
                    return new MeFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }
    };

    @Override
    public void onBackPressed() {
        if (!isExist) {
            isExist = true;
            activityUtils.showToast("再按一次退出");
            viewPager.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isExist = false;
                }
            }, 2000);
        } else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_shop, R.id.tv_msg, R.id.tv_mail, R.id.tb_me})
    public void onClick(TextView view) {
       for(int i=0;i<textViews.length;i++){
           textViews[i].setSelected(false);
           textViews[i].setTag(i);
       }
        view.setSelected(true);
        viewPager.setCurrentItem((Integer) view.getTag(),false);
        tvTitle.setText(textViews[(Integer) view.getTag()].getText());
    }

}
