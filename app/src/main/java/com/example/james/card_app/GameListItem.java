/*  Author: Luke Burton & James Morrisey
    Purpose: Provide functionality to search activity
    Date: 21/02/2016
 */

package com.example.james.card_app;

import android.content.Intent;

public class GameListItem {
    private String name;
    private Intent destination;

    public GameListItem(){
        super();
    }

    public GameListItem(String name, Intent destination){
        super();
        this.name = name;
        this.destination = destination;
    }

    @Override
    public String toString(){
        return this.name;
    }

    public Intent getDestination(){
        return destination;
    }

}

