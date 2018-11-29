package com.bw.movie.model;

import com.bw.movie.model.bean.DyAndYyIdBean;
import com.bw.movie.model.bean.ListByCinemaldBean;
import com.bw.movie.model.http.OkHttpUtil;

import io.reactivex.Observable;

public class ListByCinemaIdModel {
    public static Observable<ListByCinemaldBean> infoData(int s) {
        Observable<ListByCinemaldBean> movieListByCinemaId = OkHttpUtil.getInstance().apiService.findMovieListByCinemaId(s);
        return  movieListByCinemaId;
    }
}
