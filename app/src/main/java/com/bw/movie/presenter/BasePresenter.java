package com.bw.movie.presenter;

import com.bw.movie.view.IView.IBaseView;

public class BasePresenter<V extends IBaseView> {
    V iview;
    public void attch(V  iView){
        this.iview=iView;
    }
    public void delech(){
        this.iview=null;
    }
    public V getiView(){
        return iview;
    }

}
