package com.bw.movie.presenter;

import android.util.Log;

import com.bw.movie.model.BuyModel;
import com.bw.movie.model.WeChitModel;
import com.bw.movie.model.bean.BuyBean;
import com.bw.movie.model.bean.WechitBean;
import com.bw.movie.view.IView.IBuyView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BuyPresenter extends BasePresenter<IBuyView>{

    private final BuyModel buyModel;
    private final WeChitModel weChitModel;

    public BuyPresenter() {
        buyModel = new BuyModel();
        weChitModel = new WeChitModel();
    }

    public void infoData(String scheduleId, String amount,String sign1) {
        Observable<BuyBean> buyBeanObservable = buyModel.infoData(scheduleId,amount,sign1);
        buyBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BuyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BuyBean buyBean) {
                      iview.onsuccessBuy(buyBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("error", "onError: "+e.getMessage() );
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void infosData(String orderId) {
        Observable<WechitBean> wechitBeanObservable = weChitModel.infoDate(orderId);
        wechitBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WechitBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WechitBean wechitBean) {
                      iview.onsuccesspay(wechitBean);
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
