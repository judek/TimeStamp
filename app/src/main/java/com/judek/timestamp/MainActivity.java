package com.judek.timestamp;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Toast;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;





public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);

        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
        final CheckBox checkBoxSeconds = (CheckBox) findViewById(R.id.checkBoxSeconds);
        final CheckBox checkBox24 = (CheckBox) findViewById(R.id.checkBox24);

        boolean checked = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("checkBox", false);
        checkBox.setChecked(checked);

        checked = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("checkBoxSeconds", false);
        checkBoxSeconds.setChecked(checked);

        checked = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("checkBox24", false);
        checkBox24.setChecked(checked);


        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TextView iv = (TextView) findViewById(R.id.textView);

                String strFormat = "";


                if (true == checkBox24.isChecked()) {
                    strFormat = "MM/dd/yyyy HH:mm";
                } else {
                    strFormat = "MM/dd/yyyy h:mm a";
                }

                if (true == checkBoxSeconds.isChecked()) {
                    //strFormat = "MM/dd/yyyy h:mm:ss a";
                    strFormat = strFormat.replace(":mm", ":mm:ss");
                }


                java.util.Date date = new java.util.Date();
                Timestamp ts = new Timestamp(date.getTime());
                String S = new SimpleDateFormat(strFormat).format(ts);
                iv.setText(S);

                if (true == checkBox.isChecked()) {

                    ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

                    //We need API 11
                    ClipData clip = ClipData.newPlainText("label", S);
                    clipboard.setPrimaryClip(clip);

                    Toast.makeText(v.getContext(), "Time stamp copied to clipboard", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void itemClicked(View v) {

        boolean checked = ((CheckBox) v).isChecked();

        switch(v.getId()) {
            case R.id.checkBox:
                PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putBoolean("checkBox", checked).commit();
                break;

            case R.id.checkBoxSeconds:
                PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putBoolean("checkBoxSeconds", checked).commit();
                break;

            case R.id.checkBox24:
                PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putBoolean("checkBox24", checked).commit();
                break;
        }

           }
}
