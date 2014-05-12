package com.example.trajectory;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Switch;
import android.widget.Toast;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
// Service
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity implements CompoundButton.OnCheckedChangeListener {

//    Button buttonStart, buttonStop;
	
	Switch bigSwitch;
	
	SeekBar seekBarAccelerometer;
	SeekBar seekBarPressure;
	SeekBar seekBarMagnetometer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	//Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        buttonStart = (Button) findViewById(R.id.buttonStart);
//        buttonStop = (Button) findViewById(R.id.buttonStop);
//        buttonStart.setOnClickListener(this);
//        buttonStop.setOnClickListener(this);
        
        bigSwitch = (Switch) findViewById(R.id.bigSwitch);
        if (bigSwitch != null) {
        	bigSwitch.setOnCheckedChangeListener(this);
        }
        
        seekBarAccelerometer = (SeekBar) findViewById(R.id.accelerometer);
        seekBarAccelerometer.setOnSeekBarChangeListener(
        		new OnSeekBarChangeListener() {
        			int progress = 0;
        			
        			@Override
        			public void onProgressChanged(SeekBar seekBar, 
        					int progresValue, boolean fromUser) {
        				progress = progresValue;
        				}
        			
        			@Override
        			public void onStartTrackingTouch(SeekBar seekBar) {
        				// Do something here, if you want to do anything at the start of touching the seekbar
        				}
        			
        			@Override
        			public void onStopTrackingTouch(SeekBar seekBar) {
        				// Display the value in textview
        				textView.setText(progress + "/" + seekBar.getMax());
        				}
        			});

    }

//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.buttonStart:
//                Log.v(this.getClass().getName(), "onClick: Starting SensorService.");
//                startService(new Intent(this, SensorService.class));
//                break;
//            case R.id.buttonStop:
//                Log.v(this.getClass().getName(), "onClick: Stopping SensorService.");
//                stopService(new Intent(this, SensorService.class));
//                break;
//        }
//    }

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		
		if (isChecked) {
			Log.v(this.getClass().getName(), "bigSwitch: Starting SensorService.");
			startService(new Intent(this, SensorService.class));

			} else {
				Log.v(this.getClass().getName(), "bigSwitch: Stopping SensorService.");
				stopService(new Intent(this, SensorService.class));
			}
	}
}
