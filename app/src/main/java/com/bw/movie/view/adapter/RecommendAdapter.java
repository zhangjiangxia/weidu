package com.bw.movie.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.model.bean.RecommendBean;

import java.util.List;

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.VooHodel> {
    List<RecommendBean.ResultBean.NearbyCinemaListBean> nearbyCinemaList;
    Context context;
    //接口回调
    FilmAdapterOuter.ItemClickListener onclickListener;

    public interface ItemClickListener{
        void onMyItemClick(int  po);
    }

    public void setOnclickListener(FilmAdapterOuter.ItemClickListener onclickListener) {
        this.onclickListener = onclickListener;
    }
    public RecommendAdapter(List<RecommendBean.ResultBean.NearbyCinemaListBean> nearbyCinemaList, Context context) {
        this.nearbyCinemaList = nearbyCinemaList;
        this.context = context;
    }


    @NonNull
    @Override
    public VooHodel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recommend_itme, viewGroup, false);
        VooHodel vooHodel = new VooHodel(view);
        return vooHodel;
    }

    @Override
    public void onBindViewHolder(@NonNull VooHodel vooHodel, int i) {
        vooHodel.recommendname.setText(nearbyCinemaList.get(i).getName());
        vooHodel.recommendcontent.setText(nearbyCinemaList.get(i).getAddress());
        vooHodel.recommenddistance.setText(nearbyCinemaList.get(i).getDistance()+"m");
        String logo = nearbyCinemaList.get(i).getLogo();
        Glide.with(context).load(logo).into(vooHodel.recommendimage);
    }

    @Override
    public int getItemCount() {
        return nearbyCinemaList.size();
    }

    class VooHodel extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView recommendname;
        private final ImageView recommendimage;
        private final TextView recommendcontent;
        private final TextView recommenddistance;

        public VooHodel(@NonNull View itemView) {
            super(itemView);
            recommendname = itemView.findViewById(R.id.recommend_name);
            recommendcontent = itemView.findViewById(R.id.recommend_content);
            recommenddistance = itemView.findViewById(R.id.recommend_distance);
            recommendimage = itemView.findViewById(R.id.recommend_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            onclickListener.onMyItemClick(adapterPosition);
        }
    }
}