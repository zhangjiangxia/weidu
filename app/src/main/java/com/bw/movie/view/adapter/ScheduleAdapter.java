package com.bw.movie.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.model.bean.DyAndYyIdBean;

import java.util.List;


public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.VooHodel>{
      List<DyAndYyIdBean.ResultBean> result;
      Context  context;
      double fare;
      String name;

    public ScheduleAdapter(List<DyAndYyIdBean.ResultBean> result, Context context, double fare, String name) {
        this.result = result;
        this.context = context;
        this.fare= fare;
        this.name=name;
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
        View view = LayoutInflater.from(context).inflate(R.layout.schedule_itme,viewGroup,false);
        VooHodel vooHodel = new VooHodel(view);
        return vooHodel;
    }

    @Override
    public void onBindViewHolder(@NonNull VooHodel vooHodel, int i) {
       vooHodel.shengting.setText(result.get(i).getScreeningHall());
       vooHodel.ccsj.setText(result.get(i).getEndTime());
       vooHodel.rcsj.setText(result.get(i).getBeginTime());
       vooHodel.liexing.setText(result.get(i).getDuration());
       vooHodel.jiage.setText(fare+"");


        result.get(i).getSeatsTotal();
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class  VooHodel extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final TextView rcsj;
        private final TextView ccsj;
        private final TextView shengting;
        private final TextView liexing;
        private final TextView jiage;
        private final ImageView imageView;

        public VooHodel(@NonNull View itemView) {
            super(itemView);
            rcsj = itemView.findViewById(R.id.s_text_rcsj);
            ccsj = itemView.findViewById(R.id.s_text_ccsj);
            shengting = itemView.findViewById(R.id.sc_text_shengting);
            liexing = itemView.findViewById(R.id.s_text_leixing);
            jiage = itemView.findViewById(R.id.s_text_jiage);
            imageView = itemView.findViewById(R.id.s_image_imag);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            onclickListener.onMyItemClick(adapterPosition);
        }
    }
}
