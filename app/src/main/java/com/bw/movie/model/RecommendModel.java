package com.bw.movie.model;


import com.bw.movie.model.bean.RecommendBean;
import com.bw.movie.model.http.OkHttpUtil;

import io.reactivex.Observable;

public class RecommendModel {

    public Observable<RecommendBean> InfoData() {
        Observable<RecommendBean> recommendCinemas = OkHttpUtil.getInstance().apiService.findRecommendCinemas("116.30551391385724", "40.04571807462411", 1, 10);
        return recommendCinemas;
    }
}
