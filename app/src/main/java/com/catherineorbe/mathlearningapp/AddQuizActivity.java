package com.catherineorbe.mathlearningapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

public class AddQuizActivity extends AppCompatActivity {

    MaterialCardView easycard, mediumcard, hardcard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quiz);

        easycard = findViewById(R.id.easyCard);
        mediumcard = findViewById(R.id.mediumCard);
        hardcard = findViewById(R.id.hardCard);

        easycard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddQuizActivity.this, BasicQuiz.class));
                finish();
            }
        });

        mediumcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddQuizActivity.this,ClassicQuiz.class));
                finish();
            }
        });

        hardcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddQuizActivity.this,HardQuiz.class));
                finish();
            }
        });
    }
}