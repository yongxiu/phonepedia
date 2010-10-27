package cn.edu.nju.software;


import cn.edu.nju.software.R;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class TabWidget extends TabActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab);// 这里使用了上面创建的xml文件（Tab页面的布局）
		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost
		TabSpec spec;
		Intent intent; // Reusable Intent for each tab

		// 第一个TAB
		intent = new Intent(this, OneActivity.class);// 新建一个Intent用作Tab1显示的内容
		spec = tabHost.newTabSpec("tab1")// 新建一个 Tab
				.setIndicator("Tab1",
						res.getDrawable(R.drawable.gimp))// 设置名称以及图标
				.setContent(intent);// 设置显示的intent，这里的参数也可以是R.id.xxx
		tabHost.addTab(spec);// 添加进tabHost

		// 第二个TAB
		intent = new Intent(this, TwoActivity.class);// 第二个Intent用作Tab1显示的内容
		spec = tabHost.newTabSpec("tab2")// 新建一个 Tab
				.setIndicator("Tab2",
						res.getDrawable(R.drawable.mumule))// 设置名称以及图标
				.setContent(intent);// 设置显示的intent，这里的参数也可以是R.id.xxx
		tabHost.addTab(spec);// 添加进tabHost
		
		// 第三个TAB
		intent = new Intent(this, ThreeActivity.class);// 新建一个Intent用作Tab1显示的内容
		spec = tabHost.newTabSpec("tab3")// 新建一个 Tab
				.setIndicator("Tab3",
						res.getDrawable(R.drawable.note))// 设置名称以及图标
				.setContent(intent);// 设置显示的intent，这里的参数也可以是R.id.xxx
		tabHost.addTab(spec);// 添加进tabHost
		tabHost.setBackgroundResource(R.drawable.back_blue);
		tabHost.setCurrentTab(0);

		final android.widget.TabWidget tabWidget = tabHost.getTabWidget();

		for (int i = 0; i < tabWidget.getChildCount(); i++) {

			tabWidget.getChildAt(i).getLayoutParams().height = 50;

		}
		
		
	}
}