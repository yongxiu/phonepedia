package cn.edu.nju.software;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import cn.edu.nju.software.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

public class PediaActivity extends Activity {

	private WebView mWebView;
	private String searchWd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.pedia);
        
        Intent intent = getIntent();
		searchWd = intent.getStringExtra("search");
        
        mWebView = (WebView) findViewById(R.id.webview);
		mWebView.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				return false;
			}
		});
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		String url = "";
        try {
        	url = "http://pediault.appspot.com/search?type=1&page=2&wd=" + URLEncoder.encode(searchWd, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mWebView.loadUrl(url);
		
		super.onResume();
	}

}
