package com.bw.movie;

import com.bw.movie.model.bean.DyAndYyIdBean;
import com.bw.movie.model.http.OkHttpUtil;

import io.reactivex.Observable;

public class ScheduleolistModel {

    public Observable<DyAndYyIdBean> infoData(int id, int ccid) {
        Observable<DyAndYyIdBean> movieScheduleList = OkHttpUtil.getInstance().apiService.findMovieScheduleList(id, ccid);
        return movieScheduleList;
    }
}
