package com.baronalloway.lokdforandroid;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ChangePassword extends Activity {

	Button changePasswordButton;
	Button nukeWalletButton;
	String password;
	List<WalletItem> myItems = new ArrayList<WalletItem>();
	EncryptionKey userKey;
	String pw1;
	String pw2;
	boolean alert1done = false;
	boolean correctpw = false;
	
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
				
			}});
		
		changePasswordButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				
				AlertDialog.Builder alert = new AlertDialog.Builder(ChangePassword.this);

				
				alert.setTitle("Enter New Password");
				alert.setMessage("Please enter your new password.");
				final EditText passwordfirst = new EditText(ChangePassword.this);
				passwordfirst.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
				alert.setView(passwordfirst);
				alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
					  Editable value = passwordfirst.getText();
					  // Do something with value!
					  pw1 = value.toString();
					  alert1done = true;
					  promptsecondPassword();
					  }
					});
				alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					  public void onClick(DialogInterface dialog, int whichButton) {
					    // Canceled.
					  }
					});
				alert.show();
				
				
			}});
		
		
	}
	
	public boolean promptresult()
	{
		if(pw1.equals(pw2))
		{
			password = pw2;
			//SAVE THE FILE NEW PASSWORD, GO BACK TO LOCK SCREEN
			
			
			try{
				userKey = new EncryptionKey(password);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			try{
				userKey.saveFile(myItems, userKey.getKey(), userKey.getCipher(), userKey.getDCipher(), getApplicationContext());
				Intent locknoFile = new Intent(ChangePassword.this, MainActivity.class);
				startActivity(locknoFile);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				
			}
			
			
			
			
			
			
			return true;
		}
		else
		{
			AlertDialog.Builder alert3 = new AlertDialog.Builder(ChangePassword.this);
			alert3.setTitle("ERROR");
			alert3.setMessage("The Passwords did not match. Please try again.");
			alert3.show();
		return false;
		}
	}
	
	public void promptsecondPassword(){
		AlertDialog.Builder alert2 = new AlertDialog.Builder(ChangePassword.this);
		alert2.setTitle("Confirm Password");
		alert2.setMessage("Please confirm password");
	final EditText passwordsecond = new EditText(ChangePassword.this);
	alert2.setView(passwordsecond);
	alert2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int whichButton) {
		  Editable value = passwordsecond.getText();
		  // Do something with value!
		  pw2 = value.toString();
		  correctpw = promptresult();
		  }
		});
	alert2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		  public void onClick(DialogInterface dialog, int whichButton) {
		    // Canceled.
		  }
		});


		alert2.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.change_password, menu);
		return true;
	}

}
