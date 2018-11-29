package com.bw.movie.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.model.bean.PageListBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class AttentionAdapter extends RecyclerView.Adapter<AttentionAdapter.VooHodel> {
    List<PageListBean.ResultBean> result;
    Context  context;

    public AttentionAdapter(List<PageListBean.ResultBean> result, Context context) {
        this.result = result;
        this.context = context;
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


    class   VooHodel extends RecyclerView.ViewHolder {

        private final SimpleDraweeView hot_simpleDraweeView;
        private final TextView hot_name;
        private final TextView hotintro;
        public VooHodel(@NonNull View itemView) {
          super(itemView);
            hot_name = itemView.findViewById(R.id.hotinside_name);
            hotintro = itemView.findViewById(R.id.hotinside_intro);
            hot_simpleDraweeView = itemView.findViewById(R.id.hotinside_simpleDraweeview);
      }
  }
}
