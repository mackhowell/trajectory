package com.example.trajectory;

import java.io.IOException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class RepeatingAlarmServiceTester extends BroadcastReceiver {
	
	private final static String LOG_TAG = "RepeatingAlarmServiceTester";

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.v(LOG_TAG, "HI FROM ALARM SERVICE TEST!");
		Toast.makeText(context, "AALLLLARMMM!", Toast.LENGTH_LONG).show();
        
	}
}
