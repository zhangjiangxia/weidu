package com.bw.movie.model;

import com.bw.movie.model.bean.FilmBean;
import com.bw.movie.model.http.OkHttpUtil;

import io.reactivex.Observable;

public class FilmModel {
    public Observable<FilmBean>InfoData() {
        Observable<FilmBean> hotMovieList_uri = OkHttpUtil.getInstance().apiService.findHotMovieList_uri(1, 10);
        return hotMovieList_uri;
    }
}
