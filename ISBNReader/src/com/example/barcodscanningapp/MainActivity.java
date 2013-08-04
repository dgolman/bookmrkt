package com.example.barcodscanningapp;

import com.google.zxing.integration.android.IntentIntegrator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener{

	private Button postBtn;
	private Button searchBtn;
	private EditText searchField;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		postBtn = (Button)findViewById(R.id.list_button);
		postBtn.setOnClickListener(this);
		
		searchBtn = (Button)findViewById(R.id.search_button);
		searchBtn.setOnClickListener(this);
		
		searchField = (EditText)findViewById(R.id.search_field);
	}
	
	public void onClick(View v) {
		
		if(v.getId() == R.id.list_button){
			//scan
			Intent scanIntent = new Intent(this, ScanActivity.class);
			startActivity(scanIntent);

		}else if(v.getId() == R.id.search_button){
			if(!searchField.getText().toString().equals("")){
				//scan
				Intent webIntent = new Intent(this, WebActivity.class);
				webIntent.putExtra("keywords", searchField.getText().toString());
				startActivity(webIntent);
			}else {
				Toast toast = Toast.makeText(getApplicationContext(),
				        "You must enter a keyword", Toast.LENGTH_SHORT);
				    toast.show();
			}
			

		}
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
