package com.example.trajectory;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


public class MainActivity extends Activity implements SensorEventListener {

	private SensorManager sensorManager;
	private Sensor accelerometerSensor;
	private Sensor lightSensor;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		Log.v("onCreate", "INSIDE ONCREATE");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
		lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
		
		//List all avail sensors
//		List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
//			for (Sensor sensor : sensors) {
//		        Log.v("Sensors", "" + sensor.getName());
//		    }
    }
	
	@Override
	protected void onResume() {
		Log.v("onResume", "INSIDE ONRESUME");
		super.onResume();
		sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
		sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
	}	

	@Override 
	protected void onPause() {
		Log.v("OnPause", "INSIDE ONPAUSE");
		super.onPause();
    	sensorManager.unregisterListener(this);
    }
	
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	public void onSensorChanged(SensorEvent event) {
//		Log.v("Accel","X: " + event.values[0]);
//		Log.v("Accel","Y: " + event.values[1]);
//		Log.v("Accel","Z: " + event.values[2]);
		
		switch (event.sensor.getType()) {
		case Sensor.TYPE_LINEAR_ACCELERATION:
			if (event.values[2] >= 6)  {
				Toast toast = Toast.makeText(this, "YES IM HERE", Toast.LENGTH_SHORT);
				toast.show();
			}
			break;
		case Sensor.TYPE_LIGHT:
			float lux = event.values[0];
//			Log.v("LIGHT", "LIGHT: " + lux);
			if (lux > 600) {
				Toast toast = Toast.makeText(this, "YES IM HERE", Toast.LENGTH_SHORT);
				toast.show();
			}
			break;
		}
	}
}

