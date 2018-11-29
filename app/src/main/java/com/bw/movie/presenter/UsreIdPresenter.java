package com.bw.movie.presenter;

import android.util.Log;

import com.bw.movie.model.UsreIdModel;
import com.bw.movie.model.bean.UsreIdBean;
import com.bw.movie.view.IView.IUserIdView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UsreIdPresenter extends BasePresenter<IUserIdView>{

    private final UsreIdModel usreIdModel;

    public UsreIdPresenter() {
        usreIdModel = new UsreIdModel();
    }

    public void infoData(String userId, String sessionId) {
        Observable<UsreIdBean> usreIdBeanObservable = usreIdModel.infoData(userId, sessionId);
        usreIdBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UsreIdBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UsreIdBean usreIdBean) {
                     iview.onsuccess(usreIdBean);
                        Log.i("nn", "onNext: "+usreIdBean.getResult().getNickName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("vv", "onError: "+e.getMessage().toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
