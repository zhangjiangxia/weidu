package com.bw.movie.presenter;

import android.util.Log;

import com.bw.movie.model.BuyRecordModel;
import com.bw.movie.model.bean.BuyRecordBean;
import com.bw.movie.view.IView.IBuyRecordView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BuyRecordPresenter extends BasePresenter<IBuyRecordView>{

    private final BuyRecordModel buyRecordModel;

    public BuyRecordPresenter() {
        buyRecordModel = new BuyRecordModel();
    }

    public void infodata() {
        Observable<BuyRecordBean> buyRecordBeanObservable = buyRecordModel.infoData();
        buyRecordBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BuyRecordBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BuyRecordBean buyRecordBean) {
                        iview.onsuccess(buyRecordBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        String message = e.getMessage();
                        Log.i("errors", "onError: "+message);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
