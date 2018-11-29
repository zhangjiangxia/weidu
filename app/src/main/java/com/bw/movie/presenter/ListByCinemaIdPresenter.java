package com.bw.movie.presenter;

import android.util.Log;

import com.bw.movie.ScheduleolistModel;
import com.bw.movie.model.CommentYypinglunModel;
import com.bw.movie.model.ListByCinemaIdModel;
import com.bw.movie.model.bean.DyAndYyIdBean;
import com.bw.movie.model.bean.EvaluationCinemaBean;
import com.bw.movie.model.bean.ListByCinemaldBean;
import com.bw.movie.view.IView.InfoXqRecommmendView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ListByCinemaIdPresenter extends BasePresenter<InfoXqRecommmendView> {


    private final ListByCinemaIdModel listByCinemaIdModel;
    private final ScheduleolistModel scheduleolistModel;
    private final CommentYypinglunModel commentYypinglunModel;

    public ListByCinemaIdPresenter() {
        listByCinemaIdModel = new ListByCinemaIdModel();
        scheduleolistModel = new ScheduleolistModel();
        commentYypinglunModel = new CommentYypinglunModel();
    }

    public void infoData(int s) {
       Observable<ListByCinemaldBean> listByCinemaldBeanObservable = listByCinemaIdModel.infoData(s);
                 listByCinemaldBeanObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListByCinemaldBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ListByCinemaldBean listByCinemaldBean) {
                        iview.ListByCinemaIdsuccess(listByCinemaldBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("ee", "onError: "+e.getMessage().toString());
                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void infoDataScheduleo(int id, int ccid, final double fare, final String name) {
        Observable<DyAndYyIdBean> dyAndYyIdBeanObservable = scheduleolistModel.infoData(id, ccid);
        dyAndYyIdBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DyAndYyIdBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DyAndYyIdBean dyAndYyIdBean) {
                        iview.Scheduleosuccess(dyAndYyIdBean,fare,name);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });



    }

    public void infoDataPingjia(int id) {
        Observable<EvaluationCinemaBean> evaluationCinemaBeanObservable = commentYypinglunModel.intoData(id);
        evaluationCinemaBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EvaluationCinemaBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(EvaluationCinemaBean evaluationCinemaBean) {
                         iview.EvaCinemaCommentsuccess(evaluationCinemaBean);
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
