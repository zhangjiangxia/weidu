package com.bw.movie.view.fragment1;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.model.bean.AboutBean;
import com.bw.movie.model.bean.BeingBean;
import com.bw.movie.model.bean.FilmBean;
import com.bw.movie.presenter.BeingPresenter;
import com.bw.movie.view.IView.IFilmView;
import com.bw.movie.view.activity.DetailsActivity;
import com.bw.movie.view.adapter.BeingAdapterInside;
import com.bw.movie.view.adapter.FilmAdapterOuter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class BeingFragment extends Fragment implements IFilmView {

    @BindView(R.id.beinginside_recycleView)
    RecyclerView beinginsideRecycleView;
    Unbinder unbinder;
    private BeingPresenter beingPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_being, container, false);
        unbinder = ButterKnife.bind(this, view);
        beingPresenter = new BeingPresenter();
        beingPresenter.attch(this);
        beingPresenter.InfoData();
        return view;
    }

    @Override
    public void OnSuccess(FilmBean data) {

    }

    @Override
    public void beingSuccess(BeingBean data) {
        final List<BeingBean.ResultBean> result = data.getResult();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        beinginsideRecycleView.setLayoutManager(layoutManager);
        BeingAdapterInside beingAdapterInside=new BeingAdapterInside(getActivity(), result);
        beinginsideRecycleView.setAdapter(beingAdapterInside);
        beingAdapterInside.setOnclickListener(new FilmAdapterOuter.ItemClickListener() {
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
    public void aboutSuccess(AboutBean data) {

    }

    @Override
    public void OnErr(String errMsg) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        beingPresenter.delech();
    }
}
