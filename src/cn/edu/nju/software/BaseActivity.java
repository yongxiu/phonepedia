package cn.edu.nju.software;

import java.util.Date;

import cn.edu.nju.software.db.DBAdapter;
import cn.edu.nju.software.utils.Service;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;

public class BaseActivity extends TabActivity {
	
	private String searchWd;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.base);// 这里使用了上面创建的xml文件（Tab页面的布局）
		
		Intent intent = getIntent();
		searchWd = intent.getStringExtra("search");
		
		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost
		TabSpec spec;

		// 第一个TAB
		intent = new Intent(this, DirectoryActivity.class);// 新建一个Intent用作Tab1显示的内容
		intent.putExtra("search", searchWd);
		spec = tabHost.newTabSpec("tab1")// 新建一个 Tab
				.setIndicator("目录",
						res.getDrawable(R.drawable.dict_tab_selected))// 设置名称以及图标
				.setContent(intent);// 设置显示的intent，这里的参数也可以是R.id.xxx
		tabHost.addTab(spec);// 添加进tabHost

		// 第二个TAB
		intent = new Intent(this, PediaActivity.class);// 第二个Intent用作Tab1显示的内容
		intent.putExtra("search", searchWd);
		spec = tabHost.newTabSpec("tab2")// 新建一个 Tab
				.setIndicator("内容",
						res.getDrawable(R.drawable.wiki_tab_selected))// 设置名称以及图标
				.setContent(intent);// 设置显示的intent，这里的参数也可以是R.id.xxx
		tabHost.addTab(spec);// 添加进tabHost
		
		// 第三个TAB
		intent = new Intent(this, FavoritesActivity.class);// 新建一个Intent用作Tab1显示的内容
		spec = tabHost.newTabSpec("tab3")// 新建一个 Tab
				.setIndicator("收藏夹",
						res.getDrawable(R.drawable.favorites))// 设置名称以及图标
				.setContent(intent);// 设置显示的intent，这里的参数也可以是R.id.xxx
		tabHost.addTab(spec);// 添加进tabHost
		tabHost.setCurrentTab(0);
		
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		getTabHost().setCurrentTab(1);
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
        	DBAdapter dbHelper = new DBAdapter(BaseActivity.this);
			dbHelper.open();
			if(dbHelper.insertFavorite(searchWd)) {
				Toast.makeText(BaseActivity.this, "添加成功！",
						Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(BaseActivity.this, "发生未知错误！",
						Toast.LENGTH_LONG).show();
			}
            return true;  
        case R.id.quit:  
        	System.exit(0);
            return true;
        case R.id.send:
        	startActivity(Service.SendMsg(searchWd));
        case R.id.about:
        	LayoutInflater factory = LayoutInflater.from(BaseActivity.this);
        	final View textEntryView = factory.inflate(R.layout.about_dlg, null);
        	AlertDialog dlg = new AlertDialog.Builder(BaseActivity.this)
        	.setTitle("关于-手机百科")
            .setView(textEntryView)
            .setPositiveButton("确定", null)
            .create();
        	dlg.show();
        	return true;
        default:  
            return super.onOptionsItemSelected(item);  
        } 
	}
}
