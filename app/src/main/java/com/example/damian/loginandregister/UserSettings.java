package com.example.damian.loginandregister;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Damian on 2017-03-02.
 */

public class UserSettings {

    public static final String SP_name = "userDetails";
    SharedPreferences userLocalDatabase;

    public UserSettings(Context context){

        userLocalDatabase = context.getSharedPreferences(SP_name, 0);

    }

    public void storeUserData(String username, String name, String email){

        SharedPreferences.Editor spEditor = userLocalDatabase.edit();

        spEditor.putString("LOGIN", username);
        spEditor.putString("NAME", name);
        spEditor.putString("EMAIL", email);

        spEditor.commit();


    }

    public User getLoggedInUser(){

        String myLogin = userLocalDatabase.getString("LOGIN", "");
        String myEmail = userLocalDatabase.getString("EMAIL", "");

        User storeUser = new User(myLogin, myEmail);
        return storeUser;
    }

    public void setLoggedIn(boolean loggedIn){

        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("LoggedIn", loggedIn);
        spEditor.commit();

    }
    public boolean loggInCheck(){

        if(userLocalDatabase.getBoolean("LoggedIn", true))
            return true;
        else
            return false;
    }

    public void clearUserData(){

        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();

    }

    public void writePicUrls(String[] myUrls){

        int num = 0;
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        for(int numOfElement = 0; numOfElement<myUrls.length; numOfElement++) {
            spEditor.putString("pic" + numOfElement, myUrls[numOfElement]);
        }
        spEditor.putInt("numOfPics", myUrls.length);
        spEditor.commit();

        int nnnum = num;
    }

    public int getNumOfPic(){
        int numOfPics = userLocalDatabase.getInt("numOfPics", 0);
        return numOfPics;
    }

    public String getPicUrls(int numOfElement){

        String picUrl = userLocalDatabase.getString("pic" + numOfElement, null);

        return picUrl;
    }


}

