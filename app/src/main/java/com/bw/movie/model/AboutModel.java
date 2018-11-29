package com.bw.movie.model;

import com.bw.movie.model.bean.AboutBean;
import com.bw.movie.model.http.OkHttpUtil;

import io.reactivex.Observable;

public class AboutModel {
    public Observable<AboutBean> InfoDate() {
        Observable<AboutBean> comingSoonMovieList_uri = OkHttpUtil.getInstance().apiService.findComingSoonMovieList_uri(1, 10);
        return   comingSoonMovieList_uri;
    }
}
