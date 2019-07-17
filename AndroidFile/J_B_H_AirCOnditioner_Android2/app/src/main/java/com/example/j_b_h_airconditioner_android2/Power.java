package com.example.j_b_h_airconditioner_android2;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.view.View;

public class Power extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power);

        getSupportActionBar().setTitle("존뜨밝 에어컨");
        //액션바 배경색 변경
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF339999));
        //홈버튼 표시
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
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

    public void btn_Pow(View view) {
        //라디오 버튼
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup_Pow);
        int id = radioGroup.getCheckedRadioButtonId();

        RadioButton radioButton = (RadioButton) findViewById(id);
        Toast.makeText(this, "지금 에어컨을 " + radioButton.getText() + " 하겠습니다.", Toast.LENGTH_SHORT).show();
    }
}
