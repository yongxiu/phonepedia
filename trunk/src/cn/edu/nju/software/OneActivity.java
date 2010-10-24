package cn.edu.nju.software;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class OneActivity extends Activity {

	private ListView listView;
	private ImageButton searchButton;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		listView = (ListView) findViewById(R.id.ListView01);
		listView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, getData()));
		searchButton = (ImageButton) findViewById(R.id.ImageButton01);
		searchButton.setOnClickListener(new SearchListener());
	}

	private List<String> getData(){
		List<String> data = new ArrayList<String>();

    	data.add("测试数据1");

    	data.add("测试数据2");

    	data.add("测试数据3");

    	data.add("测试数据4");

    	return data;
	}
	
	class SearchListener implements OnClickListener {

		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(OneActivity.this, PediaActivity.class);
			OneActivity.this.startActivity(intent);
		}
		
	};
}