package com.bw.movie.view.IView;

import com.bw.movie.model.bean.BuyBean;
import com.bw.movie.model.bean.WechitBean;

public interface IBuyView extends IBaseView{
    //下单
    void onsuccessBuy(BuyBean  buyBean);
    //支付
    void   onsuccesspay(WechitBean  wechitBean);
}
