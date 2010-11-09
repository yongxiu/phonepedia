package cn.edu.nju.software;


import cn.edu.nju.software.R;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
				.setIndicator("百度百科",
						res.getDrawable(R.drawable.tran_tab_selected))// 设置名称以及图标
				.setContent(intent);// 设置显示的intent，这里的参数也可以是R.id.xxx
		tabHost.addTab(spec);// 添加进tabHost

		// 第二个TAB
		intent = new Intent(this, TwoActivity.class);// 第二个Intent用作Tab1显示的内容
		spec = tabHost.newTabSpec("tab2")// 新建一个 Tab
				.setIndicator("维基百科",
						res.getDrawable(R.drawable.wikipedia_globe_icon))// 设置名称以及图标
				.setContent(intent);// 设置显示的intent，这里的参数也可以是R.id.xxx
		tabHost.addTab(spec);// 添加进tabHost
		
		// 第三个TAB
		intent = new Intent(this, HistoryActivity.class);// 新建一个Intent用作Tab1显示的内容
		spec = tabHost.newTabSpec("tab3")// 新建一个 Tab
				.setIndicator("搜索历史",
						res.getDrawable(R.drawable.clock))// 设置名称以及图标
				.setContent(intent);// 设置显示的intent，这里的参数也可以是R.id.xxx
		tabHost.addTab(spec);// 添加进tabHost
		tabHost.setBackgroundResource(R.drawable.back_blue);
		tabHost.setCurrentTab(0);
		
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