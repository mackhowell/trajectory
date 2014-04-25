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
	
	private CameraSurface cameraSurface;
	private LinearLayout mainLayout;
	
	private int imageWidth = 320;
	private int imageHeight = 240;
	private int frameRate = 30;

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.v(LOG_TAG, "HI FROM ALARM SERVICE TEST!");
		Toast.makeText(context, "AALLLLARMMM!", Toast.LENGTH_LONG).show();
        
        initLayout();
	}
	
	private void initLayout() {
//		mainLayout = (LinearLayout) this.findViewById(R.id.record_layout);
		
		// THIS is undefined: = RepeatingAlarmServiceTester.CameraSurface(RepeatingAlarmTester)
		cameraSurface = new CameraSurface(null);
		
        LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(imageWidth, imageHeight);        
        mainLayout.addView(cameraSurface, layoutParam);
        Log.v(LOG_TAG, "added cameraView to mainLayout");
	}

//	private LinearLayout findViewById(int recordLayout) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	class CameraSurface extends SurfaceView implements SurfaceHolder.Callback, PreviewCallback {
		
    	private boolean previewRunning = false;
    	
    	private SurfaceHolder holder;
    	private Camera camera;
    	
    	private byte[] previewBuffer;
    	
    	long videoTimestamp = 0;
    	
    	Bitmap bitmap;
    	Canvas canvas;

		public CameraSurface(Context context) {
			super(context);
			
    		holder = this.getHolder();
    		holder.addCallback(this);
    		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		}
		
		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			
    		camera = Camera.open();
    		
    		try {
    			camera.setPreviewDisplay(holder);
    			camera.setPreviewCallback(this);
    			
    			Camera.Parameters currentParams = camera.getParameters();
    			Log.v(LOG_TAG,"Preview Framerate: " + currentParams.getPreviewFrameRate());
	        	Log.v(LOG_TAG,"Preview imageWidth: " + currentParams.getPreviewSize().width + " imageHeight: " + currentParams.getPreviewSize().height);
	        	
	        	// Use these values
	        	imageWidth = currentParams.getPreviewSize().width;
	        	imageHeight = currentParams.getPreviewSize().height;
	        	frameRate = currentParams.getPreviewFrameRate();
	        	
	        	bitmap = Bitmap.createBitmap(imageWidth, imageHeight, Bitmap.Config.ALPHA_8);
	        	
	        	/*
				Log.v(LOG_TAG,"Creating previewBuffer size: " + imageWidth * imageHeight * ImageFormat.getBitsPerPixel(currentParams.getPreviewFormat())/8);
	        	previewBuffer = new byte[imageWidth * imageHeight * ImageFormat.getBitsPerPixel(currentParams.getPreviewFormat())/8];
				camera.addCallbackBuffer(previewBuffer);
	            camera.setPreviewCallbackWithBuffer(this);
	        	*/	
	        	
	        	camera.startPreview();
	        	previewRunning = true;
    		}
    		catch (IOException e) {
//    			Log.v(LOG_TAG, e.getMessage());
    			e.printStackTrace();
    		}
			
		}

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {
			
    		// get current parameters
    		Camera.Parameters currentParams = camera.getParameters();
    		Log.v(LOG_TAG, "Preview Framerate: " + currentParams.getPreviewFrameRate());
    		Log.v(LOG_TAG, "Preview imageWidth: " + currentParams.getPreviewSize().width + " imageHeight: " + currentParams.getPreviewSize().height);
    		
    		// use these values
    		imageWidth = currentParams.getPreviewSize().width;
    		imageHeight = currentParams.getPreviewSize().height;
    		frameRate = currentParams.getPreviewFrameRate();
			
		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			
    		try {
    			camera.setPreviewCallback(null);
    			
    			previewRunning = false;
    			camera.release();
    		}
    		catch (RuntimeException e) {
    			Log.v(LOG_TAG, e.getMessage());
    			e.printStackTrace();
    		}
			
		}

		@Override
		public void onPreviewFrame(byte[] data, Camera camera) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
