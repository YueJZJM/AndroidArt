package com.example.chapter_11;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.chapter_11.messagequeue.Track;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent service = new Intent(this, LocalIntentService.class);
        service.putExtra("mainactivity", "main");
        startService(service);
        service.putExtra("mainactivity", "main1");
        startService(service);
        service.putExtra("mainactivity", "main2");
        startService(service);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Track track = Track.getInstance();
        track.trackEvent();
    }

    private class DownloadFilesTask extends AsyncTask<URL,Integer,Long>{

        @Override
        protected Long doInBackground(URL... urls) {
            int count = urls.length;
            long totalSize = 0;
            for (int i = 0; i < count; i++) {
                // totalSize += Downloader.downloadFile(urls[i]);
                publishProgress((int) ((i / (float) count) * 100));
                if (isCancelled()) {
                    break;
                }
            }
            return totalSize;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //setProgressPercent(progress[0]);
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
        }
    }
}
