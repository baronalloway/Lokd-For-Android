package com.baronalloway.lokdforandroid;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lokdforandroid.R;

public class MainActivity extends Activity {

	String password;
	EditText passwordView;
	Button goButton;
	EncryptionKey userKey;
	List<WalletItem> myItems = new ArrayList<WalletItem>();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		passwordView  = (EditText)findViewById(R.id.passwordtext);
		goButton = (Button)findViewById(R.id.gobutton);
		
		goButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Log.i("info", "button clicked");
			password = passwordView.getText().toString();
				Log.i("info", password);
				
				
				
			
			try {
				userKey = new EncryptionKey(password);
				Log.i("info", userKey.toString());
			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(getApplicationContext(), "PLEASE INPUT A PASSWORD", 5).show();
			} 
			try
			{
			userKey.get(userKey.getKey(), userKey.getCipher(), userKey.getDCipher(), getApplicationContext());
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			
			
			
			}});
		
		
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
