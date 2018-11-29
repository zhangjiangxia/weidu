package com.bw.movie.model;

import com.bw.movie.model.bean.NearbyBean;
import com.bw.movie.model.http.OkHttpUtil;

import io.reactivex.Observable;

public class NearbyModel {
    public Observable<NearbyBean> infoData() {
        Observable<NearbyBean> nearbyBeanObservable = OkHttpUtil.getInstance().apiService.findnearbyCinemas("121.49237440321181", "31.24718560112847", 1, 8);
        return nearbyBeanObservable;
    }
}