package edu.columbia.ee.project1;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //added
    private ImageView imgView;  //the first ImageView in the view
    private ImageButton r_imgBtn, p_imgBtn, s_imgBtn;  // Rock, Paper, Scissors

    private TextView result_tv, count_tv;  //the textView of result and count
    int count = 0; // initialize the count

    //intialize a listener to monitoring the three buttons
    MyOnClickListener myOnClickListener = new MyOnClickListener();

    private MediaPlayer mp_background;
    private MediaPlayer mp_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //added

        //initialize buttons
        r_imgBtn = (ImageButton) findViewById(R.id.btnRock);
        p_imgBtn = (ImageButton) findViewById(R.id.btnPaper);
        s_imgBtn = (ImageButton) findViewById(R.id.btnSci);

        //initialize imgView
        imgView = (ImageView) findViewById(R.id.viewCmp);

        //initialize result and count TextView
        result_tv = (TextView) findViewById(R.id.textResult);
        count_tv = (TextView) findViewById(R.id.textCount);

        r_imgBtn.setOnClickListener(myOnClickListener);
        p_imgBtn.setOnClickListener(myOnClickListener);
        s_imgBtn.setOnClickListener(myOnClickListener);



        //declare the audio resource to these two MediaPlayer objects
        mp_background = MediaPlayer.create(this, R.raw.main);
        mp_button = MediaPlayer.create(this, R.raw.blaster);

        //play background music here
        mp_background.start();

    }

    //added
    private class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub

            //play button sound here
            mp_button.start();

            // get a random number form 1 to 3
            int rand = (int) (Math.random() * 3 + 1);
            count++;//

            //store times of game in device
//            storeData(count+"");

            switch (rand) {
                /**
                 * rand = 1 means computer is Rock,
                 * 2 represents Paper,
                 * 3 represents scissors
                 */
                case 1:
                    imgView.setImageResource(R.drawable.rock);  //computer choose Rock
                    switch (v.getId()) {
                        case R.id.btnRock:   //player choose Rock
                            result_tv.setText("Result: "
                                    + "Tied!");
                            count_tv.setText("Round: " + count);
                            break;
                        case R.id.btnPaper:  //player choose Paper
                            result_tv.setText("Result: "
                                    + "Win!");
                            count_tv.setText("Round: " + count);
                            break;
                        case R.id.btnSci:  //player choose Scissors
                            result_tv.setText("Result: "
                                    + "Lose!");
                            count_tv.setText("Round: " + count);
                            break;
                    }
                    break;
                case 2:
                    imgView.setImageResource(R.drawable.paper);  //computer choose Paper
                    switch (v.getId()) {
                        case R.id.btnRock:
                            result_tv.setText("Result: "
                                    + "Lose!");
                            count_tv.setText("Round: " + count);
                            break;
                        case R.id.btnPaper:
                            result_tv.setText("Result: "
                                    + "Tie!");
                            count_tv.setText("Round: " + count);
                            break;
                        case R.id.btnSci:
                            result_tv.setText("Result: "
                                    + "Win!");
                            count_tv.setText("Round: " + count);
                            break;
                    }
                    break;
                case 3:
                    imgView.setImageResource(R.drawable.scissors);  //computer choose Scissors
                    switch (v.getId()) {
                        case R.id.btnRock:
                            result_tv.setText("Result: "
                                    + "Win!");
                            count_tv.setText("Round: " + count);
                            break;
                        case R.id.btnPaper:
                            result_tv.setText("Result: "
                                    + "Lose!");
                            count_tv.setText("Round: " + count);
                            break;
                        case R.id.btnSci:
                            result_tv.setText("Result: "
                                    + "Tie!");
                            count_tv.setText("Round: " + count);
                            break;
                    }
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        mp_background.stop();
        super.onDestroy();
    }
}
