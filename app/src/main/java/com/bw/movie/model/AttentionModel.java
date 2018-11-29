package com.bw.movie.model;

import com.bw.movie.model.bean.PageListBean;
import com.bw.movie.model.http.OkHttpUtil;

import io.reactivex.Observable;

public class AttentionModel {

    public Observable<PageListBean> infoData() {
        Observable<PageListBean> moviePageList = OkHttpUtil.getInstance().apiService.findMoviePageList(1,10);
        return moviePageList;
    }
}
