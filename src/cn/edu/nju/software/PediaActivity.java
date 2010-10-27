package cn.edu.nju.software;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import cn.edu.nju.software.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.LinearLayout;

public class PediaActivity extends Activity {

	private WebView mWebView;
	private LinearLayout linearLayout, publish, change, more;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.pedia);
        Intent intent = getIntent();
        String search = intent.getStringExtra("search");
        
        mWebView = (WebView) findViewById(R.id.webview);

        linearLayout = (LinearLayout) findViewById(R.id.home);
		linearLayout.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				linearLayout
						.setBackgroundResource(R.drawable.tab_two_highlight);
				publish.setBackgroundResource(R.drawable.tab_one_normal);
				change.setBackgroundResource(R.drawable.tab_one_normal);
				more.setBackgroundResource(R.drawable.tab_one_normal);
				
				Intent intent = new Intent();
				intent.setClass(PediaActivity.this, DirectoryActivity.class);
				PediaActivity.this.startActivity(intent);
			}
		});
		linearLayout
		.setBackgroundResource(R.drawable.tab_two_highlight);

		publish = (LinearLayout) findViewById(R.id.publish);
		publish.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				publish.setBackgroundResource(R.drawable.tab_two_highlight);
				linearLayout.setBackgroundResource(R.drawable.tab_one_normal);
				change.setBackgroundResource(R.drawable.tab_one_normal);
				more.setBackgroundResource(R.drawable.tab_one_normal);
			}
		});

		change = (LinearLayout) findViewById(R.id.change);
		change.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				change.setBackgroundResource(R.drawable.tab_two_highlight);
				linearLayout.setBackgroundResource(R.drawable.tab_one_normal);
				publish.setBackgroundResource(R.drawable.tab_one_normal);
				more.setBackgroundResource(R.drawable.tab_one_normal);
			}
		});

		more = (LinearLayout) findViewById(R.id.more);
		more.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				more.setBackgroundResource(R.drawable.tab_two_highlight);
				linearLayout.setBackgroundResource(R.drawable.tab_one_normal);
				publish.setBackgroundResource(R.drawable.tab_one_normal);
				change.setBackgroundResource(R.drawable.tab_one_normal);
			}
		});
		

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
