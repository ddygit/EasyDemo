package com.example.administrator.easyshopdemo.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.easyshopdemo.R;
import com.example.administrator.easyshopdemo.commons.ActivityUtils;
import com.example.administrator.easyshopdemo.model.Easy;
import com.example.administrator.easyshopdemo.model.EasyDemoResult;
import com.example.administrator.easyshopdemo.networks.EasyTaoClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.et_pwdAgain)
    EditText etPwdAgain;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_register)
    Button btnRegister;

    private ActivityUtils activityUtils;
    private String username;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();

    }

    private void init() {
        activityUtils = new ActivityUtils(this);
        etUsername.addTextChangedListener(textWatch);
        etPwd.addTextChangedListener(textWatch);
        etPwdAgain.addTextChangedListener(textWatch);

    }

    private String pwdagain;
    private TextWatcher textWatch = new TextWatcher() {


        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            username = etUsername.getText().toString();
            password = etPwd.getText().toString();
            pwdagain = etPwdAgain.getText().toString();
            boolean canRegister = !(TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(pwdagain));
            btnRegister.setEnabled(canRegister);

        }
    };

    @OnClick(R.id.btn_register)
    public void onClick() {

        Easy easy=new Easy(username,password);
        Call<EasyDemoResult> register = EasyTaoClient.getInstance().getEasyDemoApi().register(easy);
   register.enqueue(new Callback<EasyDemoResult>() {
       @Override
       public void onResponse(Call<EasyDemoResult> call, Response<EasyDemoResult> response) {
           if (response.isSuccessful()&&response!=null){
               EasyDemoResult body = response.body();
               Log.d("EasyDemoResult","EasyDemoResult="+body.toString());
           }
       }

       @Override
       public void onFailure(Call<EasyDemoResult> call, Throwable t) {

       }
   });
    }
}
