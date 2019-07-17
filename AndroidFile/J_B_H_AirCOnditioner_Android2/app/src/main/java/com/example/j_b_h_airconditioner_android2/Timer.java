package com.example.j_b_h_airconditioner_android2;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Timer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        getSupportActionBar().setTitle("존뜨밝 에어컨");
        //액션바 배경색 설정
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF339999));
        //홈버튼 표시
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void btn_Timer(View view) {
        EditText editTexth = (EditText)findViewById(R.id.edt_hour);
        EditText editTextm = (EditText)findViewById(R.id.edt_min);

        Toast.makeText(this, "집을 비운지 " + editTexth.getText() + "시간 " + editTextm.getText() + "분 후에 에어컨을 끕니다.", Toast.LENGTH_SHORT).show();
    }
    public boolean onOptionsItemSelected(MenuItem item){
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
