package com.example.webviewandroid;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private final int MY_PERMISSIONS_READ_EXTERNAL_STORAGE = 1;
    private WebView webView;
    private File path;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.web_view);


        //When permission is not granted by user, show them message why this permission is needed.
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(this, "Please grant permissions to read file", Toast.LENGTH_LONG).show();

            //Give user option to still opt-in the permissions
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_READ_EXTERNAL_STORAGE);
        } else {
            // Show user dialog to grant permission to record audio
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_READ_EXTERNAL_STORAGE);
        }

        getPathFromDevice();

    }


    private void getPathFromDevice() {
        try {
            path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File myFile = new File(path, "nuclear_project_short3/index.html");
            if (myFile.exists()) {
                String web = myFile.toString();
                //Toast.makeText(this, web, Toast.LENGTH_LONG).show();
                lodeweb(web);
            } else {
                Toast.makeText(this, "File not found", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Excperion: " + e, Toast.LENGTH_LONG).show();
        }
    }


    @SuppressLint("SetJavaScriptEnabled")
    private void lodeweb(String path) {
        try {
            //Toast.makeText(this, "In web view", Toast.LENGTH_LONG).show();
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webView.loadUrl("file://" + path);
            webView.setWebChromeClient(new WebChromeClient());
        } catch (Exception e) {
            Toast.makeText(this, "Excperion: " + e, Toast.LENGTH_LONG).show();
        }
    }
}