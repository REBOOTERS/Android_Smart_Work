package com.example.dreamwork.service.services;

import com.example.dreamwork.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class IntentServiceActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intentservice_layout);

		Intent it1 = new Intent("com.test.intentservice");
		//5.0之后需要显示启动Service必须这么写
		it1.setPackage("com.example.dreamwork");
		Bundle b1 = new Bundle();
		b1.putString("param", "s1");
		it1.putExtras(b1);

		Intent it2 = new Intent("com.test.intentservice");
		it2.setPackage("com.example.dreamwork");
		Bundle b2 = new Bundle();
		b2.putString("param", "s2");
		it2.putExtras(b2);

		Intent it3 = new Intent("com.test.intentservice");
		it3.setPackage("com.example.dreamwork");
		Bundle b3 = new Bundle();
		b3.putString("param", "s3");
		it3.putExtras(b3);

		// 接着启动多次IntentService,每次启动,都会新建一个工作线程
		// 但始终只有一个IntentService实例
		startService(it1);
		startService(it2);
		startService(it3);
	}

}
