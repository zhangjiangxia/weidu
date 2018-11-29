package com.bw.movie.presenter;

import com.bw.movie.model.RagModel;
import com.bw.movie.model.bean.RegBean;
import com.bw.movie.view.IView.IRegView;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RagPresenter extends BasePresenter<IRegView>{

    private final RagModel ragModel;

    public RagPresenter() {
        ragModel = new RagModel();
    }

    public void infoData(Map<String, String> map) {
        Observable<RegBean> regBeanObservable = ragModel.infoData(map);
          regBeanObservable.subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread()).
           subscribe(new Observer<RegBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(RegBean regBean) {
                iview.onsuccess(regBean);
            }

            @Override
            public void onError(Throwable e) {
               iview.onerror(e.getMessage().toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
