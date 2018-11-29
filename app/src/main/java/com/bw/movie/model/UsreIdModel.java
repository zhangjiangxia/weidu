package com.bw.movie.model;

import com.bw.movie.model.bean.EvaluationCinemaBean;
import com.bw.movie.model.bean.UsreIdBean;
import com.bw.movie.model.http.OkHttpUtil;

import io.reactivex.Observable;

public class UsreIdModel {
    public Observable<UsreIdBean> infoData(String userId, String sessionId) {
        Observable<UsreIdBean> userInfoByUserId = OkHttpUtil.getInstance().apiService.getUserInfoByUserId(userId, sessionId);
        return userInfoByUserId;
    }
}
