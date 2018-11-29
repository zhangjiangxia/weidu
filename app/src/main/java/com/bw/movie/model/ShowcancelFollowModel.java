package com.bw.movie.model;

import com.bw.movie.model.bean.CancelFollowBean;
import com.bw.movie.model.http.OkHttpUtil;

import io.reactivex.Observable;

public class ShowcancelFollowModel {
    public Observable<CancelFollowBean> infoData(int id) {
        Observable<CancelFollowBean> cancelFollowBeanObservable = OkHttpUtil.getInstance().apiService.cancelFollowMovie(id);
        return cancelFollowBeanObservable;
    }
}
