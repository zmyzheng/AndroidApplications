package edu.columbia.ee.cat_mouse_elephant;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HistoryActivity extends AppCompatActivity {

    private TextView textViewWin, textViewTied, textViewLose;
    private Button BtnClear;
    MyOnClickListener myOnClickListener = new MyOnClickListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // retrieve record and update view
        SharedPreferences pref = getSharedPreferences("dataPref", MODE_PRIVATE);
        textViewWin = (TextView) findViewById(R.id.tv_win);
        textViewTied = (TextView) findViewById(R.id.tv_tied);
        textViewLose = (TextView) findViewById(R.id.tv_lose);
        textViewWin.setText(String.format("Win : %d", pref.getInt("win", 0)));
        textViewTied.setText(String.format("Tied : %d", pref.getInt("tied", 0)));
        textViewLose.setText(String.format("Lose : %d", pref.getInt("lose", 0)));

        // inital button and set click listener
        BtnClear = (Button) findViewById(R.id.btn_clear);
        BtnClear.setOnClickListener(myOnClickListener);
    }

    private class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btn_clear:
                    // clear view
                    textViewWin.setText(String.format("Win : %d", 0));
                    textViewTied.setText(String.format("Tied : %d", 0));
                    textViewLose.setText(String.format("Lose : %d", 0));
                    // clear record
                    SharedPreferences pref = getSharedPreferences("dataPref", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putInt("win", 0);
                    editor.putInt("tied", 0);
                    editor.putInt("lose", 0);
                    editor.apply();
                    break;
            }
        }
    }
}
