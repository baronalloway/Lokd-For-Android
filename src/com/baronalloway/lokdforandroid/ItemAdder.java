package com.baronalloway.lokdforandroid;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ItemAdder extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_adder);
		
		
		
		setTitle("New Item");
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.item_adder, menu);
		return true;
	}

}
