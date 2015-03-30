package com.baronalloway.lokdforandroid;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class ViewItem extends Activity {
	String password;
	String selected;
	EncryptionKey userKey;
	List<WalletItem> myItems = new ArrayList<WalletItem>();
	WalletItem selectedItem;
	int a = 0;
	ImageView picView;
	Bitmap image;
	ImageButton changePicButton;
	ImageButton deleteItemButton;
	ImageSer newImageSer;
	ImageButton closeButton;
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_item);
		picView = (ImageView)findViewById(R.id.imageView1);
		changePicButton = (ImageButton)findViewById(R.id.changePicture);
		deleteItemButton = (ImageButton)findViewById(R.id.deleteButton);
		closeButton = (ImageButton)findViewById(R.id.closeButton);
		
		Intent i = getIntent();
		
		password = i.getStringExtra("password");
		selected = i.getStringExtra("selected");
		
		try{
		userKey = new EncryptionKey(password);
		myItems = userKey.get(userKey.getKey(), userKey.getCipher(), userKey.getDCipher(), getApplicationContext());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		do{
			selectedItem = myItems.get(a);
			a++;
		}while(!(selected.equals(myItems.get(a-1).getName())));
		
		setTitle(selectedItem.getName());
		
		
		//code to display the selected item using the ImageSer and a view
		image = selectedItem.getImage().toImage();
		picView.setImageBitmap(image);
		 
		picView.refreshDrawableState();
		
		
		
		changePicButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				//code to reopen camera and take the picture again
				Toast.makeText(getApplicationContext(), "Take a Picture", 5).show();
				//take the picture through this intent
				Intent takePictureIntent = new Intent(ViewItem.this, Camera_capture.class);
				
			    startActivityForResult(takePictureIntent, 1);
			    
				
				
			}});
		
		
		
		deleteItemButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				//code to delete the item and close the activity
				
				Log.i(null, "removed" + myItems.get(a-1).getName());
				myItems.remove(a-1);
				
				userKey.saveFile(myItems, userKey.getKey(), userKey.getCipher(), userKey.getDCipher(), getApplicationContext());
				Intent newIntent = new Intent(ViewItem.this, ViewWallet.class);
				newIntent.putExtra("password", password);
				Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fadeanim,R.anim.blankanim).toBundle();
				startActivity(newIntent, bndlanimation);
				
				
			}});
		
		closeButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				userKey.saveFile(myItems, userKey.getKey(), userKey.getCipher(), userKey.getDCipher(), getApplicationContext());
				Intent newIntent = new Intent(ViewItem.this, ViewWallet.class);
				newIntent.putExtra("password", password);
				Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fadeanim,R.anim.blankanim).toBundle();
				startActivity(newIntent, bndlanimation);
				
			}});
		
	}
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_item, menu);
		return true;
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		boolean cancelled = data.getExtras().getBoolean("cancelled");
		
		if(cancelled == true)
		{
			
		}
		else
		{
		image = (Bitmap)data.getExtras().get("image");
		newImageSer = new ImageSer(image);
		selectedItem.setImageSer(newImageSer);
		picView.setImageBitmap(image);
		picView.refreshDrawableState();
		
		myItems.get(a-1).setImageSer(newImageSer);
		
		
		
		userKey.saveFile(myItems, userKey.getKey(), userKey.getCipher(), userKey.getDCipher(), getApplicationContext());
		}
		
		
	}
		
		
	

}
