package com.bw.movie.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.model.bean.MovieCommentBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class YpAdapter extends RecyclerView.Adapter<YpAdapter.VooHodel> {
    Context context;
    List<MovieCommentBean.ResultBean> result;

    public YpAdapter(Context context, List<MovieCommentBean.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public VooHodel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.yingping_itme, viewGroup, false);
        VooHodel vooHodel = new VooHodel(view);
        return vooHodel;
    }

    @Override
    public void onBindViewHolder(@NonNull VooHodel vooHodel, int i) {
       vooHodel.p_username.setText(result.get(i).getCommentUserName());
        String commentHeadPic = result.get(i).getCommentHeadPic();
        vooHodel.pcommentsimple.setImageURI(commentHeadPic);
        vooHodel.p_review_comment.setText(result.get(i).getCommentContent());
    }

    @Override
    public int getItemCount() {
        return result.size();
    }


    class  VooHodel extends RecyclerView.ViewHolder {
        private CheckBox p_commentcheckzan;
        private TextView p_zanshu;
        private TextView p_pingshu;
        private TextView p_shijian;
        private TextView p_username;
        private TextView p_review_comment;
        private SimpleDraweeView pcommentsimple;
        public VooHodel(@NonNull View itemView) {
            super(itemView);
            p_commentcheckzan = itemView.findViewById(R.id.p_comment_check_zan);
            p_zanshu = itemView.findViewById(R.id.p_zan_shu);
            p_pingshu = itemView.findViewById(R.id.p_ping_shu);
            p_shijian = itemView.findViewById(R.id.p_comment_shijian);
            p_username = itemView.findViewById(R.id.p_user_name);
            p_review_comment =itemView. findViewById(R.id.p_review_comment);
            pcommentsimple = itemView.findViewById(R.id.p_comment_simple);
        }
    }
}
