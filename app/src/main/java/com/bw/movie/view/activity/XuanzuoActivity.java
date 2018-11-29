package com.bw.movie.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.view.widget.SeatTable;

import java.math.BigDecimal;
import java.util.ArrayList;

public class XuanzuoActivity extends AppCompatActivity implements View.OnClickListener{

    private SeatTable seatTableView;
    private ArrayList<String> selectedSeat;
    private TextView heji;
    private double fare;
    private double f1;
    private double price;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuanzuo);
        LinearLayout  linearLayout = findViewById(R.id.x_lin);
        TextView xname = findViewById(R.id.x_name);
        TextView cssj = findViewById(R.id.x_cssj);
        TextView jssj = findViewById(R.id.x_jssj);

        ImageView xuanzhong = findViewById(R.id.x_xuanzhong);
        ImageView quxiao = findViewById(R.id.x_quxiao);
        heji = findViewById(R.id.x_heji);
        xuanzhong.setOnClickListener(this);
        quxiao.setOnClickListener(this);

        linearLayout.getBackground().setAlpha(100);
        Intent intent = getIntent();

        String beginTime = intent.getStringExtra("beginTime");
        String endTime = intent.getStringExtra("endTime");
        String name = intent.getStringExtra("name");
        id = intent.getStringExtra("id");
        fare = intent.getDoubleExtra("fare",1);
        cssj.setText(beginTime);
        jssj.setText(endTime);
        xname.setText(name);
        String screeningHall = intent.getStringExtra("screeningHall");
        seatTableView = findViewById(R.id.seatView);
        seatTableView.setScreenName(screeningHall+"荧幕");//设置屏幕名称
        seatTableView.setMaxSelected(3);//设置最多选中
        seatTableView.setSeatChecker(new SeatTable.SeatChecker() {

            @Override
            public boolean isValidSeat(int row, int column) {
                if(column==3) {
                    return false;
                }
                return true;
            }

            @Override
            public boolean isSold(int row, int column) {
                if(row==6&&column==6){
                    return true;
                }
                return false;
            }

            @Override
            public void checked(int row, int column) {
                onClickLisenter();
            }



            @Override
            public void unCheck(int row, int column) {
                onClickLisenter();
            }

            @Override
            public String[] checkedSeatTxt(int row, int column) {
                return null;
            }

        });
        seatTableView.setData(10,15);
    }

    private void onClickLisenter() {
        selectedSeat = seatTableView.getSelectedSeat();
        if (selectedSeat.size() == 0) {
            heji.setText("0.00");
            f1 = 0;
            return;
        }
        int size = selectedSeat.size();
        price = 0;
        for (int i = 0; i < size; i++) {
            price +=fare;
        }
        BigDecimal bg = new BigDecimal(price);
        f1 =  bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        heji.setText(f1 +"");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.x_quxiao:
                Toast.makeText(this, "取消订单", Toast.LENGTH_SHORT).show();
                break;
            case R.id.x_xuanzhong:
                if (f1==0){
                    Toast.makeText(XuanzuoActivity.this, "请选取你想要的坐位!", Toast.LENGTH_LONG).show();
                }else{
                    int size = seatTableView.getSelectedSeat().size();
                    Intent  intent=new Intent(XuanzuoActivity.this,WeiXinActivity.class);
                    intent.putExtra("id",id);
                    intent.putExtra("size",size+"");
                    intent.putExtra("price", f1);
                    startActivity(intent);
                }
                break;
        }
    }
}

