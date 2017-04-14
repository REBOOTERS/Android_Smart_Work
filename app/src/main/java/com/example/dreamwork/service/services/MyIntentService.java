package com.example.dreamwork.service.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyIntentService extends IntentService {
	private final String TAG = MyIntentService.class.getSimpleName();

	public MyIntentService(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public MyIntentService() {
		// TODO Auto-generated constructor stub
		super("MyIntentService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		// Intent是从Activity发过来的，携带识别参数，根据参数不同执行不同的任务
		String action = intent.getExtras().getString("param");
		if (action.equals("s1"))
			Log.e(TAG, "启动service1");
		else if (action.equals("s2"))
			Log.e(TAG, "启动service2");
		else if (action.equals("s3"))
			Log.e(TAG, "启动service3");

		// 让服务休眠2秒
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.e(TAG, "onBind");
		return super.onBind(intent);
	}

	@Override
	public void onCreate() {
		Log.e(TAG, "onCreate");
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.e(TAG, "onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void setIntentRedelivery(boolean enabled) {
		super.setIntentRedelivery(enabled);
		Log.e(TAG, "setIntentRedelivery");
	}

	@Override
	public void onDestroy() {
		Log.e(TAG, "onDestroy");
		super.onDestroy();
	}

}
