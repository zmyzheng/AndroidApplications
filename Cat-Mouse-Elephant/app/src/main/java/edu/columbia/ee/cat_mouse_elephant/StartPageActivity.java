package edu.columbia.ee.cat_mouse_elephant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class StartPageActivity extends AppCompatActivity {

    private Button startBtn, HistoryBtn;
    //intialize a listener to monitoring two buttons
    MyOnClickListener myOnClickListener = new MyOnClickListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);


        //initialize button
        startBtn = (Button) findViewById(R.id.button_start);
        HistoryBtn = (Button) findViewById(R.id.button_history);
        // bind listener
        startBtn.setOnClickListener(myOnClickListener);
        HistoryBtn.setOnClickListener(myOnClickListener);

        // initial record
        this.initialRecord();
    }

    private class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.button_start:
                    // initial intent to start GameActivity
                    Intent intentStartGame = new Intent(v.getContext(), GameActivity.class);
                    startActivity(intentStartGame);
                    break;
                case R.id.button_history:
                    // initial intent to start HistoryActivity
                    Intent intentViewHistory = new Intent(v.getContext(), HistoryActivity.class);
                    startActivity(intentViewHistory);
                    break;
            }
        }
    }

    private void initialRecord(){
        // initial record to be 0
        SharedPreferences pref = getSharedPreferences("dataPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (!pref.contains("win")){
            editor.putInt("win", 0);
        }
        if (!pref.contains("tied")){
            editor.putInt("tied", 0);
        }
        if (!pref.contains("loss")){
            editor.putInt("loss", 0);
        }
        editor.apply();
    }
}