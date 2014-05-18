package com.example.trajectory;

import java.util.List;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.os.SystemClock;
import android.util.Log;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

public class SensorService extends Service implements SensorEventListener {
	
//	AlarmManager alarmManager;
//	public static boolean alarmRunning = false;
//	public static final int INTERVAL = 5000; //5 secs
//	public static final int FIRST_RUN = 0; //0 secs
//	int REQUEST_CODE = 11223344;
	
//	// window manager!
//	private View myView;
//	private WindowManager.LayoutParams myParams;
//	private WindowManager myWindowManager;
	
	// SENSOR MANAGER
	private SensorManager sensorManager;
	private Sensor accelerometerSensor;
	private Sensor pressureSensor;
	private Sensor gyroSensor;
	private Sensor lightSensor;
	
	// Set Values From Seekbar
	public static int setMotion = 0;
	
//	Binder to get seekbar value
	final IBinder myBinder = new LocalBinder();
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		startService();
		Log.v(this.getClass().getName(), "onBind(..)");
		
//		// load the new windowManager view
//	    myView = new MyLoadView(this);
//	    myParams = new WindowManager.LayoutParams(
//	            WindowManager.LayoutParams.MATCH_PARENT, 150, 10, 10,
//	            WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
//	            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
//	            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
//	            PixelFormat.TRANSLUCENT);
//	    myParams.gravity = Gravity.CENTER;
//	    myParams.setTitle("Window test");
//	    myWindowManager = (WindowManager)getSystemService(WINDOW_SERVICE);
//	    myWindowManager.addView(myView, myParams);
	    
		// SENSOR MANAGER + REGISTER LISTENER!!!
		sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
		pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
		gyroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
		lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
		sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
		sensorManager.registerListener(this, pressureSensor, SensorManager.SENSOR_DELAY_NORMAL);
		sensorManager.registerListener(this, gyroSensor, SensorManager.SENSOR_DELAY_NORMAL);
		sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
		
		//List all avail sensors
		List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
			for (Sensor sensor : sensors) {
		        Log.v("Sensors", "" + sensor.getName());
		    }
			
		// intent for accelerometer set value from seekbar
			IntentFilter accelerometerFilter = new IntentFilter();
			accelerometerFilter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
		    this.registerReceiver(null, accelerometerFilter);

	}
	
    public class LocalBinder extends Binder {
        SensorService getService() {
            // Return this instance of LocalService so clients can call public methods
            return SensorService.this;
        }
    }

	
	@Override
	public IBinder onBind(Intent intent) {
		Log.v(this.getClass().getName(), "onBind(..)");
		return myBinder;
	}
	
	private void startService() {
		
		// alarm boolean
//		alarmRunning = true;
		
		// setup Intent for alarmManager
//		Intent intent = new Intent(this, RepeatingAlarmServiceTester.class);
//		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, REQUEST_CODE, intent, 0);
		
		// REPEATING ALARM SENDER
//		alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//		alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
//				SystemClock.elapsedRealtime() + FIRST_RUN,
//				INTERVAL,
//				pendingIntent);
		
		Toast.makeText(this, "Service Started.", Toast.LENGTH_SHORT).show();
//		Log.v(this.getClass().getName(), "AlarmManager started at " + new java.sql.Timestamp(System.currentTimeMillis()).toString());
		
	}
	
	private void launchStreamer() {
		// launches the new activity StreamActivity.java
		Intent streamintent = new Intent();
		streamintent.setAction(Intent.ACTION_MAIN);
		streamintent.addCategory(Intent.CATEGORY_LAUNCHER);
		streamintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		streamintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
		ComponentName cn = new ComponentName(this, StreamActivity.class);
		streamintent.setComponent(cn);
		
		startActivity(streamintent);
	}
	
	private void closeStreamer() {
		sendBroadcast(new Intent("closeStream"));
	}

	@Override
	public void onDestroy() {
		
		// alarm boolean
//		alarmRunning = false;
		
//		if (alarmManager != null) {
//			Intent intent = new Intent(this, RepeatingAlarmServiceTester.class);
//			alarmManager.cancel(PendingIntent.getBroadcast(this, REQUEST_CODE, intent, 0));
//		}
		
		Toast.makeText(this, "Service Stopped!", Toast.LENGTH_SHORT).show();
		Log.v(this.getClass().getName(), "Service onDestroy(). Stop AlarmManager at " + new java.sql.Timestamp(System.currentTimeMillis()).toString());
		
//		// destroy window manager
//		((WindowManager)getSystemService(WINDOW_SERVICE)).removeView(myView);
//	    myView = null;
	    
	    // unregister LISTENER!
	    sensorManager.unregisterListener(this);
	}

	
	
	
	
	// HERE THEY ARE!
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		Log.v("ACCURACY INT","onAccuracyChanged: " + sensor + ", accuracy: " + accuracy);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		
		//// ----- Get Values From Seek Bar ----- ////
