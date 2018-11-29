package com.bw.movie.presenter;

import com.bw.movie.model.FilmModel;
import com.bw.movie.model.bean.FilmBean;
import com.bw.movie.view.IView.IFilmView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FilmPresenter extends BasePresenter<IFilmView>{

    private final FilmModel filmModel;

    public FilmPresenter() {
        filmModel = new FilmModel();
    }

    public void InfoData() {
        Observable<FilmBean> filmBeanObservable = filmModel.InfoData();
        filmBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FilmBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FilmBean filmBean) {
                       iview.OnSuccess(filmBean);

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
