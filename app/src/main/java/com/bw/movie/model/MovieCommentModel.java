package com.bw.movie.model;

import com.bw.movie.model.bean.MovieCommentBean;
import com.bw.movie.model.http.OkHttpUtil;

import io.reactivex.Observable;

public class MovieCommentModel {
    public Observable<MovieCommentBean> infoData(String s){
        Observable<MovieCommentBean> allMovieComment = OkHttpUtil.getInstance().apiService.findAllMovieComment(s, 1, 5);
        return allMovieComment;
    }
}
