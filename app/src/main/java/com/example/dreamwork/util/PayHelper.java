package com.example.dreamwork.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by co-mall on 2016/6/14.
 */
public class PayHelper {

    private static final int SDK_PAY_FLAG = 1;
    private static final String WX_APP_ID = "8490434389793483";

    private static IWXAPI api;



    /**
     * 支付宝支付
     *
     * @param id
     * @param payType
     */
    public static void aliPay(final Activity mActivity, String id, String payType) {

        @SuppressLint("HandlerLeak")
        final Handler mHandler = new Handler() {
            @SuppressWarnings("unused")
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case SDK_PAY_FLAG: {
                        PayResult payResult = new PayResult((String) msg.obj);
                        /**
                         * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                         * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                         * docType=1) 建议商户依赖异步通知
                         */
                        String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                        String resultStatus = payResult.getResultStatus();
                        Log.e("alipay", "the resultSatus is " + resultStatus);
                        // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                        if (TextUtils.equals(resultStatus, "9000")) {
                            Toast.makeText(mActivity, "支付成功", Toast.LENGTH_SHORT).show();
                        } else {
                            // 判断resultStatus 为非"9000"则代表可能支付失败
                            // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                            if (TextUtils.equals(resultStatus, "8000")) {
                                Toast.makeText(mActivity, "支付结果确认中", Toast.LENGTH_SHORT).show();

                            } else {
                                // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                                Toast.makeText(mActivity, "支付失败", Toast.LENGTH_SHORT).show();

                            }
                        }
                        break;
                    }
                    default:
                        break;
                }
            }

            ;
        };
        final String payInfo = getPayInfo(id, payType);
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(mActivity);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


    public static void wxPay(Activity mActivity, String Id, String payType) {
        api = WXAPIFactory.createWXAPI(mActivity, WX_APP_ID);
        api.registerApp(WX_APP_ID);


        final String payInfo = getPayInfo(Id, payType);

        JSONObject json;
        try {
            json = new JSONObject(payInfo);
            if (null != json && !json.has("retcode")) {
                PayReq req = new PayReq();
                req.appId = json.getString("appid");
                req.partnerId = json.getString("partnerid");
                req.prepayId = json.getString("prepayid");
                req.nonceStr = json.getString("noncestr");
                req.timeStamp = json.getString("timestamp");
                req.packageValue = json.getString("package");
                req.sign = json.getString("sign");
                req.extData = "app data"; // optional

                api.sendReq(req);
            } else {
                Log.d("PAY_GET", "返回错误" + json.getString("retmsg"));
                Toast.makeText(mActivity,
                        "返回错误" + json.getString("retmsg"), Toast.LENGTH_SHORT)
                        .show();
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    /**
     * 根据订单id及支付方式，获取对于的支付信息
     *
     * @param id
     * @param payType
     * @return
     */
    private static String getPayInfo(String id, String payType) {

        String info="_input_charset=\"utf-8\"&service=\"mobile.securitypay.pay\"&partner=\"2088911258507622\"&notify_url=\"http://localhost:9080/mobile/api/pay/alipayNotify\"&out_trade_no=\"114160613000092\"&subject=\"订单支付:114160613000092\"&payment_type=\"1\"&seller_id=\"sanzhisswuxian@163.com\"&total_fee=\"160.00\"&body=\"北美虾\"&sign=\"C9YhRIEHkOqgDzEp5muSn20usDOp7vs1MgCK0xEsRJveYkEj%2Fl0Ih%2Bdnn1HXvwg%2FLYPv7N5NlUQh85nIDw1nfyqdMHCWtQNt%2BmoNTV2N90%2FOXQlXwaI5DxSgjHb7OESa6I%2BfphzHl%2FJVS56wLIunuQbZsMhs1rtWbES8SkzEuac%3D\"&sign_type=\"RSA\"";

        return info;
    }
}
