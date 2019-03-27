package com.example.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AboutAuthor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_author);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" TASKINATOR");
        getSupportActionBar().setIcon(R.drawable.taskinator32);
    }
}
