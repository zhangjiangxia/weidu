package com.bw.movie.model;

import com.bw.movie.model.bean.FollowBean;
import com.bw.movie.model.http.OkHttpUtil;

import io.reactivex.Observable;

public class ShowfollowModel {
    public Observable<FollowBean> infoData(int id) {
        Observable<FollowBean> followBeanObservable = OkHttpUtil.getInstance().apiService.followMovie(id);
        return  followBeanObservable;
    }
}
