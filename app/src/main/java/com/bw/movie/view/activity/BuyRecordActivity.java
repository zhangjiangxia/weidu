package com.bw.movie.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.model.bean.BuyBean;
import com.bw.movie.model.bean.BuyRecordBean;
import com.bw.movie.model.bean.WechitBean;
import com.bw.movie.presenter.BuyRecordPresenter;
import com.bw.movie.view.IView.IBuyRecordView;
import com.bw.movie.view.IView.IBuyView;
import com.bw.movie.view.adapter.AttentionAdapter;
import com.bw.movie.view.adapter.BuyRecordAdapter;

import java.util.List;

public class BuyRecordActivity extends AppCompatActivity implements IBuyRecordView{

    private RecyclerView buyrecycelview;
    private BuyRecordPresenter buyRecordPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_record);
        buyrecycelview = findViewById(R.id.buy_recycelview);

        buyRecordPresenter = new BuyRecordPresenter();
        buyRecordPresenter.attch(this);
        buyRecordPresenter.infodata();
    }


    @Override
    public void onsuccess(BuyRecordBean buyRecordBean) {
        String message = buyRecordBean.getMessage();
        Toast.makeText(this, "查询购票"+message, Toast.LENGTH_SHORT).show();
        List<BuyRecordBean.ResultBean> result = buyRecordBean.getResult();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BuyRecordActivity.this, LinearLayoutManager.VERTICAL, false);
        buyrecycelview.setLayoutManager(linearLayoutManager);
        BuyRecordAdapter buyRecordAdapter = new BuyRecordAdapter(result, BuyRecordActivity.this);
        buyrecycelview.setAdapter(buyRecordAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        buyRecordPresenter.delech();
    }
}
