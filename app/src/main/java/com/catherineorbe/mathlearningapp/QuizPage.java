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

public class QuizPage extends AppCompatActivity {

    WebView webViewQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        webViewQuiz = findViewById(R.id.webViewQuiz);
        // Enable JavaScript
        WebSettings webSettings = webViewQuiz.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Set WebViewClient to ensure links open within the WebView
        webViewQuiz.setWebViewClient(new WebViewClient());

        // Load local HTML file
        webViewQuiz.loadUrl("file:///android_asset/src/quiz/quizview.html");

    }

    @Override
    public void onBackPressed() {
        if (webViewQuiz.canGoBack()) {
            webViewQuiz.goBack();
        } else {
            super.onBackPressed();
        }
    }
}