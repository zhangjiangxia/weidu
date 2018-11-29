package com.bw.movie.model;

import com.bw.movie.model.bean.BuyBean;
import com.bw.movie.model.http.OkHttpUtil;

import io.reactivex.Observable;

public class BuyModel {

    public Observable<BuyBean> infoData( String scheduleId, String amount,String sign1) {
        Observable<BuyBean> buyBeanObservable = OkHttpUtil.getInstance().apiService.buyMovieTicket(scheduleId,amount,sign1);
        return buyBeanObservable;
    }
}
