package cn.edu.nju.software;

import java.util.ArrayList;
import java.util.List;

import cn.edu.nju.software.db.DBAdapter;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

public class FavoritesActivity extends Activity {
	
	private ListView listView;
	private LinearLayout linearLayout, publish, change, more;
	private String searchWd;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.favorites);
		
		Intent intent = getIntent();
		searchWd = intent.getStringExtra("search");
		
		linearLayout = (LinearLayout) findViewById(R.id.home);
		linearLayout.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				linearLayout
						.setBackgroundResource(R.drawable.tab_two_highlight);
				publish.setBackgroundResource(R.drawable.tab_one_normal);
				change.setBackgroundResource(R.drawable.tab_one_normal);
				more.setBackgroundResource(R.drawable.tab_one_normal);
				
				Intent intent = new Intent();
				intent.putExtra("search", searchWd);
				intent.setClass(FavoritesActivity.this, DirectoryActivity.class);
				FavoritesActivity.this.startActivity(intent);
			}
		});
		

		publish = (LinearLayout) findViewById(R.id.publish);
		publish.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				publish.setBackgroundResource(R.drawable.tab_two_highlight);
				linearLayout.setBackgroundResource(R.drawable.tab_one_normal);
				change.setBackgroundResource(R.drawable.tab_one_normal);
				more.setBackgroundResource(R.drawable.tab_one_normal);
				
				Intent intent = new Intent();
				intent.putExtra("search", searchWd);
				intent.setClass(FavoritesActivity.this, PediaActivity.class);
				FavoritesActivity.this.startActivity(intent);
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
		
		change.setBackgroundResource(R.drawable.tab_two_highlight);

		more = (LinearLayout) findViewById(R.id.more);
		more.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				more.setBackgroundResource(R.drawable.tab_two_highlight);
				linearLayout.setBackgroundResource(R.drawable.tab_one_normal);
				publish.setBackgroundResource(R.drawable.tab_one_normal);
				change.setBackgroundResource(R.drawable.tab_one_normal);
			}
		});
		
		listView = (ListView) findViewById(R.id.listView);
		listView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, getData()));
	}
	
	private List<String> getData(){
		List<String> data = new ArrayList<String>();
		DBAdapter dbHelper = new DBAdapter(FavoritesActivity.this);
		dbHelper.open();
		Cursor cursor = dbHelper.getAllPedias();
		while(cursor.moveToNext()) {
			String name = cursor.getString(cursor.getColumnIndex("name"));
			data.add(name);
		}

    	return data;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();  
        inflater.inflate(R.menu.menu, menu);  
        return true;  
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection  
        switch (item.getItemId())  
        {  
        case R.id.save:  
            System.out.println("save");
            return true;  
        case R.id.quit:  
        	System.exit(0);
            return true;  
        default:  
            return super.onOptionsItemSelected(item);  
        } 
	}
}
