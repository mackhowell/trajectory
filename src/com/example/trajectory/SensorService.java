package com.example.trajectory;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.os.SystemClock;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

public class SensorService extends Service {
	public static final int INTERVAL = 5000; //5 secs
	public static final int FIRST_RUN = 5000; //5 secs
	int REQUEST_CODE = 11223344;
	
	AlarmManager alarmManager;
	
	// window manager!
	private View myView;
	private WindowManager.LayoutParams myParams;
	private WindowManager myWindowManager;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		startService();
		Log.v(this.getClass().getName(), "onBind(..)");
		
		// load the new windowManager view
	    myView = new MyLoadView(this);
	    myParams = new WindowManager.LayoutParams(
	            WindowManager.LayoutParams.MATCH_PARENT, 150, 10, 10,
	            WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
	            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
	            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
	            PixelFormat.TRANSLUCENT);
	    myParams.gravity = Gravity.CENTER;
	    myParams.setTitle("Window test");
	    myWindowManager = (WindowManager)getSystemService(WINDOW_SERVICE);
	    myWindowManager.addView(myView, myParams);
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		Log.v(this.getClass().getName(), "onBind(..)");
		return null;
	}
	
	@Override
	public void onDestroy() {
		if (alarmManager != null) {
			Intent intent = new Intent(this, RepeatingAlarmService.class);
			alarmManager.cancel(PendingIntent.getBroadcast(this, REQUEST_CODE, intent, 0));
		}
		Toast.makeText(this, "Service Stopped!", Toast.LENGTH_SHORT).show();
		Log.v(this.getClass().getName(), "Service onDestroy(). Stop AlarmManager at " + new java.sql.Timestamp(System.currentTimeMillis()).toString());
		
		// destroy alarm manager
		((WindowManager)getSystemService(WINDOW_SERVICE)).removeView(myView);
	    myView = null;
	}
	
	private void startService() {
		
		// Stuff for alarmManager
		Intent intent = new Intent(this, RepeatingAlarmService.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, REQUEST_CODE, intent, 0);
		
		// DO IT ONCE FOR TESTING STREAM...NOT REPEATING
		alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
				SystemClock.elapsedRealtime() + FIRST_RUN,
				INTERVAL,
				pendingIntent);
		
		Toast.makeText(this, "Service Started.", Toast.LENGTH_SHORT).show();
		Log.v(this.getClass().getName(), "AlarmManager started at " + new java.sql.Timestamp(System.currentTimeMillis()).toString());
	}
	
	public class MyLoadView extends View {

	    private Paint mPaint;

	    public MyLoadView(Context context) {
	        super(context);
	        mPaint = new Paint();
	        mPaint.setTextSize(50);
	        mPaint.setARGB(200, 200, 200, 200);
	    }

	    @Override
	    protected void onDraw(Canvas canvas) {
	        super.onDraw(canvas);
	        canvas.drawText("test test test", 0, 100, mPaint);
	    }

	    @Override
	    protected void onAttachedToWindow() {
	        super.onAttachedToWindow();
	    }

	    @Override
	    protected void onDetachedFromWindow() {
	        super.onDetachedFromWindow();
	    }

	    @Override
	    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	    }
	}

}
