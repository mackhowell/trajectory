package com.example.trajectory;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.graphics.Paint;
import android.graphics.RectF;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.ShortBuffer;

import com.googlecode.javacv.FrameRecorder;
import com.googlecode.javacv.FFmpegFrameRecorder;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

import static com.googlecode.javacv.cpp.opencv_core.*;


public class RepeatingAlarmService extends BroadcastReceiver {
	
//	// NOW STARTING VARS FROM JAVACV CODE
//	private final static String LOG_TAG = "RepeatingAlarmService";
//	private PowerManager.WakeLock mWakeLock;
//	
//	private String ffmpeg_link = "rtmp://mackhowell:blueberries@172.31.99.235:1935/live/test.flv";
//	
//	private volatile FFmpegFrameRecorder recorder;
//	boolean recording = false;
//	long startTime = 0;
//	
//	private int sampleAudioRateInHz = 44100;
//	private int imageWidth = 320;
//	private int imageHeight = 240;
//	private int frameRate = 30;
//	
//	private Thread audioThread;
//	volatile boolean runAudioThread = true;
//	private AudioRecord audioRecord;
//	private AudioRecordRunnable audioRecordRunnable;  // probs XMl or media recorder
//	
//	private CameraView cameraView;  //create a new view in XML
//	private IplImage yuvIplimage;
//	private LinearLayout mainLayout;
//	// NOW ENDING VARS FROM JAVACV CODE

