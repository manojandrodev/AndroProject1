package com.example.home.wakemethere;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.EditText;

public class SearchResultsDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results_display);

        EditText shared_search = (EditText) findViewById(R.id.search_field2);
        shared_search.setBackground(getResources().getDrawable(R.drawable.capsule));
    }
}
