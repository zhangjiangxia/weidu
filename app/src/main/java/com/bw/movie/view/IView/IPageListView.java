package com.bw.movie.view.IView;

import android.view.View;

import com.bw.movie.model.bean.PageListBean;

public interface IPageListView extends IBaseView {
    void   onsuccesspage(PageListBean  pageListBean);
}
