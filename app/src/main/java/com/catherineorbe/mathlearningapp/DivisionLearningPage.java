package com.catherineorbe.mathlearningapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.card.MaterialCardView;

public class DivisionLearningPage extends AppCompatActivity {
//    WebView webViewDivision;

MaterialCardView divby2and3Card, divby4and5Card, divby6and7Card, divby8and9Card, divby1Card, divmenCard, probsolCard;

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

        divby2and3Card = findViewById(R.id.divby2and3Card);
        divby4and5Card = findViewById(R.id.divby4and5Card);
        divby6and7Card = findViewById(R.id.divby6and7Card);
        divby8and9Card = findViewById(R.id.divby8and9Card);
        divby1Card = findViewById(R.id.divby1Card);
        divmenCard = findViewById(R.id.divmenCard);
        probsolCard = findViewById(R.id.probsolCard);

        divby2and3Card.setOnClickListener(createCardClickListener("divby2and3Card"));
        divby4and5Card.setOnClickListener(createCardClickListener("divby4and5Card"));
        divby6and7Card.setOnClickListener(createCardClickListener("divby6and7Card"));
        divby8and9Card.setOnClickListener(createCardClickListener("divby8and9Card"));
        divby1Card.setOnClickListener(createCardClickListener("divby1Card"));
        divmenCard.setOnClickListener(createCardClickListener("divmenCard"));
        probsolCard.setOnClickListener(createCardClickListener("probsol"));

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

    private View.OnClickListener createCardClickListener(final String topic) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DivisionLearningPage.this, DivisionContentActivity.class);
                intent.putExtra("TOPIC_KEY", topic);
                startActivity(intent);
            }
        };
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