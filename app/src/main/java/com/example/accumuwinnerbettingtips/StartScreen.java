package com.example.accumuwinnerbettingtips;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class StartScreen extends Activity {

	 protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);		
			setContentView(R.layout.startscreen);

			Button signIn = (Button)findViewById(R.id.btnSignIn);
			 signIn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i = new Intent(StartScreen.this, Login.class);
					startActivity(i);
					finish();
				}
			});

			 Button signUp = (Button)findViewById(R.id.btnSignUp);
			 signUp.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i = new Intent(StartScreen.this, Register.class);
					startActivity(i);
					finish();
				}
			});
}
}

