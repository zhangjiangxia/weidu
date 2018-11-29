package com.bw.movie.presenter;

import android.util.Log;

import com.bw.movie.model.XqRecommmendModel;
import com.bw.movie.model.bean.XqRecommmendBean;
import com.bw.movie.view.IView.InfoXqRecommmendView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class XqRecommmendPresenter extends BasePresenter<InfoXqRecommmendView>{

    private final XqRecommmendModel xqRecommmendModel;

    public XqRecommmendPresenter() {
        xqRecommmendModel = new XqRecommmendModel();
    }

    public void infoData(String id) {
        Observable<XqRecommmendBean> xqRecommmendBeanObservable = xqRecommmendModel.infoData(id);
        xqRecommmendBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<XqRecommmendBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(XqRecommmendBean xqRecommmendBean) {
                       iview.findCinemalnfo(xqRecommmendBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("sss", "onError: "+e.getMessage() );
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
