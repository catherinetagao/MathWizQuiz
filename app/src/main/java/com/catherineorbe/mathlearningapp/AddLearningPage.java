package com.catherineorbe.mathlearningapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddLearningPage extends AppCompatActivity {

    WebView webViewAdd;
    Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_learning_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Start the background music service
        Intent musicServiceIntent = new Intent(this, BackgroundMusicService.class);
        startService(musicServiceIntent);

        webViewAdd = findViewById(R.id.webViewAdd);
        buttonBack = findViewById(R.id.buttonBack);


        // Enable JavaScript
        WebSettings webSettings = webViewAdd.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Set WebViewClient to ensure links open within the WebView
        webViewAdd.setWebViewClient(new WebViewClient());

        // Load local HTML file
        webViewAdd.loadUrl("file:///android_asset/src/add.html");

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddLearningPage.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (webViewAdd.canGoBack()) {
            webViewAdd.goBack();
        } else {
            super.onBackPressed();
        }
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