package com.example.j_b_h_airconditioner_android2;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;
import android.os.Handler;


public class MainActivity extends AppCompatActivity implements TimePicker.OnTimeChangedListener {
//    private String html = "";
//    private Handler mHandler;
//
//    private Socket socket;
//
//    private BufferedReader networkReader;
//    private BufferedWriter networkWriter;
//
//    private String ip = "192.168.137.140"; // IP
//    private int port = 8090; // PORT번호
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        try {
//            socket.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    TimePicker tp;
    int nHour, nMin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mHandler = new Handler();

        getSupportActionBar().setTitle("존뜨밝 에어컨");
        //액션바 배경색 설정
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF339999));
        //홈버튼 표시
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //타임피커
        tp = (TimePicker) findViewById(R.id.timePicker);
        tp.setIs24HourView(true);
        tp.setOnTimeChangedListener(this);


    }


    @Override
    public void onTimeChanged(TimePicker timePicker, int hour, int min) {
        nHour = hour;
        nMin = min;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    //예약 버튼을 누르면
    public void btn_Res(View view) {
        //라디오 버튼
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup_Time);
        int id = radioGroup.getCheckedRadioButtonId();

        RadioButton radioButton = (RadioButton) findViewById(id);
        Toast.makeText(this, nHour + "시 " + nMin + "분, 에어컨을 " + radioButton.getText() + " 하겠습니다.", Toast.LENGTH_SHORT).show();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.act_Time) {
            Toast.makeText(this, "시간을 설정하러 갑니다.", Toast.LENGTH_SHORT).show();
            Intent timeIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(timeIntent);
            return true;
        }
        if (id == R.id.act_Power) {
            Toast.makeText(this, "ON/OFF를 설정하러 갑니다.", Toast.LENGTH_SHORT).show();
            Intent powerIntent = new Intent(getApplicationContext(), Power.class);
            startActivity(powerIntent);
            return true;
        }
        if (id == R.id.act_Tem) {
            Toast.makeText(this, "온도를 설정하러 갑니다.", Toast.LENGTH_SHORT).show();
            Intent temIntent = new Intent(getApplicationContext(), Temperate.class);
            startActivity(temIntent);
            return true;
        }
        if (id == R.id.act_Timer) {
            Toast.makeText(this, "타이머를 설정하러 갑니다.", Toast.LENGTH_SHORT).show();
            Intent temIntent = new Intent(getApplicationContext(), Timer.class);
            startActivity(temIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
