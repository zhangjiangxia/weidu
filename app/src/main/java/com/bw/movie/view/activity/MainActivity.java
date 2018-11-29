package com.bw.movie.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_xbanner)
    XBanner mainXbanner;
    @BindView(R.id.main_image)
    ImageView mainImage;
    private ArrayList<Integer> imageViews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        imageViews = new ArrayList<>();
        imageViews.add(R.mipmap.one);
        imageViews.add(R.mipmap.two);
        imageViews.add(R.mipmap.three);
        imageViews.add(R.mipmap.foue);
        mainXbanner.setData(imageViews, null);
        mainXbanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(MainActivity.this)
                        .load(imageViews.get(position))
                        .into((ImageView) view);
                if (position == 3) {
                    mainImage.setVisibility(View.VISIBLE);
                    Glide.with(MainActivity.this)
                            .load(imageViews.get(position))
                            .into((ImageView) view);
                }
            }

        });
        mainXbanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
           //   Glide.with(MainActivity.this).load(imageViews.get(0)).into(mainImage);
            }
            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @OnClick({ R.id.main_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_image:
                Intent  intent=new Intent(MainActivity.this,ShouActivity.class);
                startActivity(intent);
                break;
        }
    }
}
