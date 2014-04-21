package com.example.trajectory;

import java.util.List;

//import org.divenvrsk.examples.service.R;
//import org.divenvrsk.examples.service.ServiceExample;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.view.View;

// Service
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

// here comes the new activity

public class MainActivity extends Activity implements View.OnClickListener {

    Button buttonStart, buttonStop;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        buttonStart = (Button) findViewById(R.id.buttonStart);
        buttonStop = (Button) findViewById(R.id.buttonStop);

        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonStart:
                Log.v(this.getClass().getName(), "onClick: Starting service.");
                startService(new Intent(this, SensorService.class));
                break;
            case R.id.buttonStop:
                Log.v(this.getClass().getName(), "onClick: Stopping service.");
                stopService(new Intent(this, SensorService.class));
                break;
        }
    }
}


//public class MainActivity extends Activity implements SensorEventListener {
//
//	private SensorManager sensorManager;
//	private Sensor accelerometerSensor;
//	private Sensor lightSensor;
//	private Sensor magneticSensor;
//	private Sensor pressureSensor;
//	
//	@Override
//    public void onCreate(Bundle savedInstanceState) {
//		Log.v("onCreate", "INSIDE ONCREATE");
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        
//        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
//		accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
//		lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
//		magneticSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
//		pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
//		
//		//List all avail sensors
////		List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
////			for (Sensor sensor : sensors) {
////		        Log.v("Sensors", "" + sensor.getName());
////		    }
//    }
//	
//	@Override
//	protected void onResume() {
//		Log.v("onResume", "INSIDE ONRESUME");
//		super.onResume();
//		sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
//		sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
//		sensorManager.registerListener(this, magneticSensor, SensorManager.SENSOR_DELAY_NORMAL);
//		sensorManager.registerListener(this, pressureSensor, SensorManager.SENSOR_DELAY_NORMAL);
//	}	
//
//	@Override 
//	protected void onPause() {
//		Log.v("OnPause", "INSIDE ONPAUSE");
//		super.onPause();
//    	sensorManager.unregisterListener(this);
//    }
//	
//	public void onAccuracyChanged(Sensor sensor, int accuracy) {
//	}
//
//	public void onSensorChanged(SensorEvent event) {
//		
//        //Initial RED Background
//        View view = this.getWindow().getDecorView();
//		switch (event.sensor.getType()) {
//		case Sensor.TYPE_LINEAR_ACCELERATION:
////			Log.v("Accel","Z: " + event.values[2]);
//			if (event.values[2] >= 6)  {
////				Toast toast = Toast.makeText(this, "YES IM HERE", Toast.LENGTH_SHORT);
////				toast.show();
//				view.setBackgroundColor(Color.GREEN);
//			}
//			else {
//				view.setBackgroundColor(Color.RED);
//			}
//			break;
//		
//		case Sensor.TYPE_LIGHT:
//			float lux = event.values[0];
////			Log.v("LIGHT", "LIGHT: " + lux);
//			if (lux > 600) {
////				Toast toast = Toast.makeText(this, "YES IM HERE", Toast.LENGTH_SHORT);
////				toast.show();
//				view.setBackgroundColor(Color.GREEN);
//			}
//			else {
//				view.setBackgroundColor(Color.RED);
//			}
//			break;
//			
//		case Sensor.TYPE_MAGNETIC_FIELD:
//			float magnetism = event.values[0];
//			Log.v("MAGNETISM", "MAGNETISM: " + magnetism);
//			break;
//			
//		case Sensor.TYPE_PRESSURE:
//			float millibars = event.values[0];
//			Log.v("MILLIBARS", "MILLIBARS: " + millibars);
//			break;
//		}
//	}
//}
//
