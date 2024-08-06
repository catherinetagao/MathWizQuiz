package com.catherineorbe.mathlearningapp;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GamePage extends AppCompatActivity {
    WebView webViewGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        webViewGame = findViewById(R.id.webViewGame);
        // Enable JavaScript
        WebSettings webSettings = webViewGame.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Set WebViewClient to ensure links open within the WebView
        webViewGame.setWebViewClient(new WebViewClient());

        // Load local HTML file
        webViewGame.loadUrl("file:///android_asset/src/games/index.html");
    }

    @Override
    public void onBackPressed() {
        if (webViewGame.canGoBack()) {
            webViewGame.goBack();
        } else {
            super.onBackPressed();
        }
    }
}