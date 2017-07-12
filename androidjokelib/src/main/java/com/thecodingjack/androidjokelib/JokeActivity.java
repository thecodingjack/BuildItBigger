package com.thecodingjack.androidjokelib;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.JavaJoke;

public class JokeActivity extends AppCompatActivity {
    private TextView gcmtextView;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        JavaJoke joke = new JavaJoke();
        String jokeMsg = joke.getJoke();
        textView = (TextView)findViewById(R.id.joketextview);
        gcmtextView = (TextView)findViewById(R.id.gcmjoketextview);
        textView.setText(jokeMsg);
        Intent intent = getIntent();
        if(intent.hasExtra("gcm")){
            gcmtextView.setText(intent.getStringExtra("gcm"));
        }else{
            gcmtextView.setVisibility(View.GONE);
        }
    }
}
