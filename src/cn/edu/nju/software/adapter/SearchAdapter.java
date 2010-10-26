package cn.edu.nju.software.adapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import cn.edu.nju.software.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SearchAdapter extends BaseAdapter implements Filterable {
	ArrayList<String> keyWordValue = new ArrayList<String>();
	ArrayList<String> resultValue = new ArrayList<String>();
	private Context mContext;
	LinearLayout.LayoutParams param1;

	public SearchAdapter(Context context) {
		mContext = context;

		param1 = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
	}

	public int getCount() {
		return keyWordValue.size();
	}

	public Object getItem(int position) {
		return keyWordValue.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View view, ViewGroup viewGroup) {
		LinearLayout myLinearLayout = new LinearLayout(mContext);
		myLinearLayout.setOrientation(LinearLayout.HORIZONTAL);

		if (position >= keyWordValue.size())
			return myLinearLayout;
		/* 第一个TextView放关键字 */
		TextView keyWordTextView = new TextView(this.mContext);
		keyWordTextView.setTextColor(mContext.getResources().getColor(
				R.drawable.blue));
		keyWordTextView.setTextSize(18);
		keyWordTextView.setWidth(180);
		try {
			keyWordTextView.setText(keyWordValue.get(position).toString());
		} catch (java.lang.IndexOutOfBoundsException i) {
			keyWordTextView.setText("");
		}
		/* 第二个TextView放关键字结果数量 */
		TextView resultTextView = new TextView(this.mContext);
		resultTextView.setTextColor(mContext.getResources().getColor(
				R.drawable.red));
		resultTextView.setTextSize(18);
		try {
			resultTextView.setText(resultValue.get(position).toString());
		} catch (java.lang.IndexOutOfBoundsException i) {
			resultTextView.setText("");
		}
		myLinearLayout.addView(keyWordTextView, param1);
		myLinearLayout.addView(resultTextView, param1);

		return myLinearLayout;
	}

	public Filter getFilter() {
		// TODO Auto-generated method stub
		Filter myFilter = new Filter() {

			@Override
			protected FilterResults performFiltering(CharSequence text) {

				FilterResults fr = new FilterResults();
				keyWordValue = new java.util.ArrayList<String>();
				resultValue = new java.util.ArrayList<String>();
				if (text == null || text.length() == 0) {
					fr.count = keyWordValue.size();
					fr.values = keyWordValue;
					return fr;
				}

				/* 输入关键字后调用google 关键字API */
				changeResult(getGoogleAPI(text.toString()));

				fr.count = keyWordValue.size();
				fr.values = keyWordValue;
				return fr;
			}

			@Override
			protected void publishResults(CharSequence text,
					FilterResults filterResults) {
				if (filterResults != null && filterResults.count > 0)
					notifyDataSetChanged();
				else
					notifyDataSetInvalidated();

			}
		};
		return myFilter;
	}

	/* 存取GoogleAPI取得并传的结果字符串 */
	private String getGoogleAPI(String text) {
		String uri = "";
		try {
			/* 输入的字要encode */
			uri = "http://www.google.cn/complete/" + "search?hl=en&js=true&qu="
					+ URLEncoder.encode(text, "utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		URL googleUrl = null;
		HttpURLConnection conn = null;
		InputStream is = null;
		BufferedReader in = null;
		String resultStr = "";
		/* 取得联机 */
		try {
			googleUrl = new URL(uri);
			/* 开启联机 */
			conn = (HttpURLConnection) googleUrl.openConnection();
			int code = conn.getResponseCode();
			/* 联机OK时 */
			if (code == HttpURLConnection.HTTP_OK) {
				/* 取得并传的InputStream */
				is = conn.getInputStream();

				in = new BufferedReader(new InputStreamReader(is));
				String inputLine;

				/* 几行几行读取 */
				while ((inputLine = in.readLine()) != null) {
					resultStr += inputLine;
				}

			}
		} catch (IOException e) {
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

		return resultStr;
	}

	/* 处理并传的字符串变成ArrayList */
	private void changeResult(String text) {
		int start = text.indexOf("[\"", 1);
		int end = 0;

		while ((start = text.indexOf("[\"", start + 1)) != -1) {
			end = text.indexOf("\"", start + 2);
			keyWordValue.add(text.substring(start + 2, end));
		}

	}

}