    @Override
    public void onReceive(Context context, Intent intent) {
//        Toast.makeText(context, "HI FROM SERVICE!", Toast.LENGTH_LONG).show();
//        Log.v(this.getClass().getName(), "Timed alarm onReceive() started at time: " + new java.sql.Timestamp(System.currentTimeMillis()).toString());
    	
//    	// NOW STARTING JAVACV STREAM FUNCTION CALLS
//    	
//    	cameraView = new CameraView(this);  // originally from initLayout() in test code
//    	Log.v(LOG_TAG, "added cameraView to BroadcastReceiver!");
//    	
//    	mainLayout = (LinearLayout) this.findViewById(R.id.record_layout);
//    	LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(imageWidth, imageHeight); // also from initLayout()    
//        mainLayout.addView(cameraView, layoutParam);
//        Log.v(LOG_TAG, "added cameraView to mainLayout");
//        
//    	
//    	initRecorder();
//    	startRecording();
//    	
//    	// NOW ENDING JAVACV STREAM FUNCTION CALLS
    	
    }
    
//    private void initRecorder() {
//        Log.w(LOG_TAG,"initRecorder");
//
//        if (yuvIplimage == null) {
//        	// Recreated after frame size is set in surface change method
//            yuvIplimage = IplImage.create(imageWidth, imageHeight, IPL_DEPTH_8U, 2);
//        	//yuvIplimage = IplImage.create(imageWidth, imageHeight, IPL_DEPTH_32S, 2);
//
//            Log.v(LOG_TAG, "IplImage.create");
//        }
//
//        recorder = new FFmpegFrameRecorder(ffmpeg_link, imageWidth, imageHeight, 1);
//        Log.v(LOG_TAG, "FFmpegFrameRecorder: " + ffmpeg_link + " imageWidth: " + imageWidth + " imageHeight " + imageHeight);
//
//        recorder.setFormat("flv");
//        Log.v(LOG_TAG, "recorder.setFormat(\"flv\")");
//        
//        recorder.setSampleRate(sampleAudioRateInHz);
//        Log.v(LOG_TAG, "recorder.setSampleRate(sampleAudioRateInHz)");
//
//        // re-set in the surface changed method as well
//        recorder.setFrameRate(frameRate);
//        Log.v(LOG_TAG, "recorder.setFrameRate(frameRate)");
//
//        // this is a class created below
//        audioRecordRunnable = new AudioRecordRunnable();
//        audioThread = new Thread(audioRecordRunnable);
//    }
//    
//    // Start the capture
//    public void startRecording() {
//        try {
//        	Log.v(LOG_TAG, "Sucess! i'm now in StartRecording()");
//            recorder.start();
//            startTime = System.currentTimeMillis();
//            recording = true;
//            audioThread.start();
//        } catch (FFmpegFrameRecorder.Exception e) {
//            e.printStackTrace();
//        }
//    }
//    
//    public void stopRecording() {
//    	// This should stop the audio thread from running
//    	runAudioThread = false;
//
//        if (recorder != null && recording) {
//            recording = false;
//            Log.v(LOG_TAG,"Finishing recording, calling stop and release on recorder");
//            try {
//                recorder.stop();
//                recorder.release();
//            } catch (FFmpegFrameRecorder.Exception e) {
//                e.printStackTrace();
//            }
//            recorder = null;
//        }
//    }
//    
//    //----------------------------------------------------
//    // audio thread, gets and encodes audio data
//    //----------------------------------------------------
//    class AudioRecordRunnable implements Runnable {
//    	
//    	@Override
//    	public void run() {
//    		// set the thread priority
//    		android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_URGENT_AUDIO);
//    		
//    		// AUDIO!
//    		int bufferSize;
//    		short [] audioData;
//    		int bufferReadResult;
//    		
//    		bufferSize = AudioRecord.getMinBufferSize(sampleAudioRateInHz, AudioFormat.CHANNEL_CONFIGURATION_MONO, AudioFormat.ENCODING_PCM_16BIT);
//    		audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, sampleAudioRateInHz, AudioFormat.CHANNEL_CONFIGURATION_MONO, AudioFormat.ENCODING_PCM_16BIT, bufferSize);
//    		
//    		audioData = new short[bufferSize];
//    		
//    		Log.d(LOG_TAG, "audioRecord.startRecording()");
//    		
//    		audioRecord.startRecording();
//    		
//    		// audio capture and encoding loop
//    		while (runAudioThread) {
//    			// read from audioRecord
//    			bufferReadResult = audioRecord.read(audioData, 0, audioData.length);
//    			if (bufferReadResult > 0) {
//    				//Log.v(LOG_TAG,"audioRecord bufferReadResult: " + bufferReadResult);
//                	
//                    // Changes in this variable may not be picked up despite it being "volatile"
//    				if (recording) {
//    					try {
//    						// write to FFmpedFrameRecorder
//    						Buffer[] buffer = {ShortBuffer.wrap(audioData, 0, bufferReadResult)};
//    						recorder.record(buffer);
//    					} catch (FFmpegFrameRecorder.Exception e) {
//    						Log.v(LOG_TAG, e.getMessage());
//    						e.printStackTrace();
//    					}
//    				}
//    			}
//    		}
//    		Log.v(LOG_TAG,"AudioThread Finished");
//    		
//    		/* Capture/Encoding finished, release recorder */
//            if (audioRecord != null) {
//                audioRecord.stop();
//                audioRecord.release();
//                audioRecord = null;
//                Log.v(LOG_TAG,"audioRecord released");
//            }
//    	}
//    }
//    
//    //----------------------------------------------------
//    // camera view class
//    //----------------------------------------------------
//    
//    class CameraView extends SurfaceView implements SurfaceHolder.Callback, PreviewCallback {
//    	
//    	private boolean previewRunning = false;
//    	
//    	private SurfaceHolder holder;
//    	private Camera camera;
//    	
//    	private byte[] previewBuffer;
//    	
//    	long videoTimestamp = 0;
//    	
//    	Bitmap bitmap;
//    	Canvas canvas;
//    	
//    	public CameraView(Context _context) {
//    		super (_context);
//    		
//    		holder = this.getHolder();
//    		holder.addCallback(this);
//    		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
//    	}
//    	
//    	@Override
//    	public void surfaceCreated(SurfaceHolder holder) {
//    		camera = Camera.open();
//    		
//    		try {
//    			camera.setPreviewDisplay(holder);
//    			camera.setPreviewCallback(this);
//    			
//    			Camera.Parameters currentParams = camera.getParameters();
//    			Log.v(LOG_TAG,"Preview Framerate: " + currentParams.getPreviewFrameRate());
//	        	Log.v(LOG_TAG,"Preview imageWidth: " + currentParams.getPreviewSize().width + " imageHeight: " + currentParams.getPreviewSize().height);
//	        	
//	        	// Use these values
//	        	imageWidth = currentParams.getPreviewSize().width;
//	        	imageHeight = currentParams.getPreviewSize().height;
//	        	frameRate = currentParams.getPreviewFrameRate();
//	        	
//	        	bitmap = Bitmap.createBitmap(imageWidth, imageHeight, Bitmap.Config.ALPHA_8);
//	        	
//	        	/*
//				Log.v(LOG_TAG,"Creating previewBuffer size: " + imageWidth * imageHeight * ImageFormat.getBitsPerPixel(currentParams.getPreviewFormat())/8);
//	        	previewBuffer = new byte[imageWidth * imageHeight * ImageFormat.getBitsPerPixel(currentParams.getPreviewFormat())/8];
//				camera.addCallbackBuffer(previewBuffer);
//	            camera.setPreviewCallbackWithBuffer(this);
//	        	*/	
//	        	
//	        	camera.startPreview();
//	        	previewRunning = true;
//    		}
//    		catch (IOException e) {
//    			Log.v(LOG_TAG, e.getMessage());
//    			e.printStackTrace();
//    		}
//    	}
//    	
//    	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//    		Log.v(LOG_TAG, "SURFACE CHANGED: Width " + width + " height: " + height);
//    		
//            // We would do this if we want to reset the camera parameters
//            /*
//            if (!recording) {
//    			if (previewRunning){
//    				camera.stopPreview();
//    			}
//
//    			try {
//    				//Camera.Parameters cameraParameters = camera.getParameters();
//    				//p.setPreviewSize(imageWidth, imageHeight);
//    			    //p.setPreviewFrameRate(frameRate);
//    				//camera.setParameters(cameraParameters);
//    				
//    				camera.setPreviewDisplay(holder);
//    				camera.startPreview();
//    				previewRunning = true;
//    			}
//    			catch (IOException e) {
//    				Log.e(LOG_TAG,e.getMessage());
//    				e.printStackTrace();
//    			}	
//    		}            
//            */
//    		
//    		// get current parameters
//    		Camera.Parameters currentParams = camera.getParameters();
//    		Log.v(LOG_TAG, "Preview Framerate: " + currentParams.getPreviewFrameRate());
//    		Log.v(LOG_TAG, "Preview imageWidth: " + currentParams.getPreviewSize().width + " imageHeight: " + currentParams.getPreviewSize().height);
//    		
//    		// use these values
//    		imageWidth = currentParams.getPreviewSize().width;
//    		imageHeight = currentParams.getPreviewSize().height;
//    		frameRate = currentParams.getPreviewFrameRate();
//    		
//    		// create the yuvIplimage if needed
//    		yuvIplimage = IplImage.create(imageWidth, imageHeight, IPL_DEPTH_8U, 2);
////    		yuvIplimage = IplImage.create(imageWidth, imageHeight, IPL_DEPTH_32S, 2);
//    	}
//    	
//    	@Override
//    	public void surfaceDestroyed(SurfaceHolder holder) {
//    		try {
//    			camera.setPreviewCallback(null);
//    			
//    			previewRunning = false;
//    			camera.release();
//    		}
//    		catch (RuntimeException e) {
//    			Log.v(LOG_TAG, e.getMessage());
//    			e.printStackTrace();
//    		}
//    	}
//    	
//    	@Override
//    	public void onPreviewFrame(byte[] data, Camera camera) {
//    		
//    		if (yuvIplimage != null && recording) {
//    			videoTimestamp = 1000 * (System.currentTimeMillis() - startTime);
//    			
//    			// put the camera preview frame right into the yuvIplimage oject
//    			yuvIplimage.getByteBuffer().put(data);
//    			
//    			// FAQ about IplImage:
//                // - For custom raw processing of data, getByteBuffer() returns an NIO direct
//                //   buffer wrapped around the memory pointed by imageData, and under Android we can
//                //   also use that Buffer with Bitmap.copyPixelsFromBuffer() and copyPixelsToBuffer().
//                // - To get a BufferedImage from an IplImage, we may call getBufferedImage().
//                // - The createFrom() factory method can construct an IplImage from a BufferedImage.
//                // - There are also a few copy*() methods for BufferedImage<->IplImage data transfers.
//            	
//            	// Let's try it..
//            	// This works but only on transparency
//            	// Need to find the right Bitmap and IplImage matching types
//            	
//            	/*
//            	bitmap.copyPixelsFromBuffer(yuvIplimage.getByteBuffer());
//            	//bitmap.setPixel(10,10,Color.MAGENTA);
//            	
//            	canvas = new Canvas(bitmap);
//            	Paint paint = new Paint(); 
//            	paint.setColor(Color.GREEN);
//            	float leftx = 20; 
//            	float topy = 20; 
//            	float rightx = 50; 
//            	float bottomy = 100; 
//            	RectF rectangle = new RectF(leftx,topy,rightx,bottomy); 
//            	canvas.drawRect(rectangle, paint);
//       		 
//            	bitmap.copyPixelsToBuffer(yuvIplimage.getByteBuffer());
//                */
//                //Log.v(LOG_TAG,"Writing Frame");
//    			
//    			try {
//    				// get the correct time
//    				recorder.setTimestamp(videoTimestamp);
//    				
//    				// record the image into FFmpegFrameRecorder
//    				recorder.record(yuvIplimage);
//    			} catch (FFmpegFrameRecorder.Exception e) {
//    				Log.v(LOG_TAG, e.getMessage());
//    				e.printStackTrace();
//    			}
//    		}
//    	}
//    }

}
