package com.bw.movie.view.fragment2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.model.bean.NearbyBean;
import com.bw.movie.presenter.NearbyPresenter;
import com.bw.movie.view.IView.INearbyView;
import com.bw.movie.view.adapter.NearbyAdapter;

import java.util.List;


public class NearbyFragment extends Fragment implements INearbyView {

    private NearbyPresenter nearbyPresenter;
    private RecyclerView nerecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nearby, container, false);
        nerecyclerView = view.findViewById(R.id.nearbyrecyclerView);
        nearbyPresenter = new NearbyPresenter();
        nearbyPresenter.attch(this);
        nearbyPresenter.infoData();
        return view;
    }

    @Override
    public void OnSuccesss(NearbyBean nearbyBean) {
        NearbyBean.ResultBean result = nearbyBean.getResult();
        List<NearbyBean.ResultBean.NearbyCinemaListBean> nearbyCinemaList = result.getNearbyCinemaList();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        nerecyclerView.setLayoutManager(layoutManager);
        NearbyAdapter nearbyAdapter = new NearbyAdapter(nearbyCinemaList,getActivity());
        nerecyclerView.setAdapter(nearbyAdapter);
        nearbyAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        nearbyPresenter.delech();
    }
}
