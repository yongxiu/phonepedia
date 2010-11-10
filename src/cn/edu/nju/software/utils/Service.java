package cn.edu.nju.software.utils;

import android.content.Intent;
import android.net.Uri;

public class Service {
	public static Intent SendMsg(String word) {
		//Uri smsUri = Uri.parse("tel:100861");
		Intent returnIt = new Intent(Intent.ACTION_VIEW, null);
		returnIt.putExtra("sms_body", "我向你推荐一个百科词汇：“" + word + "”，快查查看吧！");
		returnIt.setType("vnd.android-dir/mms-sms");
		
		return returnIt;
	}
	
	public static Intent SendMsgs(String word) {
		//Uri smsUri = Uri.parse("tel:100861");
		Intent returnIt = new Intent(Intent.ACTION_VIEW, null);
		returnIt.putExtra("sms_body", "我向你推荐一个百科词汇：“" + word + "”，快查查看吧！");
		returnIt.setType("vnd.android-dir/mms-sms");
		
		return returnIt;
	}
}
