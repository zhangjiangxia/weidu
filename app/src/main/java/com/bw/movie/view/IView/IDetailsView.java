package com.bw.movie.view.IView;

import com.bw.movie.model.bean.CancelFollowBean;
import com.bw.movie.model.bean.DetailsBean;
import com.bw.movie.model.bean.FollowBean;

public interface IDetailsView extends IBaseView{
    void OnSuccess(DetailsBean data);
    void followsuccess(FollowBean  followBean);
    void deletesuccess(CancelFollowBean cancelFollowBean);
}
