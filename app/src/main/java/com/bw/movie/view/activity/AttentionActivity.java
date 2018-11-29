package com.bw.movie.view.activity;

import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.model.bean.PageListBean;
import com.bw.movie.presenter.AttentionPresenter;
import com.bw.movie.view.IView.IPageListView;
import com.bw.movie.view.adapter.AttFragmentPageAdapte;
import com.bw.movie.view.fragment4.YingpianFragment;
import com.bw.movie.view.fragment4.YingyuanFragment;

import java.util.ArrayList;
import java.util.List;

public class AttentionActivity extends AppCompatActivity {

    private AttentionPresenter attentionPresenter;


    private TabLayout atttablayout;
    private ViewPager attviewpager;
    List<Fragment> fragments = new ArrayList<Fragment>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention);
        atttablayout = findViewById(R.id.attention_tablayout);
        attviewpager = findViewById(R.id.attention_viewpager);
        fragments.add(new YingpianFragment());
        fragments.add(new YingyuanFragment());
        AttFragmentPageAdapte pageAdapter = new AttFragmentPageAdapte(getSupportFragmentManager(), fragments);
        attviewpager.setAdapter(pageAdapter);
        atttablayout.setupWithViewPager(attviewpager);


    }




}
