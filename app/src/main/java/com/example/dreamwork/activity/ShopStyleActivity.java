package com.example.dreamwork.activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dreamwork.R;
import com.example.dreamwork.util.SecurityCode;
import com.example.dreamwork.util.ShoppingTools;
import com.example.dreamwork.util.T;
import com.example.dreamwork.util.V;
import com.example.dreamwork.widget.BadgeView;
import com.example.dreamwork.widget.TimerCount;

/**
 * Created by co-mall on 2016/6/3.
 */
public class ShopStyleActivity extends AppCompatActivity {

    private Context mContext;

    /**
     * 杠掉价
     */
    private TextView marketPrice;
    /**
     * 精确到分
     */
    private TextView Price1, Price2;
    /**
     * 简单富文本
     */
    private TextView richStyleTv;
    private TextView richStyleTv1;

    /**
     * 倒计时实现
     */
    private TimerCount timer;
    private Button getCode;
    private EditText phoneNum;

    /**
     * 密码显示
     */
    private EditText passwordEdit;
    private CheckBox show;
    /**
     * 动态校验码
     */
    private ImageView codeImg;
    private EditText inputCode;
    private Button verifyCode;

    private ImageView plusImg, subImg;
    private TextView numTv;
    private RelativeLayout shopCarTab;
    private BadgeView numView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_style_layout);
        mContext = this;
        initView();
        Function();
    }

    private void Function() {
        /**
         * 实现TextView 中间横线效果
         */
        marketPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);

        /**
         * 实现小数点后保留两位
         */
        String valueStr = "88.88888";
        double valueDou = 88.8888;

        Price1.setText(ShoppingTools.FormatNum2(valueStr));
        Price2.setText(ShoppingTools.FormatNum2(valueDou));
        /**
         * 实现TextView文本内变色
         */
        SpannableStringBuilder builder = new SpannableStringBuilder(richStyleTv.getText().toString());
        ForegroundColorSpan redSpan = new ForegroundColorSpan(Color.RED);
        ForegroundColorSpan greenSpan = new ForegroundColorSpan(Color.GREEN);
        builder.setSpan(redSpan, 1, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(greenSpan, 7, 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        richStyleTv.setText(builder);


        richStyleTv1.setText(Html.fromHtml("第<font color=green>二</font>杯半价，第<font color=green>三</font>杯免费"));

        /**
         * 密码明文显示或密文显示
         */
        show.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //明文显示
                    passwordEdit.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    passwordEdit.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                //这句话确保，点击checkBox时，光标始终在最后，很有用呀
                passwordEdit.setSelection(passwordEdit.getText().length());
            }
        });


        /**
         * 验证码倒计时实现
         */
        timer = new TimerCount(120, 1000, getCode);
        getCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(phoneNum.getText().toString())) {
                    T.showShort(mContext, "不能为空");
                } else if (!ShoppingTools.isMobileNO(phoneNum.getText().toString())) {
                    T.showShort(mContext, "请输入正确的手机号");
                } else {
                    timer.start();
                }

            }
        });

        /**
         * 图片验证码获取及验证
         */
        codeImg.setImageBitmap(SecurityCode.getInstance().createBitmap(300, 100, 15, mContext));
        /**
         * 点击图片生成新的验证码
         */
        codeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codeImg.setImageBitmap(SecurityCode.getInstance().createBitmap(300, 100, 15, mContext));
            }
        });

        verifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SecurityCode.getInstance().Verification(inputCode.getText().toString().trim())) {
                    T.showShort(mContext, "OK");
                } else {
                    T.showShort(mContext, "WRONG");
                }
            }
        });

        /**
         * 角标更新
         */

        numView = new BadgeView(mContext);
        numView.setBackground(9, getResources().getColor(R.color.enablebtn));
        numView.setTargetView(shopCarTab);
        numView.setHideOnNull(false);
        numView.setBadgeCount(0);

        plusImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.valueOf(numTv.getText().toString());
                value++;
                numTv.setText(String.valueOf(value));
                numView.setBadgeCount(value);
            }
        });

        subImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.valueOf(numTv.getText().toString());
                if (value > 1) {
                    value--;
                }
                numTv.setText(String.valueOf(value));
                numView.setBadgeCount(value);
            }
        });

    }

    private void initView() {
        marketPrice = V.f(this, R.id.marketPrice);


        Price1 = V.f(this, R.id.Price1);
        Price2 = V.f(this, R.id.Price2);


        richStyleTv = V.f(this, R.id.richStyleTv);
        richStyleTv1 = V.f(this, R.id.richStyleTv1);

        getCode = V.f(this, R.id.getCode);
        phoneNum = V.f(this, R.id.phoneNum);


        passwordEdit = V.f(this, R.id.passwordEdit);
        /**
         * 这样也可以是EditText变为密码模式
         */
        passwordEdit.setInputType(0x81);
        show = V.f(this, R.id.showPw);

        codeImg = V.f(this, R.id.codeImg);
        inputCode = V.f(this, R.id.inputCode);
        verifyCode = V.f(this, R.id.verifyCode);
        ///
        plusImg = V.f(this, R.id.plusBut);
        subImg = V.f(this, R.id.subBut);
        numTv = V.f(this, R.id.numText);
        shopCarTab = V.f(this, R.id.shopCarTab);

    }
}
