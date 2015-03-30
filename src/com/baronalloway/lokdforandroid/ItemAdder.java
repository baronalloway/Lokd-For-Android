package com.baronalloway.lokdforandroid;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class ItemAdder extends Activity {
String password;
EncryptionKey userKey;
Bitmap imageBitmap = null;
ImageButton saveButton;
EditText nameText;
ImageView picView;
WalletItem newItem;
ImageSer newImage;
String name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_adder);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		saveButton = (ImageButton)findViewById(R.id.saveButton);
		nameText = (EditText)findViewById(R.id.nameField);
		picView = (ImageView)findViewById(R.id.imageView1);
		
		setTitle("New Item");
//		Intent i = getIntent();
//		password = i.getStringExtra("password");
//		
//		try{
//			userKey = new EncryptionKey(password);
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
		
		
		
		//if we dont have a picture
		if(imageBitmap == null)
		{
			Toast.makeText(getApplicationContext(), "Take a Picture", 5).show();
			//take the picture through this intent
			Intent takePictureIntent = new Intent(ItemAdder.this, Camera_capture.class);
			
		    startActivityForResult(takePictureIntent, 1);
		    
		}
		
		saveButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				name = nameText.getText().toString();
				
				if(name.equals("") || name.equals(null))
				{
					Toast.makeText(getApplicationContext(), "PLEASE ENTER A NAME", 5).show();
				}
				else
				{
					newImage = new ImageSer(imageBitmap);
					newItem = new WalletItem(newImage, name);
					Log.i("info", "INTENT");
					Intent intent = new Intent();
			            intent.putExtra("new_item", newItem);
			            setResult(RESULT_OK, intent);
			            Log.i("info", "FINISHING");
			            Log.i("info", "" + imageBitmap.getByteCount());
			            finish();
				}
				
			}});
		
		
		
		
		//add retake photo button
		//add cancel button
		
		
		
	}

	
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		
		
		boolean cancelled = data.getExtras().getBoolean("cancelled");
		
		if(cancelled == true)
		{
			finish();
		}
		else
		{
		imageBitmap = (Bitmap)data.getExtras().get("image");
		
		picView.setImageBitmap(imageBitmap);
		 
		picView.refreshDrawableState();
		}
		
		
	}
	@Override
	public void onBackPressed() {
		Toast.makeText(getApplicationContext(), "WHOOPS...KEEP MOVING FORWARD", 5).show();
	}
	
	

}
