package cn.edu.nju.software;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ThreeActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        TextView textview = new TextView(this);
        textview.setText("This is the Artists tabThis is the Artists tab");
        setContentView(textview);
    }
}