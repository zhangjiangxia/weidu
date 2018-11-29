package com.bw.movie.model;

import com.bw.movie.model.bean.LoginBean;
import com.bw.movie.model.http.OkHttpUtil;

import java.util.Map;

import io.reactivex.Observable;

public class LoginModel {
    public Observable<LoginBean> InfoData(Map<String, String> map) {
        Observable<LoginBean> login = OkHttpUtil.getInstance().apiService.login(map);
        return login;
    }
}
