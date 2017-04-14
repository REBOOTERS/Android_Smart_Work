package com.example.dreamwork.service.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

	private final String TAG = MyService.class.getSimpleName();
	private int number = 0;
	private MyBinder binder = new MyBinder();
	private boolean flag = true;
	// 广播
	private Intent broadcastIntent;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.e(TAG, "onCreate");

		broadcastIntent = new Intent();
		broadcastIntent.setAction(ServiceActivity.MY_RECEIVER_ACTION);

		flag = true;
		new Thread() {
			public void run() {
				while (flag) {
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						// TODO: handle exception
					}
					number++;
					broadcastIntent.putExtra("number", number);
					sendBroadcast(broadcastIntent);
				}

			};
		}.start();

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.e(TAG, "onStartCommand");
		return super.onStartCommand(intent, flags, startId);

	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		flag = false;
		Log.e(TAG, "onDestroy");
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		Log.e(TAG, "onUnbind");
		return super.onUnbind(intent);
	}

	@Override
	public void onRebind(Intent intent) {
		// TODO Auto-generated method stub
		super.onRebind(intent);
		Log.e(TAG, "onRebind");
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Log.e(TAG, "onBind");
		return binder;
	}

	class MyBinder extends Binder {

		public int getMsg() {
			return number;
		}
	}

}
