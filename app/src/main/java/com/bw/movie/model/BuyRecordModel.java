package com.bw.movie.model;

import com.bw.movie.model.bean.BuyRecordBean;
import com.bw.movie.model.http.OkHttpUtil;

import io.reactivex.Observable;

public class BuyRecordModel {
    public Observable<BuyRecordBean> infoData() {
        Observable<BuyRecordBean> userBuyTicketRecordList = OkHttpUtil.getInstance().apiService.findUserBuyTicketRecordList(1, 5);
        return userBuyTicketRecordList;
    }
}
