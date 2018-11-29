package com.bw.movie.view.IView;

import com.bw.movie.model.bean.RegBean;

public interface IRegView extends IBaseView {
    void onsuccess(RegBean regBean);

    void onerror(String s);
}


