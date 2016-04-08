/*  Author: Luke Burton & James Morrisey
    Purpose: Provide functionality to search activity
    Date: 21/02/2016
 */

package com.example.james.card_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.james.card_app.R.layout.activity_settings_menu;

public class SettingsMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        setContentView(activity_settings_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        onClickBackButtonListener();
        onVolumeSeekBarChange();
        onTextSizeSeekBarChange();
//        onFontSpinnerChange();
    }

    private void onClickBackButtonListener(){
        ImageButton btn = (ImageButton)findViewById(R.id.imageButton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void onTextSizeSeekBarChange() {
        int p=10;
        final int minimumValue = p;
        final TextView tutorialText = (TextView) findViewById(R.id.tutorialText);
        final SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        p = SP.getInt("key", p+minimumValue);
        tutorialText.setTextSize(p+minimumValue);
        final SeekBar sb = (SeekBar) findViewById(R.id.seekBar);
        sb.setProgress(SP.getInt("key", 0));
        sb.setMax(30);



        sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int progressChanged = progress;
                tutorialText.setTextSize(progressChanged+minimumValue);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }


            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                SharedPreferences.Editor edit = SP.edit();
                edit.putInt("key", sb.getProgress());
                edit.commit();
            }

        });
    }

    private void onVolumeSeekBarChange() {
        try {
            final SeekBar volumeSeekbar = (SeekBar) findViewById(R.id.seekBarVolume);
            final AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            volumeSeekbar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            volumeSeekbar.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
            volumeSeekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar volumeSeekbar, int progress, boolean fromUser) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                            progress, 0);
                }

                @Override
                public void onStartTrackingTouch(SeekBar volumeSeekbar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar volumeSeekbar) {

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    private void onFontSpinnerChange(){
//        final Spinner fontSpinner = (Spinner) findViewById(R.id.spinner);
//        final TextView fontText = (TextView)findViewById(R.id.tutorialText);
//        fontSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
//                Typeface customFont = Typeface.createFromAsset(getAssets(),"fonts/arial.ttf,calibri.ttf,cambria.ttc,tahoma.ttf,times.ttf");
//                fontText.setTypeface(customFont);
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });
//    }
}

