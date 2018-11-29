package com.bw.movie.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.model.bean.FilmBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class FilmAdapterOuter extends RecyclerView.Adapter<FilmAdapterOuter.VooHodel> {
    List<FilmBean.ResultBean> result;
    Context  context;
    //接口回调
    ItemClickListener onclickListener;

    public interface ItemClickListener{
        void onMyItemClick(int po);
    }

    public void setOnclickListener(ItemClickListener onclickListener) {
        this.onclickListener = onclickListener;
    }


    public FilmAdapterOuter(List<FilmBean.ResultBean> result, Context context) {
        this.result = result;
        this.context = context;
    }

    @NonNull
    @Override
    public VooHodel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.filmouter, viewGroup, false);
        VooHodel vooHodel = new VooHodel(view);
        return vooHodel;
    }

    @Override
    public void onBindViewHolder(@NonNull VooHodel vooHodel, int i) {
       vooHodel.outertext.setText(result.get(i).getName());
       Uri uri = Uri.parse(result.get(i).getImageUrl());
        Log.e("aaa", "onBindViewHolder: "+result.size() );
        vooHodel.outerimag.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return result.size();
    }
    class VooHodel extends RecyclerView.ViewHolder implements View.OnClickListener{

        private  TextView outertext;
        private  SimpleDraweeView outerimag;

        public VooHodel(@NonNull View itemView){
            super(itemView);
          outertext = itemView.findViewById(R.id.outer_text);
          outerimag = itemView.findViewById(R.id.outer_imag);
         itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            onclickListener.onMyItemClick(adapterPosition);
        }
    }

}
