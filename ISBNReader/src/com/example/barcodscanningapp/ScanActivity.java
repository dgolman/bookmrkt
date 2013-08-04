package com.example.barcodscanningapp;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ScanActivity extends Activity implements OnClickListener{
	
	private Button scanBtn;
	private Button postBtn;
	private EditText isbnField;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scancontent);
		
		scanBtn = (Button)findViewById(R.id.scan_button);
		scanBtn.setOnClickListener(this);
		
		postBtn = (Button)findViewById(R.id.post_button);
		postBtn.setOnClickListener(this);
		
		isbnField = (EditText)findViewById(R.id.isbn_field);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		

		if(v.getId() == R.id.scan_button){
			//scan
			
			IntentIntegrator scanIntegrator = new IntentIntegrator(this);
			scanIntegrator.initiateScan();

		}else if(v.getId() == R.id.post_button){
			//scan
			
			if(!isbnField.getText().toString().equals("")){
				if(isbnField.getText().toString().length() < 9 || isbnField.getText().toString().length() > 13){
					Toast toast = Toast.makeText(getApplicationContext(),
					        "You must enter between either 9 or 13 digits!", Toast.LENGTH_SHORT);
					    toast.show();
				}else{
					final Context context = this;
					Intent webIntent = new Intent(context, WebActivity.class);
					webIntent.putExtra("isbn", isbnField.getText().toString());
					startActivity(webIntent);
				}
				
			}else {
				Toast toast = Toast.makeText(getApplicationContext(),
				        "You must enter an ISBN", Toast.LENGTH_SHORT);
				    toast.show();
			}
			
			
			
		}
		
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		final Context context = this;
		//retrieve scan result
		IntentResult scanningResult = IntentIntegrator.parseActivityResult(
									requestCode, resultCode, intent);
		if(scanningResult != null){
			//we have a result
			String scanContent = scanningResult.getContents();
			//String scanFormat = scanningResult.getFormatName();
			
			Intent webIntent = new Intent(context, WebActivity.class);
			webIntent.putExtra("isbn", scanContent);
			startActivity(webIntent);
		
			//formatTxt.setText("Format: " + scanFormat);
			//contentTxt.setText("Content: " + scanContent);
			
		}else {
			Toast toast = Toast.makeText(getApplicationContext(),
			        "No scan data received!", Toast.LENGTH_SHORT);
			    toast.show();
		}

	}

}