//		int setAccel = getIntent().getIntExtra("AccelerometerValue");
//		Log.v("SeekBar", "SENT FROM SEEKBAR!! " + );
		
		// broadcast receiver
//		final BroadcastReceiver accelerometerReceiver = new BroadcastReceiver() {
//			   @Override
//			   public void onReceive(Context context, Intent intent) {
//			      String action = intent.getAction();
//			      if(action.equals("android.intent.action.ACCELEROMETER_VALUE")){
//			        setMotion = intent.getIntExtra("AccelerometerValue", 0);
//			        Log.v("SeekBar", "SENT FROM SEEKBAR!! " + setMotion);
//			      }
////			      else if(action.equals(android.telephony.TelephonyManager.ACTION_PHONE_STATE_CHANGED)){
////			           //action for phone state changed
////			      }     
//			   }
//			};
		
//		Log.v("SensorListener", "imRunning = " + StreamActivity.imRunning);

		switch (event.sensor.getType()) {
		
		//------LINEAR ACCELERATION-----//
		case Sensor.TYPE_LINEAR_ACCELERATION:
//			Log.v("Accel","Z: " + event.values[2]);
			// was at (8)!!
			if (event.values[2] >= Math.abs(8) && StreamActivity.imRunning == false)  {
//				Toast.makeText(this, "YES IM HERE", Toast.LENGTH_SHORT).show();
				launchStreamer();
			}
			
			else {
//				Log.v("CLOSE STREAM","CLOSING STREAM NOW!!!");
//				closeStreamer();
			}
			
			break;
			
			//------PRESSURE-----//
//			case Sensor.TYPE_PRESSURE:
//				float millibars_of_pressure = event.values[0];
//				Log.v("MILLIBARS OF PRESSURE = ", Float.toString(millibars_of_pressure));
			
//			 Try and get just altitude
//			float altitude = getAltitude(sensorManager.PRESSURE_STANDARD_ATMOSPHERE, Sensor.TYPE_PRESSURE);
			
//				if (event.values[0] < 1007 && StreamActivity.imRunning == false) {
//					Toast.makeText(this, "YES IM HERE", Toast.LENGTH_SHORT).show();
//					launchStreamer();
//				}
//				
//				else{
////					closeStreamer();
//				}
//			break;
			
			//------GYROSCOPE-----//
//		case Sensor.TYPE_GYROSCOPE:
//			Log.v("GYRO", " X:" + event.values[0]);
//			if (event.values[0] >= Math.abs(10) && StreamActivity.imRunning == false)  {
////				Toast.makeText(this, "YES IM HERE", Toast.LENGTH_SHORT).show();
//				launchStreamer();
//			}
//			
//			else {
//				closeStreamer();
//			}
//			break;
			
			//------LIGHT-----//
//					case Sensor.TYPE_LIGHT:
////						Log.v("Accel","Z: " + event.values[2]);
//						if (event.values[0] >= Math.abs(3) && StreamActivity.imRunning == false)  {
////							Toast.makeText(this, "YES IM HERE", Toast.LENGTH_SHORT).show();
////							launchStreamer();
//						}
//						
//						else {
////							Log.v("CLOSE STREAM","CLOSING STREAM NOW!!!");
////							closeStreamer();
//						}
//						
//						break;
		}
		
	}
	
//	public class MyLoadView extends View {
//		
//		private Paint mPaint;
//		
//		public MyLoadView(Context context) {
//			super(context);
//		    mPaint = new Paint();
//		    mPaint.setTextSize(50);
//		    mPaint.setARGB(200, 200, 200, 200);
//		}
//	
//	    @Override
//	    protected void onDraw(Canvas canvas) {
//	        super.onDraw(canvas);
//	        canvas.drawText("test test test", 0, 100, mPaint);
//	    }
//	
//	    @Override
//	    protected void onAttachedToWindow() {
//	        super.onAttachedToWindow();
//	    }
//	
//	    @Override
//	    protected void onDetachedFromWindow() {
//	        super.onDetachedFromWindow();
//	    }
//	
//	    @Override
//	    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//	        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//	    }
//	}

}
