package com.ilmi.rda;

import com.ilmi.rda.R;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class stuMyMentor extends Activity {
	private WebView webView;
	 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.smymentor);
 
		webView = (WebView) findViewById(R.id.webView2);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("http://54.250.213.210:82/WEB/MyMentor.html");
 

}

}
