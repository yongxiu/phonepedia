package cn.edu.nju.software;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.nju.software.db.DBAdapter;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class HistoryActivity extends ListActivity {
	
	private List<String> data;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.favorites);
		
		data = getData();
		this.setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, data));
		/*listView = (ListView) findViewById(R.id.listView);
		listView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, getData()));*/
	}
	
	@Override
	protected void onResume() {
		data = getData();
		((BaseAdapter) this.getListAdapter()).notifyDataSetChanged();
		super.onResume();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent();
		intent.putExtra("search", ((TextView) v).getText());
		intent.setClass(HistoryActivity.this, BaseActivity.class);
		HistoryActivity.this.startActivity(intent);
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
	}

	private List<String> getData(){
		List<String> data = new ArrayList<String>();
		DBAdapter dbHelper = new DBAdapter(HistoryActivity.this);
		dbHelper.open();
		Cursor cursor = dbHelper.getAllPedias();
		while(cursor.moveToNext()) {
			String name = cursor.getString(cursor.getColumnIndex("name"));
			data.add(name);
		}

    	return data;
	}
	
}
