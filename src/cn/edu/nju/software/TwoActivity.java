package cn.edu.nju.software;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.nju.software.adapter.SearchAdapter;
import cn.edu.nju.software.db.DBAdapter;
import cn.edu.nju.software.utils.Preferences;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class TwoActivity extends Activity {
	private ListView listView;
	private ImageButton searchButton;
	private ImageButton phoneButton;
	private AutoCompleteTextView myAutoCompleteTextView;
	public static final int VOICE_RECOGNITION_REQUEST_CODE = 0x1008;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		listView = (ListView) findViewById(R.id.ListView01);
		listView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, getData()));
		searchButton = (ImageButton) findViewById(R.id.ImageButton01);
		searchButton.setOnClickListener(new SearchListener());
		phoneButton = (ImageButton) findViewById(R.id.ImageButton02);
		phoneButton.setOnClickListener(new PhoneListener());
		myAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.AutoCompleteTextView01);
		
		/* new一个自己实现做的BaseAdapter */
	    SearchAdapter adapter = new SearchAdapter(this);

	    myAutoCompleteTextView.setAdapter(adapter);
	    
	    //myAutoCompleteTextView.add
	}

	private List<String> getData(){
		List<String> data = new ArrayList<String>();

    	data.add("苹什么");

    	data.add("血荒");

    	data.add("透明国际");

    	data.add("马化腾");

    	return data;
	}
	
	class SearchListener implements OnClickListener {

		public void onClick(View v) {
			Intent intent = new Intent();
			System.out.println(myAutoCompleteTextView.getText().toString());
			DBAdapter dbHelper = new DBAdapter(TwoActivity.this);
			dbHelper.open();
			dbHelper.insertPedia(myAutoCompleteTextView.getText().toString(), new Date());

			intent.putExtra("search", myAutoCompleteTextView.getText().toString());
			intent.putExtra("type", Preferences.WIKI_PEDIA);
			intent.setClass(TwoActivity.this, BaseActivity.class);
			TwoActivity.this.startActivity(intent);
		}
		
	};

	class PhoneListener implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			PackageManager pm = getPackageManager();
			/* 查询有无安装Google Voice Search Engine */
			List<ResolveInfo> activities = pm.queryIntentActivities(new Intent(
					RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
			/* 若有安装Google Voice Search Engine */
			if (activities.size() != 0) {
				try {
					/* 语音识别Intent */
					Intent intent = new Intent(
							RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

					intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
							RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
					/* 识别画面出现的说明 */
					intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "语音识别");
					/* 启动语音识别Intent */
					startActivityForResult(intent,
							VOICE_RECOGNITION_REQUEST_CODE);

				} catch (Exception e) {
					myAutoCompleteTextView.setText(e.getMessage());
				}

			} else {
				Toast.makeText(TwoActivity.this, "未找到语音识别设备！",
						Toast.LENGTH_LONG).show();
			}
		}

	};
}