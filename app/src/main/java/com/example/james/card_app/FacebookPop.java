package com.example.james.card_app;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.facebook.FacebookSdk;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;


/**
 * Created by Luke on 28/03/2016.
 */
public class FacebookPop extends AppCompatPreferenceActivity {

    private int mainWidth;
    private int mainHeight;
    final private double percentOfMainScreenWidth = .8;
    final private double percentOfMainScreenHeight = .8;

    private ShareButton shareButton;

    private Bitmap screenshot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_facebook_pop);
        FacebookSdk.sdkInitialize(getApplicationContext());

        getMainDimensions();
        setPopDimensions();

        onClickShareButtonListenter();
    }

    public void getMainDimensions() {
        DisplayMetrics dimensions = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dimensions);

        mainWidth = dimensions.widthPixels;
        mainHeight = dimensions.heightPixels;
    }

    public void setPopDimensions() {
        double popWidth = mainWidth * percentOfMainScreenWidth;
        double popHeight = mainHeight * percentOfMainScreenHeight;

        getWindow().setLayout((int) popWidth, (int) popHeight);
    }

    public void shareCongratulations() {
        /* Generate screenshot bitmap */
        View rootView = findViewById(android.R.id.content).getRootView();
        rootView.setDrawingCacheEnabled(true);

        screenshot = Bitmap.createBitmap(rootView.getDrawingCache());

        rootView.destroyDrawingCache();

        /* Share success */
        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(screenshot)
                .build();
        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();
        shareButton.setShareContent(content);
        shareButton.performClick();

    }

    public void onClickShareButtonListenter() {
        shareButton = (ShareButton) findViewById(R.id.fb_share_button);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareCongratulations();
            }
        });
    }

}
