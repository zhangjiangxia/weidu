package com.bw.movie.model;

import com.bw.movie.model.bean.EvaluationCinemaBean;
import com.bw.movie.model.http.OkHttpUtil;

import io.reactivex.Observable;

public class CommentYypinglunModel {

    public Observable<EvaluationCinemaBean> intoData(int id) {
        Observable<EvaluationCinemaBean> allCinemaComment = OkHttpUtil.getInstance().apiService.findAllCinemaComment(id, 1, 6);
        return allCinemaComment;
    }
}
