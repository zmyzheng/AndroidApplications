package edu.columbia.ee.cat_mouse_elephant;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    private ImageView playerImgView, computerImgView;
    private Button mouseBtn, catBtn, elephantBtn;

    private TextView resultTextView, roundTextView;
    int count = 0; // initialize the count

    //intialize a listener to monitoring the three buttons
    MyOnClickListener myOnClickListener = new MyOnClickListener();

    private MediaPlayer mp_background;
    private MediaPlayer mp_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //initialize button
        mouseBtn = (Button) findViewById(R.id.mouseBtn);
        catBtn = (Button) findViewById(R.id.catBtn);
        elephantBtn = (Button) findViewById(R.id.elephantBtn);
        //set click listener
        mouseBtn.setOnClickListener(myOnClickListener);
        catBtn.setOnClickListener(myOnClickListener);
        elephantBtn.setOnClickListener(myOnClickListener);

        //initialize imgView
        playerImgView = (ImageView) findViewById(R.id.playerImgView);
        computerImgView = (ImageView) findViewById(R.id.computerImgView);

        //initialize result and count TextView
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        roundTextView = (TextView) findViewById(R.id.roundTextView);



        //declare the audio resource to these two MediaPlayer objects
        mp_background = MediaPlayer.create(this, R.raw.main);
        mp_button = MediaPlayer.create(this, R.raw.blaster);

        //play background music here
        mp_background.start();
    }

    @Override
    protected void onDestroy() {
        mp_background.stop();
        super.onDestroy();
    }

    private class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //play button sound here
            mp_button.start();

            // get a random number form 1 to 3
            int computer = (int) (Math.random() * 3);
            count++;//

            int player = 0;

            switch(v.getId()){
                case R.id.mouseBtn:
                    player = 0;
                    playerImgView.setImageResource(R.drawable.mouse);
                    break;
                case R.id.catBtn:
                    player = 1;
                    playerImgView.setImageResource(R.drawable.cat);
                    break;
                case R.id.elephantBtn:
                    player = 2;
                    playerImgView.setImageResource(R.drawable.elephant);
                    break;
            }

            switch(computer){
                case 0:
                    computerImgView.setImageResource(R.drawable.mouse);
                    break;
                case 1:
                    computerImgView.setImageResource(R.drawable.cat);
                    break;
                case 2:
                    computerImgView.setImageResource(R.drawable.elephant);
                    break;
            }

            if(computer == player){
                resultTextView.setText("Result: " + "Tied!");
                roundTextView.setText("Round: " + count);
                this.putToRecord("tied");
            }
            else if(computer == player + 1 || computer == player -2){
                resultTextView.setText("Result: " + "Lose!");
                roundTextView.setText("Round: " + count);
                this.putToRecord("lose");
            }
            else{
                resultTextView.setText("Result: " + "Win!");
                roundTextView.setText("Round: " + count);
                this.putToRecord("win");
            }

        }

        private void putToRecord(String res) {
            SharedPreferences pref = getSharedPreferences("dataPref", MODE_PRIVATE);
            // create SharedPreferences.Editor to modify data
            SharedPreferences.Editor editor = pref.edit();
            editor.putInt(res, pref.getInt(res, 0)+1);
            editor.apply();
        }
    }


}
