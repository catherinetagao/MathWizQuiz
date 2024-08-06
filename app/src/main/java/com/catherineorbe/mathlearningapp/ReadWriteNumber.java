package com.catherineorbe.mathlearningapp;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ReadWriteNumber extends AppCompatActivity {

    WebView webViewNumbersUpToThousands;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_read_write_number);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Start the background music service
        Intent musicServiceIntent = new Intent(this, BackgroundMusicService.class);
        startService(musicServiceIntent);

        webViewNumbersUpToThousands = findViewById(R.id.webViewNumberUpToThousand);

        // Enable JavaScript
        WebSettings webSettings = webViewNumbersUpToThousands.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Set WebViewClient to ensure links open within the WebView
        webViewNumbersUpToThousands.setWebViewClient(new WebViewClient());

        // Load local HTML file
        webViewNumbersUpToThousands.loadUrl("file:///android_asset/src/readingandwriting/numbersUpToThousand/index.html");


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Stop the background music service
        Intent musicServiceIntent = new Intent(this, BackgroundMusicService.class);
        stopService(musicServiceIntent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Intent musicServiceIntent = new Intent(this, BackgroundMusicService.class);
        stopService(musicServiceIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent musicServiceIntent = new Intent(this, BackgroundMusicService.class);
        startService(musicServiceIntent);
    }
}