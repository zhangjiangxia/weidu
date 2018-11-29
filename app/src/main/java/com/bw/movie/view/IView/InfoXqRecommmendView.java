package com.bw.movie.view.IView;

import com.bw.movie.model.bean.DyAndYyIdBean;
import com.bw.movie.model.bean.EvaluationCinemaBean;
import com.bw.movie.model.bean.ListByCinemaldBean;
import com.bw.movie.model.bean.XqRecommmendBean;

public interface InfoXqRecommmendView extends IBaseView {
    void findCinemalnfo(XqRecommmendBean data);
    void Scheduleosuccess(DyAndYyIdBean dyAndYyIdBean, double fare, String name);
    void ListByCinemaIdsuccess(ListByCinemaldBean  listByCinemaldBean);
    void EvaCinemaCommentsuccess(EvaluationCinemaBean  evaluationCinemaBean);
}
