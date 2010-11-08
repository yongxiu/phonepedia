package cn.edu.nju.software;

import java.util.ArrayList;
import java.util.List;

import cn.edu.nju.software.db.DBAdapter;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class HistoryActivity extends ListActivity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.favorites);
		
		this.setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, getData()));
		/*listView = (ListView) findViewById(R.id.listView);
		listView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, getData()));*/
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
