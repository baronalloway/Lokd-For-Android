package com.example.lokdforandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		EditText passwordView = (EditText)findViewById(R.id.passwordtext);
		Button goButton = (Button)findViewById(R.id.gobutton);
		
		goButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				
			
				
				
				
			}});
		
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
