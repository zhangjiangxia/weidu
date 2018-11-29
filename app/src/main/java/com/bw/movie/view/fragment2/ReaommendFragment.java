package com.bw.movie.view.fragment2;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.model.bean.RecommendBean;
import com.bw.movie.presenter.RecommendPresenter;
import com.bw.movie.view.IView.IRecommendView;
import com.bw.movie.view.activity.XqrecommendActivity;
import com.bw.movie.view.adapter.FilmAdapterOuter;
import com.bw.movie.view.adapter.RecommendAdapter;

import java.util.List;

public class ReaommendFragment extends Fragment implements IRecommendView{
    private RecyclerView recommendrecyclerView;
    private RecommendPresenter recommendPresenter;
    private LinearLayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View  view = inflater.inflate(R.layout.fragment_reaommend, container, false);
      recommendrecyclerView = view.findViewById(R.id.recommend_recyclerView);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recommendPresenter = new RecommendPresenter();
        recommendPresenter.attch(this);
        recommendPresenter.InfoData();
    }

    @Override
    public void RecommendOnSuccess(RecommendBean data) {
        RecommendBean.ResultBean result = data.getResult();
        final List<RecommendBean.ResultBean.NearbyCinemaListBean> nearbyCinemaList = result.getNearbyCinemaList();
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recommendrecyclerView.setLayoutManager(layoutManager);
        RecommendAdapter recommendAdapter=new RecommendAdapter(nearbyCinemaList, getActivity());
        recommendrecyclerView.setAdapter(recommendAdapter);
        recommendAdapter.notifyDataSetChanged();
        recommendAdapter.setOnclickListener(new FilmAdapterOuter.ItemClickListener() {
            private int id;
            @Override
            public void onMyItemClick(int po) {
                id = nearbyCinemaList.get(po).getId();
                Log.i("tac", "onMyItemClick: "+id);
                Intent intent = new Intent(getActivity(), XqrecommendActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        recommendPresenter.delech();
    }



}
