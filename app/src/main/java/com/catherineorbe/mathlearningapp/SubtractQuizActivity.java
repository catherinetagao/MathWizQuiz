package com.catherineorbe.mathlearningapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

public class SubtractQuizActivity extends AppCompatActivity {

    MaterialCardView easycard, mediumcard, hardcard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtract_quiz);

        easycard = findViewById(R.id.easyCard);
        mediumcard = findViewById(R.id.mediumCard);
        hardcard = findViewById(R.id.hardCard);

        easycard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SubtractQuizActivity.this, SubtractionBasicQuiz.class));
                finish();
            }
        });

        mediumcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SubtractQuizActivity.this, SubtactionClassicQuiz.class));
                finish();
            }
        });

        hardcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SubtractQuizActivity.this,SubtractionHardQuiz.class));
                finish();
            }
        });

    }
}