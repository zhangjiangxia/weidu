package com.bw.movie.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.model.bean.CancelFollowBean;
import com.bw.movie.model.bean.DetailsBean;
import com.bw.movie.model.bean.FollowBean;
import com.bw.movie.presenter.XqingPresenter;
import com.bw.movie.view.IView.IDetailsView;
import com.bw.movie.view.adapter.YugaoAdapter;

import java.util.List;

public class YugaoActivity extends AppCompatActivity implements IDetailsView {

    private RecyclerView yg_recyclerView;
    private XqingPresenter xqingPresenter;
    private List<DetailsBean.ResultBean.ShortFilmListBean> shortFilmList;
    private YugaoAdapter yugaoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yugao);
        xqingPresenter = new XqingPresenter();
        xqingPresenter.attch(this);
        Intent intent = getIntent();
        int filmid = intent.getIntExtra("id", 0);
        xqingPresenter.infoData(filmid+"");
        infoView();
    }

    private void infoView() {
    //    yg_recyclerView = findViewById(R.id.yugao_recycleView);
    }

    @Override
    public void OnSuccess(DetailsBean data) {
        final DetailsBean.ResultBean result = data.getResult();
        shortFilmList = result.getShortFilmList();
        LinearLayoutManager layoutManager = new LinearLayoutManager(YugaoActivity.this, LinearLayoutManager.VERTICAL, false);
        yg_recyclerView.setLayoutManager(layoutManager);
        yugaoAdapter = new YugaoAdapter(shortFilmList, YugaoActivity.this);
        yg_recyclerView.setAdapter(yugaoAdapter);
    }

    @Override
    public void followsuccess(FollowBean followBean) {

    }

    @Override
    public void deletesuccess(CancelFollowBean cancelFollowBean) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        xqingPresenter.delech();
    }
}
