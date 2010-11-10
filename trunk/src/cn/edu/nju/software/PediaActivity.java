package cn.edu.nju.software;

import cn.edu.nju.software.R;
import cn.edu.nju.software.utils.Preferences;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

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
		
		Thread thread = new Thread(new ContentDownload());
		thread.start();
	}
	
	class ContentDownload implements Runnable{

		public void run() {
			String url = Preferences.getUrl(searchWd, Preferences.BAIDU_PEDIA, 2);
			mWebView.loadUrl(url);
		}
	}
}
