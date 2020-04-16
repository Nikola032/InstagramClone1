package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("7NwVLia3TK4hVNLD7X3R4v93wt2pLwAH3mUixddO")
                .clientKey("0OrKhjq1j3RrWjwVx5YbpZ8P39bHcaE9k4jvZr4k")
                .server("https://parseapi.back4app.com/")
                .build()
        );

    }



}
