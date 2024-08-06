package com.catherineorbe.mathlearningapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DivisionLearningPage extends AppCompatActivity {
//    WebView webViewDivision;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_division_learning_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        webViewDivision = findViewById(R.id.webViewDivision);
//        WebSettings webSettings = webViewDivision.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//
//        // Set WebViewClient to ensure links open within the WebView
//        webViewDivision.setWebViewClient(new WebViewClient());
//
//        // Load local HTML file
//        webViewDivision.loadUrl("file:///android_asset/src/learn/division/index.html");
    }
//    @Override
//    public void onBackPressed() {
//        if (webViewDivision.canGoBack()) {
//            webViewDivision.goBack();
//        } else {
//            super.onBackPressed();
//        }
//    }
}