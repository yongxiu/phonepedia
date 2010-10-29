package cn.edu.nju.software;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class BaseActivity extends Activity {
	
	private LinearLayout linearLayout, publish, change, more;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.directory);
	}
}
