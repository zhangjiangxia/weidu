package com.bw.movie.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.model.bean.BuyRecordBean;

import java.util.List;

public class BuyRecordAdapter extends RecyclerView.Adapter<BuyRecordAdapter.VooHodel> {
    List<BuyRecordBean.ResultBean> result;
    Context context;

    public BuyRecordAdapter(List<BuyRecordBean.ResultBean> result, Context context) {
        this.result = result;
        this.context = context;


    }

    @NonNull
    @Override
    public VooHodel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.buyreciod_itme, viewGroup, false);
        VooHodel vooHodel = new VooHodel(view);
        return vooHodel;
    }

    @Override
    public void onBindViewHolder(@NonNull VooHodel vooHodel, int i) {
        vooHodel.bname.setText(result.get(i).getMovieName());
        vooHodel.bdindanhao.setText("订单号："+result.get(i).getOrderId());
        vooHodel.byingyuan.setText("影院："+result.get(i).getScreeningHall());
        vooHodel.byingting.setText("影厅"+result.get(i).getMovieName());
        vooHodel.bshuliang.setText("数量"+result.get(i).getAmount());
        double price = result.get(i).getPrice();
        vooHodel.bjine.setText("金额："+price+"元");
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class VooHodel extends RecyclerView.ViewHolder {

        private final TextView bname;
        private final TextView bdindanhao;
        private final TextView byingyuan;
        private final TextView byingting;
        private final TextView bshuliang;
        private final TextView bjine;

        public VooHodel(@NonNull View itemView) {
            super(itemView);
            bname = itemView.findViewById(R.id.b_name);
            bdindanhao = itemView.findViewById(R.id.b_dindanhao);
            byingyuan = itemView.findViewById(R.id.b_yingyuan);
            byingting = itemView.findViewById(R.id.b_yingting);
            bshuliang = itemView.findViewById(R.id.b_shuliang);
            bjine = itemView.findViewById(R.id.b_jine);
        }
    }
}
