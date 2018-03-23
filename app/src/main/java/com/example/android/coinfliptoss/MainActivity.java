package com.example.android.coinfliptoss;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    private GestureDetector gestureDetector;

    private ImageView empty,arrow;
    private TextView slide;
    private ArrowHandler arrowHandler;

    private RadioGroup radio;
    private ImageView coin,match;
    private TextView prediction,outcome;
    private CoinHandler coinHandler;



    /* This function implements all basic functionality on the creation of the app*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onDetectGesture();
    }

    /* This function is used to detect gestures on the screen */
    private void onDetectGesture(){
        CustomGestureDetector customGestureDetector = new CustomGestureDetector();
        gestureDetector = new GestureDetector(this, customGestureDetector);

        /* Detect gestures on the arrow icon
         * Highly important to use an empty image view because onFling is not recognised on animation
         * with this method */

        empty = (ImageView) findViewById(R.id.empty_image_view);
        empty.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, final MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });

    }

    /* The internal class used to detect gestures */
    class CustomGestureDetector  extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {


            if (coinHandler.getAllowance()) {
                coinHandler.rollCoin();
                coinHandler.flipCoin();
                arrowHandler.stopArrow();
                slide.setText("");

            }
            return true;
        }

    }

    /* This function implements all function after the user interface is loaded */
    @Override
    protected void onStart(){
        super.onStart();
        init();
    }

    /* This function initialises process*/
    private void init(){

        empty = findViewById(R.id.empty_image_view);
        arrow = findViewById(R.id.arrow_image_view);
        coin = findViewById(R.id.coin_image_view);
        slide = findViewById(R.id.slide_text_view);
        match = findViewById(R.id.match_image_view);
        prediction = findViewById(R.id.predict_text_view);
        outcome = findViewById(R.id.outcome_text_view);
        radio = findViewById(R.id.predict_radio_group);

        arrowHandler = new ArrowHandler(arrow,slide);
        coinHandler = new CoinHandler(coin,match,prediction,outcome,radio);

    }

    /* This function starts process*/
    private void start(int predict){

        outcome.setText(null);
        match.setBackgroundResource(R.drawable.arrow_0);

        if (coinHandler.getAllowance()) {
            arrowHandler.animateArrow();
            coinHandler.setPrediction(predict);
        }


    }


    /* This function sets selected prediction */
    public void selectHeads (View view){
        start(0);

    }
    public void selectTails (View view) {

        start(1);
    }



}
