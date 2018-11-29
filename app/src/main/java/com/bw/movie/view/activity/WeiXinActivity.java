package com.bw.movie.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.model.bean.BuyBean;
import com.bw.movie.model.bean.WechitBean;
import com.bw.movie.model.miwen.Md5;
import com.bw.movie.presenter.BuyPresenter;
import com.bw.movie.view.IView.IBuyView;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


public class WeiXinActivity extends AppCompatActivity implements IBuyView{


    private BuyPresenter buyPresenter;
    private String scheduleId;
    private String amount;
    private String userId;
    private String sign1;
    private String orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wei_xin);
        TextView jiage = findViewById(R.id.jiage);
        CheckBox  weixin = findViewById(R.id.checkbox_weixin);
        CheckBox  zhibao = findViewById(R.id.checkbox_zhifubao);
        boolean weixinchecked = weixin.isChecked();
        buyPresenter = new BuyPresenter();
        buyPresenter.attch(this);

        Intent intent = getIntent();
        double price = intent.getDoubleExtra("price", 0);
        scheduleId = intent.getStringExtra("id");
        amount = intent.getStringExtra("size");
        Log.i("cc", "排期id "+ scheduleId +"票数"+ amount);
        jiage.setText("支付"+price+"元");
        SharedPreferences sp = WeiXinActivity.this.getSharedPreferences("userInfo", 0);
        userId = sp.getString("userId", null);
        String Sign=userId+scheduleId+amount+"movie";
        sign1 = Md5.MD5(Sign);

        Log.i("mm", "onCreate: "+scheduleId+amount+ sign1);
        jiage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buy();
            }
        });

    }

//下单
    private void buy() {
        buyPresenter.infoData(scheduleId,amount,sign1);
    }


//下单成功
    @Override
    public void onsuccessBuy(BuyBean buyBean) {
        String message = buyBean.getMessage();
        orderId = buyBean.getOrderId();
        Log.i("message", "onsuccessBuy: =="+buyBean.getOrderId());
      //  Toast.makeText(this, "你好"+message, Toast.LENGTH_SHORT).show();
        if (buyBean.getStatus().equals("0000")){
            Log.i("message", "onsuccessBuy: ==="+buyBean.getMessage());
        }else {
            Toast.makeText(this, "下单失败", Toast.LENGTH_SHORT).show();
        }
        buyPresenter.infosData(orderId);
    }
//支付成功
    IWXAPI api;
    @Override
    public void onsuccesspay(WechitBean wechitBean) {
        String message = wechitBean.getMessage();

        if (!wechitBean.getStatus().equals("0000")){
            Toast.makeText(this, "吐司"+message, Toast.LENGTH_SHORT).show();
            return;
        }
        api = WXAPIFactory.createWXAPI(this, null);
        api.registerApp("wxb3852e6a6b7d9516");
        PayReq request = new PayReq();
        request.appId = wechitBean.getAppId();
        request.partnerId = wechitBean.getPartnerId();
        request.prepayId= wechitBean.getPrepayId();
        request.packageValue = wechitBean.getPackageValue();
        request.nonceStr= wechitBean.getNonceStr();
        request.timeStamp= wechitBean.getTimeStamp();
        request.sign= wechitBean.getSign();
        api.sendReq(request);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        buyPresenter.delech();
    }

}
