package com.bw.movie.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.model.bean.EvaluationCinemaBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class EvaluationAdaptet extends RecyclerView.Adapter<EvaluationAdaptet.VooView>{
    List<EvaluationCinemaBean.ResultBean> result;
    Context  context;

    public EvaluationAdaptet(List<EvaluationCinemaBean.ResultBean> result, Context context) {
        this.result = result;
        this.context = context;
    }

    @NonNull
    @Override
    public VooView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.evlayout_itme, viewGroup, false);
        VooView vooView = new VooView(view);
        return vooView;
    }

    @Override
    public void onBindViewHolder(@NonNull VooView vooView, int i) {
        String commentHeadPic = result.get(i).getCommentHeadPic();
        vooView.comment_simple.setImageURI(commentHeadPic);
        vooView.user_name.setText(result.get(i).getCommentUserName());
        vooView.review_comment.setText(result.get(i).getCommentContent());
    }

    @Override
    public int getItemCount() {

        return result.size();
    }

    class VooView extends RecyclerView.ViewHolder {

        private final SimpleDraweeView comment_simple;
        private final TextView user_name;
        private final TextView comment_shijian;
        private final TextView zan_shu;
        private final TextView ping_shu;
        private final TextView review_comment;

        public VooView(@NonNull View itemView) {
             super(itemView);
            comment_simple = itemView.findViewById(R.id.p_comment_simple);
            user_name = itemView.findViewById(R.id.p_user_name);
            review_comment = itemView.findViewById(R.id.p_review_comment);
            comment_shijian = itemView.findViewById(R.id.p_comment_shijian);
            zan_shu = itemView.findViewById(R.id.p_zan_shu);
            ping_shu = itemView.findViewById(R.id.p_ping_shu);
         }
     }

}
