package com.bw.movie.view.fragment4;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.model.bean.PageListBean;
import com.bw.movie.presenter.AttentionPresenter;
import com.bw.movie.view.IView.IPageListView;
import com.bw.movie.view.activity.LoginUserActivity;
import com.bw.movie.view.adapter.AttentionAdapter;

import java.util.List;


public class YingpianFragment extends Fragment implements IPageListView{

    private AttentionPresenter attentionPresenter;
    private RecyclerView attentionrecyclevew;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_yingpian, container, false);
        attentionrecyclevew = view.findViewById(R.id.att_recylerview);
        attentionPresenter = new AttentionPresenter();
        attentionPresenter.attch(this);
        attentionPresenter.infoData();
        return view;
    }

    @Override
    public void onsuccesspage(PageListBean pageListBean) {
        String message = pageListBean.getMessage();
        Toast.makeText(getActivity(), "成功"+message, Toast.LENGTH_SHORT).show();
        List<PageListBean.ResultBean> result = pageListBean.getResult();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        attentionrecyclevew.setLayoutManager(linearLayoutManager);
        AttentionAdapter attentionAdapter = new AttentionAdapter(result, getActivity());
        attentionrecyclevew.setAdapter(attentionAdapter);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        attentionPresenter.delech();
    }
}
