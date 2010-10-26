package cn.edu.nju.software;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class PediaActivity extends Activity {

	private WebView mWebView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.pedia);
        Intent intent = getIntent();
        String search = intent.getStringExtra("search");
        
        mWebView = (WebView) findViewById(R.id.webview);

        /*
         * DemoJavaScriptInterface类为js调用android服务器端提供接口
         * android 作为DemoJavaScriptInterface类的客户端接口被js调用
         * 调用的具体方法在DemoJavaScriptInterface中定义：
         * 例如该实例中的clickOnAndroid
         */

        try {
			search = "http://pediault.appspot.com/search?type=1&page=2&wd=" + URLEncoder.encode(search, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(search);
		mWebView.loadUrl(search);
	}

}
