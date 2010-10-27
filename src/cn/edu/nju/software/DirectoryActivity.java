package cn.edu.nju.software;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.edu.nju.software.R;

public class DirectoryActivity extends Activity {

	private LinearLayout linearLayout, publish, change, more;
	private List<String> groupArray;
	private List<List<String>> childArray;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.directory);
		Intent intent = getIntent();
		String search = intent.getStringExtra("search");

		linearLayout = (LinearLayout) findViewById(R.id.home);
		linearLayout.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				linearLayout
						.setBackgroundResource(R.drawable.tab_two_highlight);
				publish.setBackgroundResource(R.drawable.tab_one_normal);
				change.setBackgroundResource(R.drawable.tab_one_normal);
				more.setBackgroundResource(R.drawable.tab_one_normal);
			}
		});
		linearLayout.setBackgroundResource(R.drawable.tab_two_highlight);

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

		groupArray = new ArrayList<String>();
		childArray = new ArrayList<List<String>>();

		groupArray.add("第一行");
		groupArray.add("第二行");

		List<String> tempArray = new ArrayList<String>();
		tempArray.add("第一条");
		tempArray.add("第二条");
		tempArray.add("第三条");

		for (int index = 0; index < groupArray.size(); ++index) {
			childArray.add(tempArray);
		}
		
		ExpandableListView expandableListView = (ExpandableListView)findViewById(R.id.expandableListView);  
		expandableListView.setAdapter(new ExpandableAdapter(DirectoryActivity.this));  
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
			return getGenericView(string);
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
			text.setPadding(36, 0, 0, 0);
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
