package com.bw.movie.presenter;

import com.bw.movie.model.LoginModel;
import com.bw.movie.model.bean.LoginBean;
import com.bw.movie.model.bean.WeiXinLoginBean;
import com.bw.movie.model.http.ApiService;
import com.bw.movie.model.http.OkHttpUtil;
import com.bw.movie.view.IView.ILogInView;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends BasePresenter<ILogInView>{

    private final LoginModel loginModel;

    public LoginPresenter() {
        loginModel = new LoginModel();
    }

    public void infoData(Map<String, String> map) {
        Observable<LoginBean> loginBeanObservable = loginModel.InfoData(map);
        loginBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                      iview.onsuccess(loginBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void wxLoginData(String code) {
        ApiService apiService = OkHttpUtil.getInstance().apiService;
        Observable<LoginBean> weiXinLoginBeanObservable = apiService.weChatBindingLogin(code);
        weiXinLoginBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        iview.onsuccess(loginBean);
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
