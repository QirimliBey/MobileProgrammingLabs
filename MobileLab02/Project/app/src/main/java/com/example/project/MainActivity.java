package com.example.project;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ToolbarWidgetWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import java.time.Instant;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Intent myIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" TASKINATOR");
        getSupportActionBar().setIcon(R.drawable.taskinator32);



        Button button = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);

        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.button:
                Toast.makeText(this, "GO TO WEB APPLICATION clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button2:
                Toast.makeText(this, "ABOUT APPLICATION clicked", Toast.LENGTH_SHORT).show();
                myIntent = new Intent(this, AboutApp.class);
                startActivity(myIntent);
                break;
            case R.id.button3:
                Toast.makeText(this, "ABOUT AUTHOR clicked", Toast.LENGTH_SHORT).show();
                myIntent = new Intent(this, AboutAuthor.class);
                startActivity(myIntent);
                break;
        }
    }



}
