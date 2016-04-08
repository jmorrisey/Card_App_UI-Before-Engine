/*Authors: Luke Burton & James Morrisey
  Purpose: Provide functionality for MainGame activity
  Date: 21/02/2016*/

package com.example.james.card_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.drawable.AnimationDrawable;


public class MainGame extends AppCompatActivity {
    /* Mascot */
    private ImageView mascotView;
    private AnimationDrawable frameAnimation;

    /* Widgets */
    private TextView tutorialText;
    private ImageButton backButton;
    private Button nextButton;

    /* Tutorial Text */
    private int currentText = 0;
    private String tutorialTextArray[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setContentView(R.layout.activity_main_game);

        tutorialTextArray = getResources().getStringArray(R.array.tutorialText);

        onClickBackButtonListener();

        setMovementTutorialText();

        onClickNextButtonListener();

        mascotSmilePointAnimation();

        updateTextSize();

        tutorialText.setText(tutorialTextArray[0]);
    }
    public void updateTextSize(){
        int p = 0;
        final SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        p = SP.getInt("key", p);
        tutorialText.setTextSize(p);
    }

    public void onClickBackButtonListener() {
        backButton = (ImageButton) findViewById(R.id.backbutton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainGame.this, SearchMenu.class));
            }
        });
    }

    public void mascotSmilePointAnimation(){
        mascotView=(ImageView)findViewById(R.id.frame_animation);
        mascotView.setBackgroundResource(R.drawable.frame_animation_list);
        frameAnimation=(AnimationDrawable)mascotView.getBackground();
        frameAnimation.start();
    }

    public void setMovementTutorialText() {
        tutorialText = (TextView) findViewById(R.id.tutorialText);

        tutorialText.setMovementMethod(new ScrollingMovementMethod());
    }

    public void onClickNextButtonListener() {
        nextButton = (Button) findViewById(R.id.nextButton);

        final MediaPlayer celebrationSoundPlayer = MediaPlayer.create(this, R.raw.test);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentText != tutorialTextArray.length-1){
                    /* Change text */
                    currentText += 1;
                    tutorialText.setText(tutorialTextArray[currentText]);

                    if (currentText == tutorialTextArray.length-1) {
                        /* End of tutorial */
                        celebrationSoundPlayer.start();

                        /* Pop up facebook share */
                        startActivity(new Intent(MainGame.this, FacebookPop.class));
                    }
                }
            }
        });
    }



}
