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
import android.widget.TextView;

public class MainActivity extends Activity implements CompoundButton.OnCheckedChangeListener {
	
	Switch bigSwitch;
	
	SeekBar seekBarAccelerometer;
	SeekBar seekBarPressure;
	SeekBar seekBarMagnetometer;
	
	TextView accelerometerProg;
	TextView pressureProg;
	TextView magnetometerProg;
	
    Button buttonSet;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	//Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        bigSwitch = (Switch) findViewById(R.id.bigSwitch);
        if (bigSwitch != null) {
        	bigSwitch.setOnCheckedChangeListener(this);
        }
        
        //-------ENVIRONMENTAL SEEKBAR-------//
        seekBarAccelerometer = (SeekBar) findViewById(R.id.accelerometer);
//        seekBarAccelerometer.getThumb().mutate().setAlpha(0);
        accelerometerProg = (TextView) findViewById(R.id.accelerometerProgress);
        // Initialize the textview with '0'
        accelerometerProg.setText(seekBarAccelerometer.getProgress() + "/" + seekBarAccelerometer.getMax());

        seekBarAccelerometer.setOnSeekBarChangeListener(
        		new OnSeekBarChangeListener() {
        			int progress = 0;
        			@Override
        			public void onProgressChanged(SeekBar seekBar, 
        					int progressValue, boolean fromUser) {
        				progress = progressValue;
        				}
        			@Override
        			public void onStartTrackingTouch(SeekBar seekBar) {
        				// Do something here, if you want to do anything at the start of touching the seekbar
        				}
        			@Override
        			public void onStopTrackingTouch(SeekBar seekBar) {
        				// Display the value in textview
        				accelerometerProg.setText(progress + "/" + seekBar.getMax());
        				}
        			});
        
        
        //-------ACTIVITY SEEKBAR-------//
        seekBarPressure = (SeekBar) findViewById(R.id.pressure);
        pressureProg = (TextView) findViewById(R.id.pressureProgress);
        // Initialize the textview with '0'
        pressureProg.setText(seekBarPressure.getProgress() + "/" + seekBarPressure.getMax());

        seekBarPressure.setOnSeekBarChangeListener(
        		new OnSeekBarChangeListener() {
        			int progress = 0;
        			@Override
        			public void onProgressChanged(SeekBar seekBar, 
        					int progressValue, boolean fromUser) {
        				progress = progressValue;
        				}
        			@Override
        			public void onStartTrackingTouch(SeekBar seekBar) {
        				// Do something here, if you want to do anything at the start of touching the seekbar
        				}
        			@Override
        			public void onStopTrackingTouch(SeekBar seekBar) {
        				// Display the value in textview
        				pressureProg.setText(progress + "/" + seekBar.getMax());
        				}
        			});
        
        
        //-------MOTION SEEKBAR-------//
        seekBarMagnetometer = (SeekBar) findViewById(R.id.magnetometer);
        magnetometerProg = (TextView) findViewById(R.id.magnetometerProgress);
        // Initialize the textview with '0'
        magnetometerProg.setText(seekBarMagnetometer.getProgress() + "/" + seekBarMagnetometer.getMax());

        seekBarMagnetometer.setOnSeekBarChangeListener(
        		new OnSeekBarChangeListener() {
        			int progress = 0;
        			@Override
        			public void onProgressChanged(SeekBar seekBar, 
        					int progressValue, boolean fromUser) {
        				progress = progressValue;
        				}
        			@Override
        			public void onStartTrackingTouch(SeekBar seekBar) {
        				// Do something here, if you want to do anything at the start of touching the seekbar
        				}
        			@Override
        			public void onStopTrackingTouch(SeekBar seekBar) {
        				// Display the value in textview
        				magnetometerProg.setText(progress + "/" + seekBar.getMax());
        				}
        			});
        
        

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSet:
                Log.v(this.getClass().getName(), "onClick: SENT VALUES TO SERVICE!");
                startService(new Intent(this, SensorService.class));
                break;
//            case R.id.buttonStop:
//                Log.v(this.getClass().getName(), "onClick: Stopping SensorService.");
//                stopService(new Intent(this, SensorService.class));
//                break;
        }
    }

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
