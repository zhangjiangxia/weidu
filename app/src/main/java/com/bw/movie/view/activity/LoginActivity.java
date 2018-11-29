package com.bw.movie.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.DrmInitData;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.model.bean.LoginBean;
import com.bw.movie.model.bean.WeiXinLoginBean;
import com.bw.movie.model.miwen.EncryptUtil;
import com.bw.movie.model.utils.app.APP;
import com.bw.movie.presenter.LoginPresenter;
import com.bw.movie.view.IView.ILogInView;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements ILogInView,View.OnClickListener{

    private EditText logeditMobile;
    private EditText logeditpassword;
    private CheckBox logcheckjizhumima;
    private CheckBox logcheckzidongdenglu;
    private TextView logtextzhuce;
    private Button logbutlogin;
    private TextView logtextdisanfang;
    private ImageView logimagweixin;
    private LoginPresenter loginPresenter;
    private IWXAPI wxapi;
    private String code;
    public IWXAPI api; // IWXAPI 是第三方app和微信通信的openapi接口
    public static final String APP_ID = "wxb3852e6a6b7d9516";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginPresenter = new LoginPresenter();
        loginPresenter.attch(this);
        api = WXAPIFactory.createWXAPI(this, APP_ID, true);
        api.registerApp(APP_ID);
        EventBus.getDefault().register(this);
        infoView();

    }

    private void infoView() {
        logeditMobile = findViewById(R.id.log_edit_Mobile);
        logeditpassword = findViewById(R.id.log_edit_password);
        logcheckjizhumima = findViewById(R.id.log_check_jizhumima);
        logcheckzidongdenglu = findViewById(R.id.log_check_zidongdenglu);
        logtextzhuce = findViewById(R.id.log_text_zhuce);
        logbutlogin = findViewById(R.id.log_but_login);
        logtextdisanfang = findViewById(R.id.log_text_disanfang);
        logimagweixin = findViewById(R.id.log_imag_weixin);
        logtextzhuce.setOnClickListener(this);
        logbutlogin.setOnClickListener(this);
        logimagweixin.setOnClickListener(this);
        logtextdisanfang.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.log_text_zhuce:
                    Intent  intent=new Intent(LoginActivity.this,RegisterActivity.class);
                    startActivity(intent);
                    break;
                case R.id.log_but_login:

                    String logmobile = logeditMobile.getText().toString();
                    String logpwd = logeditpassword.getText().toString();
                    String encryptpwd = EncryptUtil.encrypt(logpwd);
                    Map<String,String> map= new HashMap<>();
                    map.put("phone",logmobile);
                    map.put("pwd",encryptpwd);
                    loginPresenter.infoData(map);
                    break;
                case R.id.log_text_disanfang:
                    break;
                case R.id.log_imag_weixin:
                    SendAuth.Req req = new SendAuth.Req();
                    req.scope = "snsapi_userinfo";//获取个人用户信息的权限
                    req.state = "wxb3852e6a6b7d9516";//防止攻击
                    api.sendReq(req);//向微信发送请求
                    break;
            }
    }

    @Override
    public void onsuccess(LoginBean loginBean) {
        String message = loginBean.getMessage();
        //Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
        LoginBean.ResultBean result = loginBean.getResult();
        String status = loginBean.getStatus();
        if (status.equalsIgnoreCase("0000")){
            SharedPreferences sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();
            String sessionId = result.getSessionId();
            LoginBean.ResultBean.UserInfoBean userInfo = result.getUserInfo();
            String nickName = userInfo.getNickName();
            String headPic = userInfo.getHeadPic();
            int userId = result.getUserId();
            edit.putString("userId", userId+"");
            edit.putString("sessionId",sessionId);
            edit.putString("nickName",nickName);
            edit.putString("headPic",headPic);
            edit.commit();
            Toast.makeText(this, ""+message.toString(), Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String code) {
        loginPresenter.wxLoginData(code);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.delech();
        EventBus.getDefault().unregister(this);
    }
}
