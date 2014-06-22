package com.example.lokdforandroid;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	String password;
	EditText passwordView;
	Button goButton;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		passwordView  = (EditText)findViewById(R.id.passwordtext);
		goButton = (Button)findViewById(R.id.gobutton);
		
		goButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
			password = passwordView.getText().toString();	
			
				
				
				
			}});
		
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
