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
import android.widget.Toast;
import android.view.View;

// Service
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
                Log.v(this.getClass().getName(), "onClick: Starting SensorService.");
                startService(new Intent(this, SensorService.class));
                break;
            case R.id.buttonStop:
                Log.v(this.getClass().getName(), "onClick: Stopping SensorService.");
                stopService(new Intent(this, SensorService.class));
                break;
        }
    }
}
