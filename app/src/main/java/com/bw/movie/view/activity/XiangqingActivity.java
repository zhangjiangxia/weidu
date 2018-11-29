package com.bw.movie.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.model.bean.CancelFollowBean;
import com.bw.movie.model.bean.DetailsBean;
import com.bw.movie.model.bean.FollowBean;
import com.bw.movie.presenter.XqingPresenter;
import com.bw.movie.view.IView.IDetailsView;

public class XiangqingActivity extends AppCompatActivity implements IDetailsView{

    private XqingPresenter xqingPresenter;
    private TextView detailstext;
    private TextView detailsdirector;
    private TextView detailsduration;
    private ImageView detailsimage;
    private TextView detailstype;
    private TextView detailssynopsis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        xqingPresenter = new XqingPresenter();
        xqingPresenter.attch(this);
        Intent intent = getIntent();
        int filmid = intent.getIntExtra("id", 0);
        xqingPresenter.infoData(filmid+"");
        infoView();
    }
    private void infoView() {
        detailstext = findViewById(R.id.details_text);
        detailsdirector = findViewById(R.id.details_director);
        detailsduration = findViewById(R.id.details_duration);
        detailsimage = findViewById(R.id.details_image);
        detailstype = findViewById(R.id.details_type);
        detailssynopsis = findViewById(R.id.details_synopsis);
    }

    @Override
    public void OnSuccess(DetailsBean data) {
        DetailsBean.ResultBean result = data.getResult();
        String name = result.getName();
        String director = result.getDirector();
        String duration = result.getDuration();

        String movieTypes = result.getMovieTypes();
        String imageUrl = result.getImageUrl();
        String summary = result.getSummary();
        String starring = result.getStarring();
        String[] split = starring.split(",");

        detailstext.setText(name);
        detailsdirector.setText(director);
        detailsduration.setText(duration);
        detailstype.setText(movieTypes);
        Glide.with(this).load(imageUrl).into(detailsimage);
        detailssynopsis.setText(summary);
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
