package com.bw.movie.presenter;

import android.util.Log;

import com.bw.movie.model.AboutModel;
import com.bw.movie.model.bean.AboutBean;
import com.bw.movie.view.IView.IFilmView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AboutPresenter extends BasePresenter<IFilmView>{

    private final AboutModel aboutModel;

    public AboutPresenter() {
        aboutModel = new AboutModel();
    }

    public void InfoData() {
        Observable<AboutBean> aboutBeanObservable = aboutModel.InfoDate();
        aboutBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AboutBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(AboutBean aboutBean) {
                    iview.aboutSuccess(aboutBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("sssss", "onError: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
