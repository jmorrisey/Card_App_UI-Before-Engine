/*  Author: Luke Burton & James Morrisey
    Purpose: Provide functionality to search activity
    Date: 21/02/2016
 */

package com.example.james.card_app;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        onClickPlayButtonListener();
        onClickSettingsButtonListener();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        printHashKey();
    }

    public void onClickPlayButtonListener(){
        ImageButton btn = (ImageButton)findViewById(R.id.playbutton);

        final MediaPlayer riffleSoundPlayer = MediaPlayer.create(this, R.raw.test);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                riffleSoundPlayer.start();
                startActivity(new Intent(MainMenu.this, SearchMenu.class));
            }
        });
    }

    public void onClickSettingsButtonListener(){
        ImageButton sbtn = (ImageButton)findViewById(R.id.settingsbutton);

        sbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View s){
                startActivity(new Intent(MainMenu.this, SettingsMenu.class));
            }
        });
    }

    public void printHashKey() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.james.card_app",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("Facebook Hash Key", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        }catch (PackageManager.NameNotFoundException e) {
            System.out.println(e);
        }catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
    }
}
