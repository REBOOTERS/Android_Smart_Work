package com.example.dreamwork.service.notification;

import com.example.dreamwork.R;
import com.example.dreamwork.service.services.ServiceActivity;
import com.example.dreamwork.util.V;

import android.app.Activity;
import android.app.Notification;
import android.app.Notification.Builder;
import android.content.Intent;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class NotificationActivity extends Activity implements OnClickListener {
	NotificationManager manager;
	Notification notification;
	final int ID = 1;

	//
	private Button sure, clean;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification_layout);
		InitView();
		manager = (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);
		Builder builder = new Builder(this);
		builder.setTicker("This is a Notification");
		builder.setContentTitle("This is content Title");
		builder.setContentText("This is content Text");
		builder.setSubText("Sub Text");
		builder.setSmallIcon(R.drawable.ic_launcher);
		builder.setWhen(System.currentTimeMillis());
		builder.setDefaults(Notification.DEFAULT_ALL);
		// 设置true后，点击notification后可自动取消
		builder.setAutoCancel(true);
		// 设置点击notification之后的intent
		Intent intent = new Intent(this, ServiceActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
		builder.setContentIntent(pendingIntent);
		notification = builder.build();
	}

	private void InitView() {
		// TODO Auto-generated method stub
		sure = V.f(this, R.id.SimpleNotification);
		sure.setOnClickListener(this);
		clean = V.f(this, R.id.CleanSimpleNotification);
		clean.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.SimpleNotification:
			manager.notify(ID, notification);
			break;
		case R.id.CleanSimpleNotification:
			manager.cancel(ID);
			break;
		default:
			break;
		}
	}

}
