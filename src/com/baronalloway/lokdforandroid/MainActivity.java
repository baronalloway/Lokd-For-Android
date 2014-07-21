package com.baronalloway.lokdforandroid;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
			password = passwordView.getText().toString();
			passwordView.setText("");
			
			try {
				userKey = new EncryptionKey(password);
				
			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(getApplicationContext(), "PLEASE INPUT A PASSWORD", 5).show();
			} 
			try
			{
			userKey.get(userKey.getKey(), userKey.getCipher(), userKey.getDCipher(), getApplicationContext());
			
			Intent walletIntent = new Intent(MainActivity.this, ViewWallet.class);
			walletIntent.putExtra("password", password);
			startActivity(walletIntent);
			
			
			}
			catch(Exception e)
			{
				e.printStackTrace();
				Toast.makeText(getApplicationContext(), "Error! WRONG PASSWORD", 7).show();
			}
			
			
			
			
			}});
		
		
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

}
