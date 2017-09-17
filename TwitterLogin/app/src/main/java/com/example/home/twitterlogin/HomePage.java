package com.example.home.twitterlogin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;

public class HomePage extends AppCompatActivity {
    int tweetNumber;
    EditText numberOfTweets;
    RadioGroup group;
    RadioButton maxRadio, custRadio;
    Button tweetButton;
    boolean isCustomized=false;
    private String token ="",secret="";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.logout:

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                logoutUser();
                finish();
                break;
        }
        return true;
    }
    public void logoutUser(){
        String MY_PREFS_NAME = "MyPrefsFile";
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        boolean userIsLoggedIn = false;
        editor.putBoolean("userIsLoggedIn",false);
        editor.apply();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar()
        String username = getIntent().getStringExtra("username");
        this.token = getIntent().getStringExtra("accesstoken");
        this.secret = getIntent().getStringExtra("tokensecret");

        TextView tvUserName = (TextView)findViewById(R.id.textView);
        tvUserName.setText("Logged in as : "+username);
        try {
            numberOfTweets = (EditText) findViewById(R.id.editText);
        numberOfTweets.setVisibility(View.GONE);
            group = (RadioGroup) findViewById(R.id.radioGroup);
            tweetButton = (Button) findViewById(R.id.button);
            RadioButton maxRadio = (RadioButton) findViewById(R.id.radio_pirates);
            RadioButton custRadio = (RadioButton) findViewById(R.id.radio_ninjas);
            maxRadio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    numberOfTweets.setVisibility(View.GONE);
                    isCustomized = false;

                }
            });
            custRadio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    numberOfTweets.setVisibility(View.VISIBLE);
                    isCustomized = true;
                }
            });
//            group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
//                    switch (checkedId) {
//                        case R.id.radio_pirates:
//                            numberOfTweets.setVisibility(View.GONE);
//                            isCustomized = false;
//                            break;
//                        case R.id.radio_ninjas:
//                            numberOfTweets.setVisibility(View.VISIBLE);
//                            isCustomized = true;
//                            break;
//                    }
//                }
//            });
            tweetButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isCustomized) {
                        if (numberOfTweets.getText() != null) {
                            try {
                                tweetNumber = Integer.parseInt(numberOfTweets.getText().toString());
                                UpdateStatus a = new UpdateStatus();
                                a.execute();
//                                tweetNumber = 0;
                                long b =
                                        a.get();
                                Toast.makeText(getApplicationContext(), Long.toString(b), Toast.LENGTH_LONG).show();



                            } catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Enter some value to number of tweets field.", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        try {
                            UpdateStatus a = new UpdateStatus();
                            a.execute();
                            long b =
                                    a.get();
                            Toast.makeText(getApplicationContext(), Long.toString(b), Toast.LENGTH_LONG).show();

                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                }
            });
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
    public class UpdateStatus extends AsyncTask<Void, Void, Long> {

        @Override
        protected Long doInBackground(Void... params) {
            long statusId = 0;
            try {
                if(isCustomized){
                    for (int i=0;i < tweetNumber;i++){
                        AccessToken accessToken = new AccessToken(token, secret);

                        String consumerKey = getResources().getString(R.string.com_twitter_sdk_android_CONSUMER_KEY);
                        String consumerSecret = getResources().getString(R.string.com_twitter_sdk_android_CONSUMER_SECRET);
                        ConfigurationBuilder builder = new ConfigurationBuilder();
                        builder.setOAuthConsumerKey(consumerKey);
                        builder.setOAuthConsumerSecret(consumerSecret);
                        Twitter twitter = new TwitterFactory(builder.build()).getInstance(accessToken);
                        twitter4j.Status status = twitter.updateStatus("Status Test " + Math.random());
                        statusId = status.getId();
                    }

                }else {
                    while (true) {
                        AccessToken accessToken = new AccessToken(token, secret);

                        String consumerKey = getResources().getString(R.string.com_twitter_sdk_android_CONSUMER_KEY);
                        String consumerSecret = getResources().getString(R.string.com_twitter_sdk_android_CONSUMER_SECRET);
                        ConfigurationBuilder builder = new ConfigurationBuilder();
                        builder.setOAuthConsumerKey(consumerKey);
                        builder.setOAuthConsumerSecret(consumerSecret);
                        Twitter twitter = new TwitterFactory(builder.build()).getInstance(accessToken);
                        twitter4j.Status status = twitter.updateStatus("Again proved that Dhoni is back bone of indian cricket team." + Math.random());
                        statusId = status.getId();
                    }
                }

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
            return statusId;
        }
    }
//    @Override
//    public void onBackPressed() {
//
//    }

}
