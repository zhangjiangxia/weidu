package com.bw.movie.view.activity;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.view.adapter.MyFragmentPageAdapte;
import com.bw.movie.view.fragment1.AboutFragment;
import com.bw.movie.view.fragment1.BeingFragment;
import com.bw.movie.view.fragment1.HotFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Hot_Being_About_Activity extends AppCompatActivity {

    @BindView(R.id.hba_location)
    TextView hbaLocation;
    @BindView(R.id.hba_search_imag)
    ImageView hbaSearchImag;
    @BindView(R.id.hba_search_edit)
    EditText hbaSearchEdit;
    @BindView(R.id.hba_animation)
    LinearLayout hbaAnimation;
    List<Fragment> fragments = new ArrayList<Fragment>();
    @BindView(R.id.hba_tablayout)
    TabLayout hbaTablayout;
    @BindView(R.id.hba_viewpager)
    ViewPager hbaViewpager;
    private TextView hbalocation;
    private EditText hbasearchedit;
    private ImageView hbasearchimag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot__being__about_);
        ButterKnife.bind(this);
        infotblayout();
    }

    private void infotblayout() {
        fragments.add(new HotFragment());
        fragments.add(new BeingFragment());
        fragments.add(new AboutFragment());
        MyFragmentPageAdapte pageAdapter = new MyFragmentPageAdapte(getSupportFragmentManager(), fragments);
        hbaViewpager.setAdapter(pageAdapter);
        hbaTablayout.setupWithViewPager(hbaViewpager);
        infoView();
    }

    private void infoView() {
        hbalocation = findViewById(R.id.hba_location);
        hbasearchedit = findViewById(R.id.hba_search_edit);
        hbasearchimag = findViewById(R.id.hba_search_imag);
    }


    @OnClick({R.id.hba_location, R.id.hba_search_imag, R.id.hba_animation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.hba_location:

                break;
            case R.id.hba_search_imag:
                String s = hbaSearchEdit.getText().toString();
                break;
            case R.id.hba_animation:
                if (view == hbaAnimation) {
                }
                break;
        }
    }
}
