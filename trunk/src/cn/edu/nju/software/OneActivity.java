package cn.edu.nju.software;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class OneActivity extends Activity {

	private ListView listView;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		listView = (ListView) findViewById(R.id.ListView01);
		listView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, getData()));
	}

	private List<String> getData(){
		List<String> data = new ArrayList<String>();

    	data.add("测试数据1");

    	data.add("测试数据2");

    	data.add("测试数据3");

    	data.add("测试数据4");

    	return data;
	}
}