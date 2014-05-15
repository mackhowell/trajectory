package com.example.trajectory;

import java.io.IOException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
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
//		Toast.makeText(context, "AALLLLARMMM!", Toast.LENGTH_LONG).show();
		
		// launches the new activity StreamActivity.java
		
//		Intent streamintent = new Intent();
//		streamintent.setAction(Intent.ACTION_MAIN);
//		streamintent.addCategory(Intent.CATEGORY_LAUNCHER);
//		streamintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		ComponentName cn = new ComponentName(this, StreamActivity.class);
//		streamintent.setComponent(cn);
//		startActivity(streamintent);
		
		
//		Intent streamIntent = new Intent();
//		streamIntent.setClassName("com.example.trajectory", "SteamActivity.class");
//		streamIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		context.startActivity(streamIntent);
		
//		if(SensorService.alarmRunning == false){
//        Intent i = new Intent();
//        i.setClassName("com.example.trajectory", "com.example.trajectory.StreamActivity");
////        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
////        i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_CLEAR_TOP |Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
//        context.startActivity(i);
//		}
        
	}
}
