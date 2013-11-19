package com.sliit.rda;

import android.app.Activity;

import android.os.Bundle;
import android.webkit.WebView;

public class StuMyAccount extends Activity {
	
	private WebView webView;
	 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.smyaccount);
 
		webView = (WebView) findViewById(R.id.webView1);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("http://54.250.213.210:82/WEB/MyAccount.html");
 

}
}
