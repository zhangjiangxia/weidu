package com.bw.movie.view.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.view.activity.AttentionActivity;
import com.bw.movie.view.activity.BuyRecordActivity;
import com.bw.movie.view.activity.LoginActivity;
import com.bw.movie.view.activity.LoginUserActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;

public class MyFragment extends Fragment implements View.OnClickListener{

    private ImageView myyuying;
    private SimpleDraweeView mytouxiang;
    private ImageView mywodexinxi;
    private ImageView mywodeguanzhu;
    private ImageView mygoumaijilv;
    private ImageView myyijianfankui;
    private ImageView myzuixinbanben;
    private TextView username;
    private String sessionId;
    private PopupWindow pw;
    private TextView camera;
    private TextView pick;
    private String path = Environment.getExternalStorageDirectory() + "/camera.png";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_my, container, false);
        username = view.findViewById(R.id.my_user_name);
        myyuying = view.findViewById(R.id.my_img_yuying);
        mytouxiang = view.findViewById(R.id.my_img_touxiang);
        mywodexinxi = view.findViewById(R.id.my_wodexinxi);
        mywodeguanzhu = view.findViewById(R.id.my_wodeguanzhu);
        mygoumaijilv = view.findViewById(R.id.my_goupiaojilv);
        myyijianfankui = view.findViewById(R.id.my_yijianfankui);
        myzuixinbanben = view.findViewById(R.id.my_zuixinbanben);
        myyuying.setOnClickListener(this);
        mytouxiang.setOnClickListener(this);
        mywodexinxi.setOnClickListener(this);
        mywodeguanzhu.setOnClickListener(this);
        mygoumaijilv.setOnClickListener(this);
        myyijianfankui.setOnClickListener(this);
        myzuixinbanben.setOnClickListener(this);

        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sp = getActivity().getSharedPreferences("userInfo", 0);
        String nickName = sp.getString("nickName", null);
        String headPic = sp.getString("headPic", null);
        sessionId = sp.getString("sessionId", null);
        String userId = sp.getString("userId", null);
        if (sessionId!=null){
            if (username!=null){
                username.setText(nickName);
            }if (mytouxiang!=null){
                mytouxiang.setImageURI(Uri.parse(headPic));
            }
        }else{
          username.setText("注册/登录");
          mytouxiang.setImageURI((new Uri.Builder()).scheme("res").path(String.valueOf(R.mipmap.touxiang)).build());
        }

    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.my_img_yuying:
               break;
           case R.id.my_img_touxiang:
            if (sessionId==null) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(getActivity(), "您已登录", Toast.LENGTH_SHORT).show();
            }
               break;
           case R.id.my_wodexinxi:
               if(sessionId!=null){
                   Intent  intent  =new Intent(getActivity(),LoginUserActivity.class);
                   startActivity(intent);
               }
               break;
           case R.id.my_wodeguanzhu:
               Intent  intent=new Intent(getActivity(),AttentionActivity.class);
               startActivity(intent);
               break;
           case R.id.my_goupiaojilv:
               Intent  intent2=new Intent(getActivity(),BuyRecordActivity.class);
               startActivity(intent2);
               break;
           case R.id.my_yijianfankui:
               break;
           case R.id.my_zuixinbanben:
               break;
       }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                Bitmap bitmap = data.getParcelableExtra("data");
                mytouxiang.setImageBitmap(bitmap);

                break;
            case 2:
                Uri uri = data.getData();
                Intent crop = crop(uri);
                startActivityForResult(crop, 3);
                break;
            case 3:
                Bitmap bitmap2=(Bitmap) data.getExtras().get("data");
                mytouxiang.setImageBitmap(bitmap2);
                break;

        }
    }
    public Intent crop(Uri uri) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        //
        intent.setDataAndType(uri, "image/*");
        //截图
        intent.putExtra("crop", true);
        //比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        //宽高
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        //储存类型
        intent.putExtra("outputFormat", "JPEG");
        //返回类型
        intent.putExtra("return-data", true);

        return intent;

    }



}

