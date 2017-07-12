package com.thecodingjack.androidjokelib;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.JavaJoke;

public class JokeActivity extends AppCompatActivity {
    private TextView gcmtextView;
    private TextView textView;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        JavaJoke joke = new JavaJoke();
        String jokeMsg = joke.getJoke();
        textView = (TextView)findViewById(R.id.joketextview);
        gcmtextView = (TextView)findViewById(R.id.gcmjoketextview);
        textView.setText(jokeMsg);
//        progressBar = (ProgressBar)findViewById(R.id.progressbar);
        Intent intent = getIntent();
        if(intent.hasExtra("gcm")){
            gcmtextView.setText(intent.getStringExtra("gcm"));
        }
//        new EndpointsAsyncTask().execute(new Pair<Context, String>(this, "Jack"));
    }
//    class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
//        private  MyApi myApiService = null;
//        private String joke;
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            progressBar.setVisibility(View.VISIBLE);
//
//        }
//
//        @Override
//        protected String doInBackground(Pair<Context, String>... params) {
//            if(myApiService == null) {
//
//                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
//                        .setRootUrl("https://2-dot-build-it-bigger-173419.appspot.com/_ah/api/");
//
//                myApiService = builder.build();
//            }
//
//            try {
//                joke = myApiService.getJoke().execute().getData();
//                return joke;
//            } catch (IOException e) {
//                return e.getMessage();
//            }
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            progressBar.setVisibility(View.GONE);
//            gcmtextView.setText(result);
//            echo(result);
//        }
//    }
//    static String echo(String joke) {
//        return joke;
//    }
}
