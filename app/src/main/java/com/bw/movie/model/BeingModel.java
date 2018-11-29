package com.bw.movie.model;

import com.bw.movie.model.bean.BeingBean;
import com.bw.movie.model.http.OkHttpUtil;

import io.reactivex.Observable;

public class BeingModel {
    public Observable<BeingBean> InfoData() {
        Observable<BeingBean> releaseMovieList_uri = OkHttpUtil.getInstance().apiService.findReleaseMovieList_uri(1, 10);
        return   releaseMovieList_uri;
    }
}
