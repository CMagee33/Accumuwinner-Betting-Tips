package com.example.accumuwinnerbettingtips;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Home extends Activity{
	
	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.home);
		Bundle b = getIntent().getExtras();
		String email = b.getString("email");
		
		final TextView uname = (TextView) findViewById(R.id.storedUser);
		uname.setText(email);
		}

}
