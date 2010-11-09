package cn.edu.nju.software;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ExpandableListView.ExpandableListContextMenuInfo;
import cn.edu.nju.software.parse.ParseXML;
import cn.edu.nju.software.utils.Preferences;

public class DirectoryActivity extends ExpandableListActivity {

	private List<String> groupArray;
	private List<List<String>> childArray;
	private String searchWd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.directory);

		Intent intent = getIntent();
		searchWd = intent.getStringExtra("search");
		this.getExpandableListView().setGroupIndicator(null);
		
		// TODO Auto-generated method stub
		groupArray = new ArrayList<String>();
		childArray = new ArrayList<List<String>>();


		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser;
		
		String uri = Preferences.getUrl(searchWd, Preferences.BAIDU_PEDIA, 1);

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
			ParseXML parX = new ParseXML(groupArray, childArray);
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

		
		this.setListAdapter(new ExpandableAdapter(this));
		
		registerForContextMenu(getExpandableListView());
	}

	public boolean onContextItemSelected(MenuItem item) {
		boolean flag=false;
		// TODO Auto-generated method stub
		ExpandableListContextMenuInfo menuInfo=(ExpandableListContextMenuInfo)item.getMenuInfo();
		String title=((TextView)menuInfo.targetView).getText().toString();
		
		Toast.makeText(this, title, Toast.LENGTH_SHORT).show();
		return true;
	}
	// ExpandableListView的Adapter
	public class ExpandableAdapter extends BaseExpandableListAdapter {
		Activity activity;

		public ExpandableAdapter(Activity a) {
			activity = a;
		}

		public Object getChild(int groupPosition, int childPosition) {
			return childArray.get(groupPosition).get(childPosition);
		}

		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		public int getChildrenCount(int groupPosition) {
			return childArray.get(groupPosition).size();
		}

		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			String string = childArray.get(groupPosition).get(childPosition);
			
			AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(
					ViewGroup.LayoutParams.FILL_PARENT, 64);
			TextView text = new TextView(activity);
			text.setLayoutParams(layoutParams);
			// Center the text vertically
			text.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
			// Set the text starting position
			text.setPadding(40, 0, 0, 0);
			text.setText(string);
			
			return text;
		}

		// group method stub
		public Object getGroup(int groupPosition) {
			return groupArray.get(groupPosition);
		}

		public int getGroupCount() {
			return groupArray.size();
		}

		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			String string = groupArray.get(groupPosition);
			return getGenericView(string);
		}

		// View stub to create Group/Children 's View
		public TextView getGenericView(String string) {
			// Layout parameters for the ExpandableListView
			AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(
					ViewGroup.LayoutParams.FILL_PARENT, 64);
			TextView text = new TextView(activity);
			text.setLayoutParams(layoutParams);
			// Center the text vertically
			text.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
			// Set the text starting position
			text.setPadding(20, 0, 0, 0);
			text.setText(string);
			return text;
		}

		public boolean hasStableIds() {
			return false;
		}

		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}
	}
	
}
