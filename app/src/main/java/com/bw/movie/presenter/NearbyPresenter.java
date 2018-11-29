package com.bw.movie.presenter;

import com.bw.movie.model.NearbyModel;
import com.bw.movie.model.bean.NearbyBean;
import com.bw.movie.view.IView.INearbyView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NearbyPresenter extends BasePresenter<INearbyView> {

    private final NearbyModel nearbyModel;

    public NearbyPresenter() {
        nearbyModel = new NearbyModel();
    }

    public void infoData() {
        Observable<NearbyBean> nearbyBeanObservable = nearbyModel.infoData();
        nearbyBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NearbyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NearbyBean nearbyBean) {
                     iview.OnSuccesss(nearbyBean);
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
