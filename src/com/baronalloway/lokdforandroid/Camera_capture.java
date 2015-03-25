package com.baronalloway.lokdforandroid;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Camera_capture extends Activity implements SurfaceHolder.Callback{
	private Camera mCamera;
	private SurfaceView surfaceView;
	private SurfaceHolder surfaceHolder;
	private ImageButton capture_image;
	private ImageButton cancelButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    //CHANGE BELOW
	    setTitle("Take a Picture");
	    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	    setContentView(R.layout.activity_camera_capture);
	    
	    capture_image = (ImageButton) findViewById(R.id.capture_image);
	    capture_image.setOnClickListener(new View.OnClickListener() {

	        @Override
	        public void onClick(View v) {
	            capture();
	        }
	    });
	    
	    cancelButton = (ImageButton)findViewById(R.id.cancelButton);
	    cancelButton.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				cancel();
				
			}});
	    
	    
	    
	    surfaceView = (SurfaceView) findViewById(R.id.surfaceview);
	    surfaceHolder = surfaceView.getHolder();
	    surfaceHolder.addCallback(Camera_capture.this);
  
	    try {
	        mCamera = Camera.open();
	        Camera.Parameters params = mCamera.getParameters();
	        params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
	        mCamera.setParameters(params);
	        mCamera.setPreviewDisplay(surfaceHolder);
	        mCamera.startPreview();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	private void capture() {
		
	    mCamera.takePicture(null, null, null, new Camera.PictureCallback() {

	        @Override
	        public void onPictureTaken(byte[] data, Camera camera) {
	            Toast.makeText(getApplicationContext(), "Picture Taken",
	                    Toast.LENGTH_SHORT).show();
	            
	            
	           
	           //FAILED BINDER TRANSACTION -- DATA TOO BIG (NEED TO EITHER COMPRESS AND SEND OR JUST COMPRESS AND SEND THE IMAGE ITSELF)
	           //FIX DIS BY MAKING BITMAP HERE AND THEN BACK TO BYTE 
//	            Bitmap imageBitmap;
//	            //make bitmap image from data
//	            imageBitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
//	            //scale the image bitmap
//	            Log.i("info", "scaling the bitmap");
//	            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//	            imageBitmap.compress(Bitmap.CompressFormat.PNG, 1, stream);      
//	            byte[] byteArray = stream.toByteArray();
	   		 
	            //this block tries to use densitymultiplier method
	          Bitmap imageBitmap;
	          imageBitmap = BitmapFactory.decodeByteArray(data, 0, data.length);  
	          final float densityMultiplier = getApplicationContext().getResources().getDisplayMetrics().density;        
	          int h= (int) (100*densityMultiplier);
	          int w= (int) (h * imageBitmap.getWidth()/((double) imageBitmap.getHeight()));
	          //int h = 100;
	          //int w = 200;
	          imageBitmap = Bitmap.createScaledBitmap(imageBitmap, w, h, true); 
	          boolean cancel = false;
	          
	          
	          
	          //ByteArrayOutputStream stream = new ByteArrayOutputStream();
	          //byte[] byteArray = stream.toByteArray();
            
	            
	   		 
	            
	            Intent intent = new Intent();
	            //intent.putExtra("image_arr", data);
	            //intent.putExtra("image_arr", byteArray);
	            intent.putExtra("image", imageBitmap);
	            intent.putExtra("cancelled", cancel);
	            setResult(1, intent);
	            camera.stopPreview();
	            if (camera != null) {
	                camera.release();
	                mCamera = null;
	            }
	            
	            finish();
	        }
	    });
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
	        int height) {
	    Log.e("Surface Changed", "format   ==   " + format + ",   width  ===  "
	            + width + ", height   ===    " + height);
	    try {
	        mCamera.setPreviewDisplay(holder);
	        mCamera.startPreview();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
	    Log.e("Surface Created", "");
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
	    Log.e("Surface Destroyed", "");
	}

	@Override
	protected void onPause() {
	    super.onPause();
	    if (mCamera != null) {
	        mCamera.stopPreview();
	        mCamera.release();
	    }
	}
	
	@Override
	public void onBackPressed() {
		Toast.makeText(getApplicationContext(), "WHOOPS...KEEP MOVING FORWARD", 5).show();
	}
	
	
	
	public void cancel()
	{
		mCamera.takePicture(null, null, null, new Camera.PictureCallback(){

			@Override
			public void onPictureTaken(byte[] data, Camera camera) {
				Bitmap imageBitmap = null;
				boolean cancel = true;
				 Intent intent = new Intent();
				intent.putExtra("image", imageBitmap);
				intent.putExtra("cancelled", cancel);
				setResult(1, intent);
		        camera.stopPreview();
		        if (camera != null) {
		            camera.release();
		            mCamera = null;
		        }	
		        Log.i(null, "Cancelling Picture");
		        finish();
			}});
		
		
	}
	
	
	
		
	

		}
