package cn.edu.nju.software;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import cn.edu.nju.software.R;
import cn.edu.nju.software.adapter.SearchAdapter;
import cn.edu.nju.software.db.DBAdapter;
import cn.edu.nju.software.parse.PopWdParseXML;
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
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class OneActivity extends Activity {

	private ListView listView;
	private ImageButton searchButton;
	private ImageButton phoneButton;
	private AutoCompleteTextView myAutoCompleteTextView;
	private List<String> popWdList = new ArrayList<String>();
	
	public static final int VOICE_RECOGNITION_REQUEST_CODE = 0x1008;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		listView = (ListView) findViewById(R.id.ListView01);
		listView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, popWdList));
		searchButton = (ImageButton) findViewById(R.id.ImageButton01);
		searchButton.setOnClickListener(new SearchListener());
		phoneButton = (ImageButton) findViewById(R.id.ImageButton02);
		phoneButton.setOnClickListener(new PhoneListener());
		myAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.AutoCompleteTextView01);
		
		/* new一个自己实现做的BaseAdapter */
	    SearchAdapter adapter = new SearchAdapter(this);

	    myAutoCompleteTextView.setAdapter(adapter);
	    
	    //myAutoCompleteTextView.add
	    //Thread thread = new Thread(new PopWdDownload());
		//thread.start();
	}

	
	class SearchListener implements OnClickListener {

		public void onClick(View v) {
			
			Intent intent = new Intent();
			System.out.println(myAutoCompleteTextView.getText().toString());
			DBAdapter dbHelper = new DBAdapter(OneActivity.this);
			dbHelper.open();
			dbHelper.insertPedia(myAutoCompleteTextView.getText().toString(), new Date());

			intent.putExtra("search", myAutoCompleteTextView.getText().toString());
			intent.putExtra("type", Preferences.BAIDU_PEDIA);
			intent.setClass(OneActivity.this, BaseActivity.class);
			OneActivity.this.startActivity(intent);
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
				Toast.makeText(OneActivity.this, "未找到语音识别设备！",
						Toast.LENGTH_LONG).show();
			}
		}

	};
	
	class PopWdDownload implements Runnable{

		public void run() {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser;
			
			String uri = Preferences.getPopUrl(Preferences.BAIDU_PEDIA);

			URL pediaUrl = null;
			HttpURLConnection conn = null;
			InputStream is = null;


			/* 取得联机 */
			try {
				pediaUrl = new URL(uri);
				/* 开启联机 */
				conn = (HttpURLConnection) pediaUrl.openConnection();
				int code = conn.getResponseCode();
				/* 联机OK时 */
				if (code == HttpURLConnection.HTTP_OK) {
					/* 取得并传的InputStream */
					is = conn.getInputStream();
				}
				
				parser = factory.newSAXParser();
				PopWdParseXML parX = new PopWdParseXML(popWdList);
				parser.parse(is, parX);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if (is != null)
						is.close();
					if (conn != null)
						conn.disconnect();
				} catch (Exception e) {
				}
			}
			
			((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
		}
		
	}
}