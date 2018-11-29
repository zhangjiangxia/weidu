package com.bw.movie.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bw.movie.R;
import com.bw.movie.model.bean.AboutBean;
import com.bw.movie.model.bean.BeingBean;
import com.bw.movie.model.bean.FilmBean;
import com.bw.movie.presenter.AboutPresenter;
import com.bw.movie.presenter.BeingPresenter;
import com.bw.movie.presenter.FilmPresenter;
import com.bw.movie.view.IView.IFilmView;
import com.bw.movie.view.activity.DetailsActivity;
import com.bw.movie.view.activity.Hot_Being_About_Activity;
import com.bw.movie.view.adapter.AboutAdapterOuter;
import com.bw.movie.view.adapter.BeingAdapterOuter;
import com.bw.movie.view.adapter.FilmAdapterOuter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import recycler.coverflow.RecyclerCoverFlow;

public class FilmFragment extends Fragment implements IFilmView {


    Unbinder unbinder;
    @BindView(R.id.hot_imag1)
    ImageView hotImag1;
    @BindView(R.id.film_recycler)
    RecyclerView filmRecycler;
    @BindView(R.id.being_recycler)
    RecyclerView beingRecycler;
    @BindView(R.id.adout_recycler)
    RecyclerView adoutRecycler;
    @BindView(R.id.film_hot_click)
    LinearLayout filmHotClick;
    @BindView(R.id.film_being_click)
    LinearLayout filmBeingClick;
    @BindView(R.id.film_about_click)
    LinearLayout filmAboutClick;
    private FilmPresenter filmPresenter;
    private BeingPresenter beingPresenter;
    private AboutPresenter aboutPresenter;
    private RecyclerCoverFlow recyclerCoverFlow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_film, container, false);
        initView(view);
        filmPresenter = new FilmPresenter();
        filmPresenter.attch(this);
        filmPresenter.InfoData();

        beingPresenter = new BeingPresenter();
        beingPresenter.attch(this);
        beingPresenter.InfoData();

        aboutPresenter = new AboutPresenter();
        aboutPresenter.attch(this);
        aboutPresenter.InfoData();

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void initView(View view) {
        recyclerCoverFlow = view.findViewById(R.id.list);

    }

    @Override
    public void OnSuccess(FilmBean data) {
        final List<FilmBean.ResultBean> result = data.getResult();

        //热播影片展示

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        filmRecycler.setLayoutManager(layoutManager);
        FilmAdapterOuter filmAdapterOuter = new FilmAdapterOuter(result, getActivity());
        filmRecycler.setAdapter(filmAdapterOuter);
        filmAdapterOuter.setOnclickListener(new FilmAdapterOuter.ItemClickListener() {
            private int filmid;
            @Override
            public void onMyItemClick(int po) {
                filmid = result.get(po).getId();
                Intent intent=new Intent(getActivity(),DetailsActivity.class);
                intent.putExtra("filmid",filmid);
                startActivity(intent);
            }
        });
    }
    @Override
    public void beingSuccess(BeingBean data) {
        final List<BeingBean.ResultBean> result = data.getResult();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        beingRecycler.setLayoutManager(layoutManager);
        BeingAdapterOuter filmAdapterOuter1 = new BeingAdapterOuter(getActivity(), result);
        beingRecycler.setAdapter(filmAdapterOuter1);
        filmAdapterOuter1.setOnclickListener(new FilmAdapterOuter.ItemClickListener() {
            private int filmid;
            @Override
            public void onMyItemClick(int po) {
                filmid = result.get(po).getId();
                Intent intent=new Intent(getActivity(),DetailsActivity.class);
                intent.putExtra("filmid",filmid);
                startActivity(intent);
            }
        });
        recyclerCoverFlow.setAdapter(filmAdapterOuter1);

    }

    @Override

    public void aboutSuccess(AboutBean data) {
        final List<AboutBean.ResultBean> result = data.getResult();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        adoutRecycler.setLayoutManager(layoutManager);
        AboutAdapterOuter aboutAdapterOuter = new AboutAdapterOuter(result, getActivity());
        adoutRecycler.setAdapter(aboutAdapterOuter);
        aboutAdapterOuter.setOnclickListener(new FilmAdapterOuter.ItemClickListener() {
            private int filmid;
            @Override
            public void onMyItemClick(int po) {
                filmid = result.get(po).getId();
                Intent intent=new Intent(getActivity(),DetailsActivity.class);
                intent.putExtra("filmid",filmid);
                startActivity(intent);
            }
        });
    }

    @Override
    public void OnErr(String errMsg) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        filmPresenter.delech();
        aboutPresenter.delech();
        beingPresenter.delech();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.film_hot_click, R.id.film_being_click, R.id.film_about_click})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.film_hot_click:
                Intent  hotintent=new Intent(getActivity(), Hot_Being_About_Activity.class);
                startActivity(hotintent);
                break;
            case R.id.film_being_click:
                Intent  beingintent=new Intent(getActivity(), Hot_Being_About_Activity.class);
                startActivity(beingintent);
                break;
            case R.id.film_about_click:
                Intent  aboutintent=new Intent(getActivity(), Hot_Being_About_Activity.class);
                startActivity(aboutintent);
                break;
        }
    }
}
