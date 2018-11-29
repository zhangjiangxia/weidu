package com.bw.movie.view.IView;

import com.bw.movie.model.bean.AboutBean;
import com.bw.movie.model.bean.BeingBean;
import com.bw.movie.model.bean.FilmBean;

public interface IFilmView extends IBaseView {
    //热门影片
    void OnSuccess(FilmBean data);
    //正在上映
    void beingSuccess(BeingBean data);
    //即将上映
    void aboutSuccess(AboutBean data);
    void OnErr(String errMsg);

}
