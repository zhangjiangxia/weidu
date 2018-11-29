package com.bw.movie.model;

import com.bw.movie.model.bean.DetailsBean;
import com.bw.movie.model.http.OkHttpUtil;

import io.reactivex.Observable;

public class XqingModel {
    public Observable<DetailsBean> infoData(String s) {
        Observable<DetailsBean> movield = OkHttpUtil.getInstance().apiService.findMoviesById_uri(s);
        return movield;
    }
}
