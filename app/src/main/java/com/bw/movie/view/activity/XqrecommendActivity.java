
package com.bw.movie.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.model.bean.DyAndYyIdBean;
import com.bw.movie.model.bean.EvaluationCinemaBean;
import com.bw.movie.model.bean.ListByCinemaldBean;
import com.bw.movie.model.bean.XqRecommmendBean;
import com.bw.movie.presenter.ListByCinemaIdPresenter;
import com.bw.movie.presenter.XqRecommmendPresenter;
import com.bw.movie.view.IView.InfoXqRecommmendView;
import com.bw.movie.view.adapter.EvaluationAdaptet;
import com.bw.movie.view.adapter.FilmAdapterOuter;
import com.bw.movie.view.adapter.ListByCinemaIdAdapter;
import com.bw.movie.view.adapter.ScheduleAdapter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import recycler.coverflow.CoverFlowLayoutManger;
import recycler.coverflow.RecyclerCoverFlow;

public class XqrecommendActivity extends AppCompatActivity implements InfoXqRecommmendView, View.OnClickListener {

    private SimpleDraweeView xqcomimag;
    private TextView xqcomname;
    private TextView weizhi;
    private XqRecommmendPresenter xqRecommmendPresenter;
    private RecyclerCoverFlow mlist;
    private RecyclerView xqcomreycleView;
    private ListByCinemaIdPresenter listByCinemaIdPresenter;
    private int id;
    private int ccid;
    private ScheduleAdapter scheduleAdapter;
    private ImageView xqcom_imag;
    private ListByCinemaIdAdapter listByCinemaIdAdapter;
    private View view;
    private XqRecommmendBean.ResultBean result;
    private LinearLayout cc;
    private LinearLayout pp;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xqrecommend);
        infoview();
        xqRecommmendPresenter = new XqRecommmendPresenter();
        xqRecommmendPresenter.attch(this);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        xqRecommmendPresenter.infoData(id + "");

        listByCinemaIdPresenter = new ListByCinemaIdPresenter();
        listByCinemaIdPresenter.attch(this);
        listByCinemaIdPresenter.infoData(id);

    }

    private void infoview() {
        xqcomimag = findViewById(R.id.xqcom_imag);
        xqcomname = findViewById(R.id.xqcom_name);
        weizhi = findViewById(R.id.xqcom_weizhi);
        mlist = findViewById(R.id.xqrecom_list);
        xqcomreycleView = findViewById(R.id.xqrecom_recyleview);
        xqcom_imag = findViewById(R.id.xqcom_iamg);
        xqcom_imag.setOnClickListener(this);

    }

    @Override
    public void findCinemalnfo(XqRecommmendBean data) {
        result = data.getResult();
        String name = result.getName();
        String logo = result.getLogo();
        String address = result.getAddress();
        String phone = result.getPhone();
        String vehicleRoute = result.getVehicleRoute();

        weizhi.setText(address);
        xqcomname.setText(name);
        xqcomimag.setImageURI(logo);
    }

    //根据影院ID查询该影院当前排期的电影列表
    @Override
    public void ListByCinemaIdsuccess(ListByCinemaldBean listByCinemaldBean) {
        final List<ListByCinemaldBean.ResultBean> result = listByCinemaldBean.getResult();
        listByCinemaIdAdapter = new ListByCinemaIdAdapter(result, XqrecommendActivity.this);
        mlist.setAdapter(listByCinemaIdAdapter);
        int ida = result.get(0).getId();
        listByCinemaIdPresenter.infoDataScheduleo(XqrecommendActivity.this.id, ida, result.get(0).getFare(), result.get(0).getName());
        mlist.setOnItemSelectedListener(new CoverFlowLayoutManger.OnSelected() {
            @Override
            public void onItemSelected(int position) {
                ccid = result.get(position).getId();
                double fare = result.get(position).getFare();
                String name = result.get(position).getName();
                //排期列表
                listByCinemaIdPresenter.infoDataScheduleo(XqrecommendActivity.this.id, ccid, fare, name);
            }
        });

    }


    //影院评论
    @Override
    public void EvaCinemaCommentsuccess(EvaluationCinemaBean evaluationCinemaBean) {
        List<EvaluationCinemaBean.ResultBean> result = evaluationCinemaBean.getResult();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(XqrecommendActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        if (result.size() == 0) {
            return;
        } else if (result.size() != 0) {
            EvaluationAdaptet evaluationAdaptet = new EvaluationAdaptet(result, XqrecommendActivity.this);
            recyclerView.setAdapter(evaluationAdaptet);
        }

    }

    //根据电影ID和影院ID查询电影排期列表
    @Override
    public void Scheduleosuccess(DyAndYyIdBean dyAndYyIdBean, final double fare, final String name) {
        final List<DyAndYyIdBean.ResultBean> result = dyAndYyIdBean.getResult();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(XqrecommendActivity.this, LinearLayoutManager.VERTICAL, false);
        xqcomreycleView.setLayoutManager(linearLayoutManager);
        scheduleAdapter = new ScheduleAdapter(result, XqrecommendActivity.this, fare, name);
        xqcomreycleView.setAdapter(scheduleAdapter);
        scheduleAdapter.setOnclickListener(new FilmAdapterOuter.ItemClickListener() {
            @Override
            public void onMyItemClick(int po) {
                String screeningHall = result.get(po).getScreeningHall();
                String beginTime = result.get(po).getBeginTime();
                String endTime = result.get(po).getEndTime();
                int id = result.get(po).getId();
                // Toast.makeText(XqrecommendActivity.this, "被点击啦" + po, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(XqrecommendActivity.this, XuanzuoActivity.class);
                intent.putExtra("screeningHall", screeningHall);
                intent.putExtra("beginTime", beginTime);
                intent.putExtra("endTime", endTime);
                intent.putExtra("fare", fare);
                intent.putExtra("name", name);
                intent.putExtra("id",id+"");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.xqcom_iamg:
                //  Toast.makeText(this, "被点击啦", Toast.LENGTH_SHORT).show();
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.popwindowlayout_itme, null);
                RadioButton xingqing = view.findViewById(R.id.c_xingqing);
                RadioButton pinglun = view.findViewById(R.id.c_pinlun);
                recyclerView = view.findViewById(R.id.po_RecyclerView);

                cc = view.findViewById(R.id.cc);
                pp = view.findViewById(R.id.pp);

                TextView weizi = view.findViewById(R.id.c_weizhi);
                TextView dianhua = view.findViewById(R.id.c_dianhua);
                TextView cheneche = view.findViewById(R.id.c_chengche);
                TextView ditie = view.findViewById(R.id.c_ditie);
                TextView gongjiao = view.findViewById(R.id.c_gongjiao);
                TextView zijia = view.findViewById(R.id.c_zijia);
                weizi.setText(result.getName());
                dianhua.setText(result.getPhone());
                cheneche.setText(result.getVehicleRoute());
                ditie.setText(result.getVehicleRoute());

                xingqing.setOnClickListener(this);
                pinglun.setOnClickListener(this);
                showPopwindow(view);
                break;
            case R.id.c_xingqing:
                cc.setVisibility(View.VISIBLE);
                pp.setVisibility(View.GONE);
                break;
            case R.id.c_pinlun:
                listByCinemaIdPresenter.infoDataPingjia(id);
                // Toast.makeText(this, "评论", Toast.LENGTH_SHORT).show();
                pp.setVisibility(View.VISIBLE);
                cc.setVisibility(View.GONE);
                break;
        }
    }

    //详情
    private void showPopwindow(View view) {
        PopupWindow window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        window.setFocusable(true);

        ColorDrawable dw = new ColorDrawable(0xffffffff);
        window.setBackgroundDrawable(dw);
        window.setAnimationStyle(R.style.PopupAnimation);
        window.showAtLocation(XqrecommendActivity.this.findViewById(R.id.xqcom_iamg),
                Gravity.BOTTOM, 0, 0);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        xqRecommmendPresenter.delech();
        listByCinemaIdPresenter.delech();
    }
}
