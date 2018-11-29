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
import com.bw.movie.model.bean.BeingBean;

import java.util.List;

public class BeingAdapterOuter extends RecyclerView.Adapter<BeingAdapterOuter.VooHodel> {
    Context  context;
    List<BeingBean.ResultBean> result;

    public BeingAdapterOuter(Context context, List<BeingBean.ResultBean> result) {
        this.context = context;
        this.result = result;
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
        View view = LayoutInflater.from(context).inflate(R.layout.filmouter, viewGroup, false);
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


    class VooHodel  extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final TextView outertext;
        private final ImageView outerimag;

        public VooHodel(@NonNull View itemView) {
            super(itemView);
            outertext = itemView.findViewById(R.id.outer_text);
            outerimag = itemView.findViewById(R.id.outer_imag);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int layoutPosition = getLayoutPosition();
            onclickListener.onMyItemClick(layoutPosition);
        }
    }
}
