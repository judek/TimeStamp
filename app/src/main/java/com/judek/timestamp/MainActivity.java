package com.judek.timestamp;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
        final EditText editText = (EditText) findViewById(R.id.editText);

        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
        final CheckBox checkBoxSeconds = (CheckBox) findViewById(R.id.checkBoxSeconds);
        final CheckBox checkBox24 = (CheckBox) findViewById(R.id.checkBox24);
        final CheckBox checkBoxHyphen = (CheckBox) findViewById(R.id.checkBoxHyphen);
        final CheckBox checkBoxUnderScore = (CheckBox) findViewById(R.id.checkBoxUnderScore);
        final CheckBox checkBoxManual = (CheckBox) findViewById(R.id.checkBoxManual);
        final TextView textViewHelp = (TextView)  findViewById(R.id.textViewHelp);

        textViewHelp.setText( Html.fromHtml(
                "<a href=\"http://judek.com/DateTimePatterns.html\">Advanced formatting help</a>"));
        textViewHelp.setMovementMethod(LinkMovementMethod.getInstance());

        boolean checked = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("checkBox", false);
        checkBox.setChecked(checked);

        checked = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("checkBoxSeconds", false);
        checkBoxSeconds.setChecked(checked);

        checked = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("checkBox24", false);
        checkBox24.setChecked(checked);

        checked = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("checkBoxHyphen", false);
        checkBoxHyphen.setChecked(checked);

        checked = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("checkBoxUnderScore", false);
        checkBoxUnderScore.setChecked(checked);

        checked = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("checkBoxManual", false);
        checkBoxManual.setChecked(checked);

        editText.setEnabled(checked);

        if(checked) {
            editText.setBackgroundColor(Color.WHITE);
            textViewHelp.setVisibility(View.VISIBLE);
        }
        else {
            editText.setBackgroundColor(Color.argb(0x00, 0x00, 0x66, 0x99));
            textViewHelp.setVisibility(View.INVISIBLE);
        }


        String S = PreferenceManager.getDefaultSharedPreferences(this)
                .getString("editText", "");

        editText.setText(S);




        int idx = PreferenceManager.getDefaultSharedPreferences(this)
               .getInt("DateFormatIndex", 1);

        RadioButton radio = null;

        switch(idx) {
            case 1:
                radio = (RadioButton) findViewById(R.id.radioButtonUSA);
                break;
            case 2:
                radio = (RadioButton) findViewById(R.id.radioButtonEurope);
                break;
            case 3:
                radio = (RadioButton) findViewById(R.id.radioButtonISO);
                break;
        }

        radio.setChecked(true);

        //button.setOnClickListener(new OnClickListener() { public void onClick(View v) {  } });


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

            case R.id.checkBoxHyphen:
                PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putBoolean("checkBoxHyphen", checked).commit();
                break;

            case R.id.checkBoxUnderScore:
                PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putBoolean("checkBoxUnderScore", checked).commit();
                break;

            case R.id.checkBoxManual:
                PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putBoolean("checkBoxManual", checked).commit();


                final EditText editText = (EditText) findViewById(R.id.editText);

                editText.setEnabled(checked);
                final TextView textViewHelp = (TextView)  findViewById(R.id.textViewHelp);

                if(checked) {
                    editText.setBackgroundColor(Color.WHITE);
                    textViewHelp.setVisibility(View.VISIBLE);
                }
                else {
                    editText.setBackgroundColor(Color.argb(0x00, 0x00, 0x66, 0x99));
                    textViewHelp.setVisibility(View.INVISIBLE);
                }
                break;
        }

           }

    public void buttonClicked(View v){

        TextView iv = (TextView) findViewById(R.id.textView);
        final EditText editText = (EditText) findViewById(R.id.editText);
        final CheckBox checkBoxManual = (CheckBox) findViewById(R.id.checkBoxManual);

        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.RadioGroupDateFormat);
        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
        final CheckBox checkBoxSeconds = (CheckBox) findViewById(R.id.checkBoxSeconds);
        final CheckBox checkBox24 = (CheckBox) findViewById(R.id.checkBox24);
        final CheckBox checkBoxHyphen = (CheckBox) findViewById(R.id.checkBoxHyphen);
        final CheckBox checkBoxUnderScore = (CheckBox) findViewById(R.id.checkBoxUnderScore);


        int radioButtonID = radiogroup.getCheckedRadioButtonId();
        View radioButton = radiogroup.findViewById(radioButtonID);
        int idx = radiogroup.indexOfChild(radioButton);

        String strYearFormat = "";

        String strFormat = "";

        switch(idx) {
            case 1:
                strYearFormat = "MM/dd/yyyy";
                break;
            case 2:
                strYearFormat = "dd/MM/yyyy";
                break;
            case 3:
                strYearFormat = "yyyy/MM/dd";
                break;
        }

        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit()
                .putInt("DateFormatIndex", idx).commit();


        if (true == checkBox24.isChecked()) {
            strFormat = strYearFormat + " HH:mm";
        } else {
            strFormat = strYearFormat + " hh:mm a";
        }

        if (true == checkBoxSeconds.isChecked()) {
            strFormat = strFormat.replace(":mm", ":mm:ss");
        }

        if (true == checkBoxHyphen.isChecked()) {
            strFormat = strFormat.replace("/", "-");
        }

        if (true == checkBoxUnderScore.isChecked()) {
            strFormat = strFormat.replace(":", "_");
            strFormat = strFormat.replace(" ", "_");
        }

        if (true == checkBoxManual.isChecked())
            strFormat = editText.getText().toString();


        java.util.Date date = new java.util.Date();
        Timestamp ts = new Timestamp(date.getTime());
        String S;

        try { S = new SimpleDateFormat(strFormat).format(ts);}
        catch(Exception e)
        {
            Toast.makeText(v.getContext(), "Error:" + e.getMessage(), Toast.LENGTH_LONG)
                    .show();
            return;
        }


        iv.setText(S);

        if (true == checkBox.isChecked()) {

            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

            //We need API 11
            ClipData clip = ClipData.newPlainText("label", S);
            clipboard.setPrimaryClip(clip);

            Toast.makeText(v.getContext(), "Time stamp copied to clipboard", Toast.LENGTH_LONG)
                    .show();
        }

        editText.setText(strFormat);

        PreferenceManager.getDefaultSharedPreferences(this).edit()
                .putString("editText", strFormat).commit();
    }
}
