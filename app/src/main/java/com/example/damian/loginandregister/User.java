package com.example.damian.loginandregister;

/**
 * Created by Damian on 2017-03-02.
 */

public class User {

    protected String myLogin;
    protected String myName;
    protected String myEmail;


    public User(String myLogin, String myName, String myEmail){

        this.myEmail = myEmail;
        this.myLogin = myLogin;
        this.myName = myName;

    }

    public User(String myLogin, String myEmail){

        this.myLogin = myLogin;
        this.myEmail = myEmail;

    }


}
