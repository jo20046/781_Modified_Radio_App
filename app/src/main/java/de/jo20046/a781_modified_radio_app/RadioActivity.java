package de.jo20046.a781_modified_radio_app;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;

public class RadioActivity extends Activity {

    WebView webView;
    MediaPlayer mp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webView = new WebView(this);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(getIntent().getExtras().getString("URL"));
        setContentView(webView);
        startMediaPlayer();
    }

    public void startMediaPlayer() {
        stopMediaPlayer();
        mp = new MediaPlayer();
        try {
            mp.setDataSource(getIntent().getExtras().getString("Stream"));

            // Version "blocking"
            mp.prepare();
            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void stopMediaPlayer() {
        if (mp != null) mp.release();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopMediaPlayer();
    }
}
