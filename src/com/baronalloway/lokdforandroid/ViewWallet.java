package com.baronalloway.lokdforandroid;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class ViewWallet extends Activity {

	//TODO: create a settings menu with a change password option
	
	
	
	String password;
	EncryptionKey userKey;
	List<WalletItem> myItems = new ArrayList<WalletItem>();
	ListView walletList;
	ArrayAdapter<String> arrayAdapter;
	List<String> itemNames = new ArrayList<String>();
	String selectedName;
	ImageButton addItemButton;
	ImageButton lockWalletButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_wallet);
		walletList = (ListView)findViewById(R.id.listView1);
		addItemButton = (ImageButton)findViewById(R.id.addItemButton);
		lockWalletButton = (ImageButton)findViewById(R.id.lockbutton);
		
		setTitle("My Wallet");
		
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
		
		updateList();
		
		

        walletList.setAdapter(arrayAdapter); 
		
		walletList.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				
				selectedName = (String) walletList.getItemAtPosition(position);
				
				Intent viewItemIntent = new Intent(ViewWallet.this, ViewItem.class);
				viewItemIntent.putExtra("password", password);
				viewItemIntent.putExtra("selected", selectedName);
				Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fadeanim,R.anim.blankanim).toBundle();
				startActivity(viewItemIntent, bndlanimation);
				arrayAdapter.notifyDataSetChanged();
				userKey.saveFile(myItems, userKey.getKey(), userKey.getCipher(), userKey.getDCipher(), getApplicationContext());
				
			}});
		
		
		
		addItemButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				userKey.saveFile(myItems, userKey.getKey(), userKey.getCipher(), userKey.getDCipher(), getApplicationContext());
				Intent goAddItem = new Intent(ViewWallet.this, ItemAdder.class);
				goAddItem.putExtra("password", password);
				startActivityForResult(goAddItem, 1);
				
				
			}});
		
		
		lockWalletButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				userKey.saveFile(myItems, userKey.getKey(), userKey.getCipher(), userKey.getDCipher(), getApplicationContext());
				
				
				Intent lockActivities = new Intent(ViewWallet.this, MainActivity.class);
				Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.leftanim2,R.anim.leftanim).toBundle();
				lockActivities.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(lockActivities, bndlanimation);
				
				

			}});
		
		
	
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_wallet, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    Intent intent = new Intent(ViewWallet.this, ChangePassword.class);
	   intent.putExtra("password", password);
	    startActivity(intent);
	    return true;
	}
	
	
	
	
	public void updateList()
	{
		itemNames.clear();
		for(int i = 0; i < myItems.size(); i++)
		{
			itemNames.add(myItems.get(i).getName());
		}
		
		arrayAdapter = new ArrayAdapter<String>(
                this, 
                android.R.layout.simple_list_item_1,
                itemNames);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		
		Log.i("info", "MADE IT");
		try{
		WalletItem addedItem = (WalletItem) data.getExtras().get("new_item");
		
		
		myItems.add(addedItem);
		Log.i("info", "SAVING THE FILE WITH NEW WALLETITEM");
		userKey.saveFile(myItems, userKey.getKey(), userKey.getCipher(), userKey.getDCipher(), getApplicationContext());
		updateList();
		}
		catch(Exception e)
		{
			updateList();
		}
		
	}
	
	
	@Override
	public void onBackPressed() {
		
	}
	

}
