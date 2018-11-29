package com.bw.movie.model;

import com.bw.movie.model.bean.WechitBean;
import com.bw.movie.model.http.OkHttpUtil;

import io.reactivex.Observable;

public class WeChitModel {

    public Observable<WechitBean> infoDate(String orderId) {
        Observable<WechitBean> pay = OkHttpUtil.getInstance().apiService.pay(1, orderId);
        return   pay;
    }
}
