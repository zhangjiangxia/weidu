package com.bw.movie.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.model.bean.MovieCommentBean;
import com.bw.movie.presenter.MovieCommentPresenter;
import com.bw.movie.view.IView.IMovieCommentView;
import com.bw.movie.view.adapter.YpAdapter;

import java.util.List;

public class YingPingActivity extends AppCompatActivity implements IMovieCommentView{

    private MovieCommentPresenter movieCommentPresenter;

    private RecyclerView yp_recycleview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ying_ping);
        movieCommentPresenter = new MovieCommentPresenter();
       movieCommentPresenter.attch(this);
        Intent intent = getIntent();
        int filmid = intent.getIntExtra("id", 0);
        movieCommentPresenter.infoData(filmid+"");
        infoview();
    }


    private void infoview() {
        yp_recycleview = findViewById(R.id.yp_recycleview);
    }

    @Override
    public void OnSuccesscc(MovieCommentBean data) {
        List<MovieCommentBean.ResultBean> result = data.getResult();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(YingPingActivity.this, LinearLayoutManager.VERTICAL, false);
        yp_recycleview.setLayoutManager(linearLayoutManager);
        YpAdapter ypAdapter= new YpAdapter(YingPingActivity.this,result);
        yp_recycleview.setAdapter(ypAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        movieCommentPresenter.delech();
    }
}
