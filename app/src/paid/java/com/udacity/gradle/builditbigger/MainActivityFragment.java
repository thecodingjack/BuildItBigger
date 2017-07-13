package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.lamkeong.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.thecodingjack.androidjokelib.JokeActivity;

import java.io.IOException;

public class MainActivityFragment extends Fragment {
    private Button button;
    private ProgressBar progressBar;
    private String anotherjoke;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        button = (Button) root.findViewById(R.id.telljokebutton);
        progressBar = (ProgressBar)root.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
        button.setVisibility(View.GONE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JokeActivity.class);
                intent.putExtra("gcm",anotherjoke);
                startActivity(intent);
            }
        });
        new EndpointsAsyncTask().execute(new Pair<Context, String>(getActivity(), "Jack"));
        return root;
    }

    class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
        private MyApi myApiService = null;
        private String joke;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (progressBar != null) {
                progressBar.setVisibility(View.VISIBLE);
            }
        }

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
            if (progressBar != null && button != null) {
                progressBar.setVisibility(View.GONE);
                button.setVisibility(View.VISIBLE);
            }
            anotherjoke = result;
        }
    }
}
