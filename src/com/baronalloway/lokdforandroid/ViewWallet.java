package com.baronalloway.lokdforandroid;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ViewWallet extends Activity {

	
	String password;
	EncryptionKey userKey;
	List<WalletItem> myItems = new ArrayList<WalletItem>();
	ListView walletList;
	ArrayAdapter<String> arrayAdapter;
	List<String> itemNames = new ArrayList<String>();
	String selectedName;
	Button addItemButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_wallet);
		walletList = (ListView)findViewById(R.id.listView1);
		addItemButton = (Button)findViewById(R.id.button1);
		
		
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
				startActivity(viewItemIntent);
				
				
			}});
		
		
		
		addItemButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				
				Intent goAddItem = new Intent(ViewWallet.this, ItemAdder.class);
				goAddItem.putExtra("password", password);
				startActivity(goAddItem);
				
				
			}});
		
		
	
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_wallet, menu);
		return true;
	}
	
	public void updateList()
	{
		for(int i = 0; i < myItems.size(); i++)
		{
			itemNames.add(myItems.get(i).getName());
		}
		
		arrayAdapter = new ArrayAdapter<String>(
                this, 
                android.R.layout.simple_list_item_1,
                itemNames);
	}

}
