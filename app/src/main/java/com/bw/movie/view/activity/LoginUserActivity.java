package com.bw.movie.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.model.bean.UsreIdBean;
import com.bw.movie.presenter.UsreIdPresenter;
import com.bw.movie.view.IView.IUserIdView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginUserActivity extends Activity implements View.OnClickListener, IUserIdView {

    private static final int CROP_PHOTO = 2;
    private static final int REQUEST_CODE_PICK_IMAGE=3;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 6;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE2 = 7;

    private SimpleDraweeView headsimlple;
    private TextView nametext;
    private TextView sextext;
    private TextView birthdaytext;
    private TextView phone;
    private TextView mailbox;
    private LinearLayout resetpwd;
    private TextView exitlogon;
    private UsreIdPresenter usreIdPresenter;
    String nan;
    private LinearLayout ll_popup;
    private PopupWindow pop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        infoView();
        SharedPreferences sp = LoginUserActivity.this.getSharedPreferences("userInfo", 0);
        String sessionId = sp.getString("sessionId", null);
        String userId = sp.getString("userId", null);
        Log.i("vv", "sessionId " + sessionId + "userId" + userId);
        usreIdPresenter = new UsreIdPresenter();
        usreIdPresenter.attch(this);
        usreIdPresenter.infoData(userId, sessionId);

    }

    private void infoView() {
        headsimlple = findViewById(R.id.set_head_simlple);
        nametext = findViewById(R.id.set_name_text);
        sextext = findViewById(R.id.set_sex_text);
        birthdaytext = findViewById(R.id.set_birthday_text);
        phone = findViewById(R.id.set_phone);
        mailbox = findViewById(R.id.set_mailbox);
        resetpwd = findViewById(R.id.set_reset_pwd);
        exitlogon = findViewById(R.id.set_exit_logon);
        resetpwd.setOnClickListener(this);
        exitlogon.setOnClickListener(this);
        headsimlple.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.set_reset_pwd:
                Intent intent = new Intent(LoginUserActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.set_exit_logon:
                SharedPreferences sp = this.getSharedPreferences("userInfo", 0);
                sp.edit().clear().commit();
                finish();
                break;
            case R.id.set_head_simlple:
                showPopupWindow();
                ll_popup.startAnimation(AnimationUtils.loadAnimation(
                        LoginUserActivity.this, R.anim.anim_in));
                pop.showAtLocation(v, Gravity.BOTTOM, 0, 0);
                showPopupWindow();
                ll_popup.startAnimation(AnimationUtils.loadAnimation(
                        LoginUserActivity.this, R.anim.anim_out));
                pop.showAtLocation(v, Gravity.BOTTOM, 0, 0);
                break;
        }
    }

    private void showPopupWindow() {
        pop = new PopupWindow(LoginUserActivity.this);
        View view = getLayoutInflater().inflate(R.layout.item_popupwindows,
                null);
        ll_popup = view.findViewById(R.id.ll_popup);
        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);
        RelativeLayout parent = view.findViewById(R.id.parent);
        Button bt1 = view.findViewById(R.id.item_popupwindows_camera);
        Button bt2 = view.findViewById(R.id.item_popupwindows_Photo);
        Button bt3 = view.findViewById(R.id.item_popupwindows_cancel);
       bt1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });
    }

    @Override
    public void onsuccess(UsreIdBean usreIdBean) {

        UsreIdBean.ResultBean result = usreIdBean.getResult();
        String nickName = result.getNickName();
        String headPic = result.getHeadPic();
        int sex = result.getSex();
        if (sex == 1) {
            nan = "男";
        }
        if (sex == 2) {
            nan = "女";
        }
        long birthday = result.getBirthday();
        String phone2 = result.getPhone();
        int id = result.getId();
        nametext.setText(nickName);
        headsimlple.setImageURI(Uri.parse(headPic));
        Date date = new Date(birthday);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        String format = sd.format(date);
        birthdaytext.setText(format);
        phone.setText(phone2);
        sextext.setText(nan);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        usreIdPresenter.delech();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
