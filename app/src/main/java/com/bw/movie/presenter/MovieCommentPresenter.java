package com.bw.movie.presenter;

import com.bw.movie.model.MovieCommentModel;
import com.bw.movie.model.bean.MovieCommentBean;
import com.bw.movie.view.IView.IMovieCommentView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieCommentPresenter extends BasePresenter<IMovieCommentView> {

    private final MovieCommentModel movieCommentModel;

    public MovieCommentPresenter() {
        movieCommentModel = new MovieCommentModel();
    }

    public void infoData(String s) {
        Observable<MovieCommentBean> movieCommentBeanObservable = movieCommentModel.infoData(s);
        movieCommentBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieCommentBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieCommentBean movieCommentBean) {
                     iview.OnSuccesscc(movieCommentBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
