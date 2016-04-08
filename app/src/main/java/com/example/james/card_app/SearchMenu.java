/*  Author: Luke Burton & James Morrisey
    Purpose: Provide functionality to search activity
    Date: 21/02/2016
 */

package com.example.james.card_app;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

public class SearchMenu extends AppCompatActivity {

    // ListView
    ListView searchResults;

    // ListView adapter
    ArrayAdapter<GameListItem> adapter;

    // searchInput EditText
    EditText searchInput;

    //Image Buttons for navigation
    ImageButton settingsButton;
    ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set activity to landscape
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setContentView(R.layout.activity_search);
        onClickBackButtonListener();
        onClickSettingsButtonListener();
        initialiseSearchWidgets();

    }
    public void onClickSettingsButtonListener(){
        settingsButton = (ImageButton)findViewById(R.id.settingsbutton);

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View s) {
                startActivity(new Intent(SearchMenu.this, SettingsMenu.class));
            }
        });
    }

    public void onClickBackButtonListener(){
        backButton = (ImageButton)findViewById(R.id.backbutton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchMenu.this, MainMenu.class));
            }
        });
    }

    public void initialiseSearchWidgets(){
        searchResults = (ListView) findViewById(R.id.searchResults);
        searchInput = (EditText) findViewById(R.id.searchInput);

        // searchInput data
        GameListItem games[] = {
                new GameListItem("Blackjack", new Intent(SearchMenu.this, MainTutorial.class)),
                new GameListItem("Hearts", new Intent(SearchMenu.this, MainGame.class)),
                new GameListItem("Cheat", new Intent(SearchMenu.this, MainGame.class))
        };

        // Adding items to searchResults
        adapter = new ArrayAdapter<GameListItem>(this, R.layout.search_item, R.id.game_name, games);
        searchResults.setAdapter(adapter);

        setTextChangedSearchResultsListener();
        setOnItemClickSearchResultsListener();
    }


    public void setTextChangedSearchResultsListener(){

        //Search Filter
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                SearchMenu.this.adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void setOnItemClickSearchResultsListener(){
        final MediaPlayer riffleSoundPlayer = MediaPlayer.create(this, R.raw.test);
        searchResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                riffleSoundPlayer.start();

                GameListItem item = adapter.getItem(position);
                startActivity(item.getDestination());
            }
        });
    }

}

