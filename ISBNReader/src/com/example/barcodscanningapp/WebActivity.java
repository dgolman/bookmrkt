package com.example.barcodscanningapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

public class WebActivity extends Activity {
	private WebView webView;

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.webcontent);
		
		webView = (WebView) findViewById(R.id.webView);
		webView.getSettings().setJavaScriptEnabled(true);
		
		Intent intent = getIntent();
		String isbn = "";
		String keywords = "";
		
		if(intent.getStringExtra("isbn") != null){
			isbn = intent.getStringExtra("isbn");
		}
		
		if(intent.getStringExtra("keywords") != null){
			keywords = intent.getStringExtra("keywords");
		}
		
		
		if(!isbn.equals("")){
			webView.loadUrl("http://www.bookmrkt.com/step2.php?isbn=" + isbn);
		}else if(!keywords.equals("")){
			webView.loadUrl("http://www.bookmrkt.com/buy.php?s=" + keywords);
		}
		

	}
}
