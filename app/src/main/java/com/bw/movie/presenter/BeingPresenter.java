package com.bw.movie.presenter;

import com.bw.movie.model.BeingModel;
import com.bw.movie.model.bean.BeingBean;
import com.bw.movie.view.IView.IFilmView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BeingPresenter extends BasePresenter<IFilmView>{

    private final BeingModel beingModel;

    public BeingPresenter() {
        beingModel = new BeingModel();
    }

    public void InfoData() {
        Observable<BeingBean> beingBeanObservable = beingModel.InfoData();
        beingBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BeingBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BeingBean beingBean) {
                     iview.beingSuccess(beingBean);
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
