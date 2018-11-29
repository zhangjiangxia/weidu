package com.bw.movie.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.model.bean.ListByCinemaldBean;

import java.util.List;

public class ListByCinemaIdAdapter extends RecyclerView.Adapter<ListByCinemaIdAdapter.VooHodel>{
    List<ListByCinemaldBean.ResultBean> result;
    Context context;

    public ListByCinemaIdAdapter(List<ListByCinemaldBean.ResultBean> result, Context context) {
        this.result = result;
        this.context = context;
    }

    @NonNull
    @Override
    public VooHodel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.lunbo_itme, viewGroup, false);
        VooHodel vooHodel = new VooHodel(view);
        return vooHodel;
    }

    @Override
    public void onBindViewHolder(@NonNull VooHodel vooHodel, int i) {
        vooHodel.outertext.setText(result.get(i).getName());
        Uri uri = Uri.parse(result.get(i).getImageUrl());
        vooHodel.outerimag.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class  VooHodel  extends RecyclerView.ViewHolder {

        private final TextView outertext;
        private final ImageView outerimag;

        public VooHodel(@NonNull View itemView) {
           super(itemView);
            outertext = itemView.findViewById(R.id.outer_text);
            outerimag = itemView.findViewById(R.id.outer_imag);
       }
   }
}
