package com.bw.movie.model;

import android.widget.RadioGroup;

import com.bw.movie.model.bean.RegBean;
import com.bw.movie.model.http.OkHttpUtil;

import java.util.Map;

import io.reactivex.Observable;

public class RagModel {
    public Observable<RegBean> infoData(Map<String, String> map) {
        Observable<RegBean> regBeanObservable = OkHttpUtil.getInstance().apiService.registerUser(map);
        return regBeanObservable;

    }
}
