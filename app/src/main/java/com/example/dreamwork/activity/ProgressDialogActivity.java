package com.example.dreamwork.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.dreamwork.R;

/**
 * Created by co-mall on 2016/6/3.
 */
public class ProgressDialogActivity extends AppCompatActivity {


    private Context mContext;
    private Button button2,button3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_progressdialog_layout);
        InitView();
    }

    private void InitView() {
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog mProgressDialog = new ProgressDialog(mContext);
                mProgressDialog.setTitle("标题");
                mProgressDialog.setMessage("内容");
                mProgressDialog.show();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog progress = ProgressDialog.show(mContext, "标题", "内容");
                progress.show();
            }
        });


    }
}
