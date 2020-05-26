package com.example.webviewandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.web_view);
        try {
            Toast.makeText(this, "In web view", Toast.LENGTH_LONG).show();
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webView.loadUrl("file:///android_asset/nuclear_project_short3/index.html");
            webView.setWebChromeClient(new WebChromeClient());
        } catch (Exception e) {
            Toast.makeText(this, "Excperion: " + e, Toast.LENGTH_LONG).show();
        }

    }
}