package com.example.dreamwork.service.services;

import com.example.dreamwork.R;
import com.example.dreamwork.service.services.MyService.MyBinder;
import com.example.dreamwork.util.T;
import com.example.dreamwork.util.V;

import android.R.integer;
import android.app.Activity;
import android.app.Service;
import android.app.ActionBar.Tab;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ServiceActivity extends Activity implements OnClickListener {
	private final String TAG = ServiceActivity.class.getSimpleName();
	private Button start, stop, bind, unbind, getbindStatus;
	private TextView statusTv;
	private Intent intent;
	private MyBinder binder;
	private ServiceConnection connection;
	// Service通过广播更新UI
	private MyRecevier receiver;
	public static final String MY_RECEIVER_ACTION = "com.my.receiver";
	private IntentFilter filter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service_layout);
		start = V.f(this, R.id.startService);
		start.setOnClickListener(this);
		stop = V.f(this, R.id.stopService);
		stop.setOnClickListener(this);
		bind = V.f(this, R.id.bindService);
		bind.setOnClickListener(this);
		unbind = V.f(this, R.id.unbindService);
		unbind.setOnClickListener(this);
		getbindStatus = V.f(this, R.id.getbindStatus);
		getbindStatus.setOnClickListener(this);
		statusTv = V.f(this, R.id.ServiceStatus);

		intent = new Intent(this, MyService.class);

		ConnectionTest();

		receiver = new MyRecevier();
		filter = new IntentFilter();
		filter.addAction(MY_RECEIVER_ACTION);

	}

	private void ConnectionTest() {
		// TODO Auto-generated method stub
		connection = new ServiceConnection() {

			@Override
			public void onServiceDisconnected(ComponentName name) {
				// TODO Auto-generated method stub
				Log.e(TAG, "onServiceDisconnected");
			}

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				// TODO Auto-generated method stub
				Log.e(TAG, "onServiceConnected");
				binder = (MyBinder) service;

			}
		};
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.startService:
			startService(intent);
			break;
		case R.id.stopService:
			stopService(intent);
			break;
		case R.id.bindService:
			bindService(intent, connection, Service.BIND_AUTO_CREATE);
			break;
		case R.id.unbindService:
			unbindService(connection);
			break;
		case R.id.getbindStatus:
			if (binder != null) {
				String msString = String.valueOf(binder.getMsg());
				T.showShort(this, msString);
			} else {
				T.showShort(this, "please binder first");
			}

		default:
			break;
		}
	}

	private class MyRecevier extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			statusTv.setText("the service msg is " + intent.getIntExtra("number", 0));
			Log.e(TAG, "the service msg is " + intent.getIntExtra("number", 0));
		}

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		registerReceiver(receiver, filter);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(receiver);
	}
}
