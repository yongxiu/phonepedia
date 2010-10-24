package cn.edu.nju.software;


import android.app.Activity;
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
        mWebView = (WebView) findViewById(R.id.webview);

        /*
         * DemoJavaScriptInterface类为js调用android服务器端提供接口
         * android 作为DemoJavaScriptInterface类的客户端接口被js调用
         * 调用的具体方法在DemoJavaScriptInterface中定义：
         * 例如该实例中的clickOnAndroid
         */

        mWebView.loadUrl("http://pediault.appspot.com/search?type=1&wd=%E4%B8%AD%E5%9B%BD&page=2");
        System.out.println("hel");
	}

}
