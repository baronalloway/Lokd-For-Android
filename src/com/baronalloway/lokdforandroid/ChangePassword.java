package com.baronalloway.lokdforandroid;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ChangePassword extends Activity {

	Button changePasswordButton;
	Button nukeWalletButton;
	String password;
	List<WalletItem> myItems = new ArrayList<WalletItem>();
	EncryptionKey userKey;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_password);
		
		changePasswordButton = (Button)findViewById(R.id.changePassword);
		nukeWalletButton = (Button)findViewById(R.id.nukewallet);
		
		Intent receive = getIntent();
		password = receive.getStringExtra("password");
		
		try{
			userKey = new EncryptionKey(password);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		try{
			myItems = userKey.get(userKey.getKey(), userKey.getCipher(), userKey.getDCipher(), getApplicationContext());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			finish();
		}
		
		nukeWalletButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				//nuke the wallet
				userKey.delete(getApplicationContext());
				
				Intent locknoFile = new Intent(ChangePassword.this, MainActivity.class);
				startActivity(locknoFile);
				
				//go back to lock screen
				
			}});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.change_password, menu);
		return true;
	}

}
