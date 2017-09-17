package com.example.home.twitterlogin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import twitter4j.auth.AccessToken;

public class MainActivity extends AppCompatActivity {

    TwitterLoginButton loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Twitter.initialize(this);
        setContentView(R.layout.activity_main);

        String MY_PREFS_NAME = "MyPrefsFile";


        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        if(prefs.getBoolean("userIsLoggedIn",true)){
            TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
            TwitterAuthToken authToken = session.getAuthToken();
            String token = authToken.token;
            String secret = authToken.secret;
            login(session,token,secret);
        }


        loginButton = (TwitterLoginButton) findViewById(R.id.login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // Do something with result, which provides a TwitterSession for making API calls
                TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
                TwitterAuthToken authToken = session.getAuthToken();
                String token = authToken.token;
                String secret = authToken.secret;
                loginUser();
                login(session,token,secret);
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
//                editor.putBoolean("userIsLoggedIn",false);
//                editor.apply();
                Toast.makeText(getApplicationContext(), exception.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
    public void login(TwitterSession session,String token,String secret){
        String username = session.getUserName();
        Intent homePageIntent = new Intent(getApplicationContext(),HomePage.class);
        homePageIntent.putExtra("username",username).putExtra("accesstoken",token).putExtra("tokensecret",secret);
        startActivity(homePageIntent);
        finish();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result to the login button.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }

    public void loginUser(){
        String MY_PREFS_NAME = "MyPrefsFile";
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        boolean userIsLoggedIn = false;
        editor.putBoolean("userIsLoggedIn",true);
        editor.apply();
    }
    public void logoutUser(){
        String MY_PREFS_NAME = "MyPrefsFile";
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        boolean userIsLoggedIn = false;
        editor.putBoolean("userIsLoggedIn",false);
        editor.apply();
    }
}
