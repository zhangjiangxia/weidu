package com.bw.movie.view.IView;

import com.bw.movie.model.bean.RecommendBean;

public interface IRecommendView extends IBaseView{
    void RecommendOnSuccess(RecommendBean data);
}
