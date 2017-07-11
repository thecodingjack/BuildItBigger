package com.thecodingjack.androidjokelib;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.JavaJoke;
import com.example.lamkeong.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

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
        new EndpointsAsyncTask().execute(new Pair<Context, String>(this, "Jack"));
    }
    class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
        private  MyApi myApiService = null;
        private String joke;


        @Override
        protected String doInBackground(Pair<Context, String>... params) {
            if(myApiService == null) {

                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl("https://2-dot-build-it-bigger-173419.appspot.com/_ah/api/");

                myApiService = builder.build();
            }

            try {
                joke = myApiService.getJoke().execute().getData();
                return joke;
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            gcmtextView.setText(result);
            echo(result);
        }
    }
    static String echo(String joke) {
        return joke;
    }
}
