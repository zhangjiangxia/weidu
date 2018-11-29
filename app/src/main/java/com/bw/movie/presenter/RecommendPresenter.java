package com.bw.movie.presenter;

import android.util.Log;

import com.bw.movie.model.RecommendModel;
import com.bw.movie.model.bean.RecommendBean;
import com.bw.movie.view.IView.IRecommendView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RecommendPresenter extends BasePresenter<IRecommendView>{

    private final RecommendModel recommendModel;

    public RecommendPresenter() {
        recommendModel = new RecommendModel();
    }

    public void InfoData() {
        Observable<RecommendBean> recommendBeanObservable = recommendModel.InfoData();
        recommendBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RecommendBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RecommendBean recommendBean) {
                        iview.RecommendOnSuccess(recommendBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("sss", "onError: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
