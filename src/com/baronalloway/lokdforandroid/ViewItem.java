package com.baronalloway.lokdforandroid;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

public class ViewItem extends Activity {
	String password;
	String selected;
	EncryptionKey userKey;
	List<WalletItem> myItems = new ArrayList<WalletItem>();
	WalletItem selectedItem;
	int a = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_item);
		
		
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
		
		
		//TODO: code to display the selected item using the ImageSer and a view
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_item, menu);
		return true;
	}

}
