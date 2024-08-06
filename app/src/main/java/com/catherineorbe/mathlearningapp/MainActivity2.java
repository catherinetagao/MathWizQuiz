package com.catherineorbe.mathlearningapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

public class MainActivity2 extends AppCompatActivity {

    MaterialCardView addcard,subtractcard,multiplycard,dividecard;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        addcard = findViewById(R.id.addCard);
        subtractcard = findViewById(R.id.minusCard);
        multiplycard = findViewById(R.id.multiplyCard);
        dividecard = findViewById(R.id.divisionCard);

        addcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this,AddQuizActivity.class));
            }
        });

        subtractcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this,SubtractQuizActivity.class));
            }
        });

        multiplycard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this,MultiplyQuizActivity.class));
            }
        });

        dividecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this,DivideQuizActivity.class));
            }
        });

    }
}