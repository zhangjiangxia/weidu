package com.bw.movie.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;

import java.util.List;

public class JuAdapterOuter extends RecyclerView.Adapter<JuAdapterOuter.VooHodel> {
    List<String> posterList;
    Context  context;

    public JuAdapterOuter(List<String> posterList, Context context) {
        this.posterList = posterList;
        this.context = context;
    }

    @NonNull
    @Override
    public VooHodel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.juzhoa_itme, viewGroup, false);
        VooHodel vooHodel = new VooHodel(view);
        return vooHodel;
    }

    @Override
    public void onBindViewHolder(@NonNull VooHodel vooHodel, int i) {
        String s = posterList.get(i);
        Glide.with(context).load(s).into(vooHodel.juzhaoimag);
    }

    @Override
    public int getItemCount() {
        return posterList.size();
    }

    class   VooHodel  extends RecyclerView.ViewHolder {

        private final ImageView juzhaoimag;

        public VooHodel(@NonNull View itemView) {
        super(itemView);
        juzhaoimag = itemView.findViewById(R.id.juzhao_image);
    }
   }
}
