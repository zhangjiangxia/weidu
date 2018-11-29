package com.bw.movie.presenter;

import android.util.Log;

import com.bw.movie.model.AttentionModel;
import com.bw.movie.model.bean.PageListBean;
import com.bw.movie.view.IView.IPageListView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class AttentionPresenter extends BasePresenter<IPageListView> {

    private final AttentionModel attentionModel;

    public AttentionPresenter() {
        attentionModel = new AttentionModel();
    }

    public void infoData() {
        Observable<PageListBean> pageListBeanObservable = attentionModel.infoData();
        pageListBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PageListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PageListBean pageListBean) {
                     iview.onsuccesspage(pageListBean);

                    }

                    @Override
                    public void onError(Throwable e) {
                        String message = e.getMessage();
                        Log.i("shibai", "onError: "+message);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
