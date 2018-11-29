package com.bw.movie.presenter;

import com.bw.movie.model.ShowcancelFollowModel;
import com.bw.movie.model.ShowfollowModel;
import com.bw.movie.model.XqingModel;
import com.bw.movie.model.bean.CancelFollowBean;
import com.bw.movie.model.bean.DetailsBean;
import com.bw.movie.model.bean.FollowBean;
import com.bw.movie.view.IView.IDetailsView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class XqingPresenter extends BasePresenter<IDetailsView> {

    private final XqingModel xqingModel;
    private final ShowfollowModel showfollowModel;
    private final ShowcancelFollowModel showcancelFollowModel;

    public XqingPresenter() {
        xqingModel = new XqingModel();
        showfollowModel = new ShowfollowModel();
        showcancelFollowModel = new ShowcancelFollowModel();
    }
    public void infoData(String s) {
        Observable<DetailsBean> detailsBeanObservable = xqingModel.infoData(s);
        detailsBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetailsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DetailsBean detailsBean) {
                      iview.OnSuccess(detailsBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

//关注电影
    public void showfollowMovie(int id) {
        Observable<FollowBean> followBeanObservable = showfollowModel.infoData(id);
        followBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FollowBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FollowBean followBean) {
                     iview.followsuccess(followBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
//取消关注
    public void showcancelFollowMovie(int id) {
        Observable<CancelFollowBean> cancelFollowBeanObservable = showcancelFollowModel.infoData(id);
        cancelFollowBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CancelFollowBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CancelFollowBean cancelFollowBean) {
                     iview.deletesuccess(cancelFollowBean);
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
