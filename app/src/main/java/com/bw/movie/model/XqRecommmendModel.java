package com.bw.movie.model;

import com.bw.movie.model.bean.XqRecommmendBean;
import com.bw.movie.model.http.OkHttpUtil;

import io.reactivex.Observable;
import retrofit2.http.HTTP;

public class XqRecommmendModel {
    public Observable<XqRecommmendBean> infoData(String id) {
        Observable<XqRecommmendBean> cinemaInfo = OkHttpUtil.getInstance().apiService.findCinemaInfo(id);
        return cinemaInfo;
    }
}
