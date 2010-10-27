package cn.edu.nju.software;

import java.util.ArrayList;
import java.util.List;

import cn.edu.nju.software.R;
import cn.edu.nju.software.adapter.SearchAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ListView;

public class OneActivity extends Activity {

	private ListView listView;
	private ImageButton searchButton;
	private AutoCompleteTextView myAutoCompleteTextView;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		listView = (ListView) findViewById(R.id.ListView01);
		listView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, getData()));
		searchButton = (ImageButton) findViewById(R.id.ImageButton01);
		searchButton.setOnClickListener(new SearchListener());
		myAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.AutoCompleteTextView01);
		
		/* new一个自己实现做的BaseAdapter */
	    SearchAdapter adapter = new SearchAdapter(this);

	    myAutoCompleteTextView.setAdapter(adapter);
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
			System.out.println(myAutoCompleteTextView.getText().toString());
			intent.putExtra("search", myAutoCompleteTextView.getText().toString());
			intent.setClass(OneActivity.this, PediaActivity.class);
			OneActivity.this.startActivity(intent);
		}
		
	};
}