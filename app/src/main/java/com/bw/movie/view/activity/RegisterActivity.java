package com.bw.movie.view.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.model.bean.RegBean;
import com.bw.movie.model.miwen.EncryptUtil;
import com.bw.movie.presenter.RagPresenter;
import com.bw.movie.view.IView.IRegView;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements IRegView, View.OnClickListener {

    private EditText regeditnicheng;
    private EditText regeditMobile;
    private EditText regeditpassword;
    private Button regbutton;
    private RadioButton regnan;
    private RadioButton regnv;
    private EditText regyouxiang;
    private EditText regchushengriqi;
    int sex = 1;
    private RagPresenter ragPresenter;
    private EditText pwd2;
    private String ragpwd2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        infoView();
        ragPresenter = new RagPresenter();
        ragPresenter.attch(this);


    }

    private void infoView() {
        regeditnicheng = findViewById(R.id.reg_edit_nicheng);
        regeditMobile = findViewById(R.id.reg_edit_Mobile);
        regeditpassword = findViewById(R.id.reg_edit_password);
        regbutton = findViewById(R.id.reg_button);
        regnan = findViewById(R.id.reg_nan);
        regnv = findViewById(R.id.reg_nv);
        regyouxiang = findViewById(R.id.reg_edit_youxiang);
        regchushengriqi = findViewById(R.id.reg_edit_chushengriqi);
        pwd2 = findViewById(R.id.reg_edit_password2);
        regbutton.setOnClickListener(this);
        regchushengriqi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //日期选择器
            case R.id.reg_edit_chushengriqi:
                Toast.makeText(this, "点了", Toast.LENGTH_SHORT).show();

                new DatePickerDialog(RegisterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        regchushengriqi.setText(String.format("%d-%d-%d", year, monthOfYear + 1, dayOfMonth));
                    }
                }, 2000, 1, 2).show();
                break;
                //注册成功
            case R.id.reg_button:
                boolean nancheck = regnan.isChecked();
                boolean nvcheck = regnv.isChecked();
                if (nancheck) {
                    sex = 1;
                } else if (nvcheck) {
                    sex = 2;
                }
                String regncname = regeditnicheng.getText().toString();
                String regmobile = regeditMobile.getText().toString();
                String regpwd = regeditpassword.getText().toString();
                String regyx = regyouxiang.getText().toString();
                String regrq = regchushengriqi.getText().toString();
                String ragpwd2 = pwd2.getText().toString();
                String regencryptpwd = EncryptUtil.encrypt(regpwd);
                String regencryptpwd2 = EncryptUtil.encrypt(ragpwd2);
                Map<String, String> map = new HashMap<>();
                map.put("nickName", regncname);
                map.put("phone", regmobile);
                map.put("email", regyx);
                map.put("birthday", regrq);
                map.put("sex", sex + "");
                map.put("pwd", regencryptpwd);
                map.put("pwd2", regencryptpwd2);
                ragPresenter.infoData(map);
                break;

        }

    }

    @Override
    public void onsuccess(RegBean regBean) {
        String message = regBean.getMessage();
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
        String status = regBean.getStatus();
        if (status.equalsIgnoreCase("0000")){
//            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onerror(String s) {
        Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();
        Log.i("xxx", "onerror: "+s);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ragPresenter.delech();
    }
}