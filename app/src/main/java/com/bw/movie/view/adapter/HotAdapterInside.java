package com.bw.movie.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.model.bean.FilmBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class HotAdapterInside extends RecyclerView.Adapter<HotAdapterInside.VooHodel> {
    List<FilmBean.ResultBean> result;
    Context  context;

    public HotAdapterInside(List<FilmBean.ResultBean> result, Context context) {
        this.result = result;
        this.context = context;
    }
    //接口回调
    FilmAdapterOuter.ItemClickListener onclickListener;

    public interface ItemClickListener{
        void onMyItemClick(int  po);
    }

    public void setOnclickListener(FilmAdapterOuter.ItemClickListener onclickListener) {
        this.onclickListener = onclickListener;
    }

    @NonNull
    @Override
    public VooHodel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.hot_inside_itme, viewGroup, false);
        VooHodel vooHodel = new VooHodel(view);
        return vooHodel;
    }

    @Override
    public void onBindViewHolder(@NonNull VooHodel vooHodel, int i) {
     vooHodel.hot_name.setText(result.get(i).getName());
     vooHodel.hotintro.setText(result.get(i).getSummary());
        String imageUrl = result.get(i).getImageUrl();
        Uri uri = Uri.parse(imageUrl);
        vooHodel.hot_simpleDraweeView.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return result.size();
    }


    class VooHodel extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final SimpleDraweeView hot_simpleDraweeView;
        private final TextView hot_name;
        private final TextView hotintro;

        public VooHodel(@NonNull View itemView) {
            super(itemView);
            hot_name = itemView.findViewById(R.id.hotinside_name);
            hotintro = itemView.findViewById(R.id.hotinside_intro);
            hot_simpleDraweeView = itemView.findViewById(R.id.hotinside_simpleDraweeview);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            onclickListener.onMyItemClick(adapterPosition);
        }
    }

}
