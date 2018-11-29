package com.bw.movie.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.model.bean.CancelFollowBean;
import com.bw.movie.model.bean.DetailsBean;
import com.bw.movie.model.bean.FollowBean;
import com.bw.movie.presenter.XqingPresenter;
import com.bw.movie.view.IView.IDetailsView;
import com.bw.movie.view.adapter.JuAdapterOuter;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JZVideoPlayerStandard;

public class DetailsActivity extends AppCompatActivity implements IDetailsView, View.OnClickListener {
    private TextView detailstext;
    private TextView detailsdirector;
    private TextView detailsduration;
    private ImageView detailsimage;
    private TextView detailstype;
    private TextView detailssynopsis;
    private XqingPresenter xqingPresenter;
    private TextView xqname;
    private ImageView xqimage;
    private TextView detaills;
    private TextView forecast;
    private TextView stage;
    private TextView filmreview;
    private ImageView fanhui;
    private Button goupiao;
    private int id;
    private DetailsBean.ResultBean result;
    private int filmid;
    private ImageView baixin;
     private boolean  dianji;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        xqingPresenter = new XqingPresenter();
        xqingPresenter.attch(this);
        setContentView(R.layout.activity_xiangqing);
        Intent intent = getIntent();
        filmid = intent.getIntExtra("filmid", 0);
        xqingPresenter.infoData(filmid + "");
        infolayout();
        detaills.setOnClickListener(this);
        forecast.setOnClickListener(this);
        stage.setOnClickListener(this);
        filmreview.setOnClickListener(this);
        fanhui.setOnClickListener(this);
        goupiao.setOnClickListener(this);
    }

    private void infolayout() {
        xqname = findViewById(R.id.xq_name);
        xqimage = findViewById(R.id.xq_image);
        detaills = findViewById(R.id.xq_details);
        forecast = findViewById(R.id.xq_forecast);
        stage = findViewById(R.id.xq_stage);
        filmreview = findViewById(R.id.xq_film_review);
        fanhui = findViewById(R.id.xq_fanhui);
        goupiao = findViewById(R.id.xq_goupiao);
        baixin = findViewById(R.id.xq_baixin);
        baixin.setOnClickListener(this);
        baixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v==baixin){
                    if (dianji){
                        baixin.setImageDrawable(getResources().getDrawable(R.mipmap.baixin));
                        xqingPresenter.showcancelFollowMovie(id);
                        dianji = false;

                    }else{
                        baixin.setImageDrawable(getResources().getDrawable(R.mipmap.hongxin));
                        xqingPresenter.showfollowMovie(id);
                        dianji = true;

                    }
                }
            }
        });
    }

    @Override
    public void OnSuccess(DetailsBean data) {
        result = data.getResult();
        id = result.getId();
        String name = result.getName();
        String imageUrl = result.getImageUrl();
        Glide.with(this).load(imageUrl).into(xqimage);
        xqname.setText(name);

    }
//
    @Override
    public void followsuccess(FollowBean followBean) {
        String message = followBean.getMessage();
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deletesuccess(CancelFollowBean cancelFollowBean) {
        String message = cancelFollowBean.getMessage();

        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.xq_details:
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.activity_details, null);
                detailstext = view.findViewById(R.id.details_text);
                detailsdirector = view.findViewById(R.id.details_director);
                detailsduration = view.findViewById(R.id.details_duration);
                detailsimage = view.findViewById(R.id.details_image);
                detailstype = view.findViewById(R.id.details_type);
                detailssynopsis = view.findViewById(R.id.details_synopsis);

                detailstext.setText(result.getName());
                detailsdirector.setText(result.getDirector());
                detailsduration.setText(result.getDuration());
                detailstype.setText(result.getMovieTypes());
                Glide.with(this).load(result.getImageUrl()).into(detailsimage);
                detailssynopsis.setText(result.getSummary());
                showPopwindow(view);

                break;
            case R.id.xq_forecast:
                LayoutInflater inflater2 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view1 = inflater2.inflate(R.layout.activity_yugao, null);
                JZVideoPlayerStandard jzVideoPlayerStandard = view1.findViewById(R.id.videoplayer);
                JZVideoPlayerStandard jzVideoPlayerStandard1 = view1.findViewById(R.id.videoplayer1);
                JZVideoPlayerStandard jzVideoPlayerStandard2 = view1.findViewById(R.id.videoplayer2);
                List<DetailsBean.ResultBean.ShortFilmListBean> shortFilmList = result.getShortFilmList();
                List<String> imageList = new ArrayList<>();
                List<String> videoList = new ArrayList<>();
                for (int i = 0; i < shortFilmList.size(); i++) {
                    imageList.add(shortFilmList.get(i).getImageUrl());
                    videoList.add(shortFilmList.get(i).getVideoUrl());
                }
                jzVideoPlayerStandard.setUp(videoList.get(0), JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL);
                jzVideoPlayerStandard.thumbImageView.setImageURI(Uri.parse(imageList.get(0)));

                jzVideoPlayerStandard1.setUp(videoList.get(1), JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL);
                jzVideoPlayerStandard1.thumbImageView.setImageURI(Uri.parse(imageList.get(1)));

                jzVideoPlayerStandard2.setUp(videoList.get(2), JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL);
                jzVideoPlayerStandard2.thumbImageView.setImageURI(Uri.parse(imageList.get(2)));
                showPopwindow(view1);
                break;
            case R.id.xq_stage:
                LayoutInflater inflater3 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view2 = inflater3.inflate(R.layout.activity_ju_zhao, null);
                RecyclerView jurecyclerview = view2.findViewById(R.id.ju_recylerview);
                List<String> posterList = result.getPosterList();
                jurecyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                JuAdapterOuter juAdapterOuter = new JuAdapterOuter(posterList, DetailsActivity.this);
                jurecyclerview.setAdapter(juAdapterOuter);
                showPopwindow(view2);
                break;
            case R.id.xq_film_review:
                Intent intent=new Intent(DetailsActivity.this,YingPingActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
                break;
            case R.id.xq_fanhui:
                Intent intent4 = new Intent(DetailsActivity.this, ShouActivity.class);
                startActivity(intent4);
                break;

        }
    }

    //详情
    private void showPopwindow(View view) {
        PopupWindow window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        window.setFocusable(true);

        ColorDrawable dw = new ColorDrawable(0xffffffff);
        window.setBackgroundDrawable(dw);
        window.setAnimationStyle(R.style.PopupAnimation);
        window.showAtLocation(DetailsActivity.this.findViewById(R.id.xq_details),
                Gravity.BOTTOM, 0, 0);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        xqingPresenter.delech();
    }
}
