package com.catherineorbe.mathlearningapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

public class MultiplyLearningPage extends AppCompatActivity {

    MaterialCardView mulf2Card, mulf3Card, mulf4Card, mulf5and6Card, mulf7and8Card, mulf9Card, oneandzeroCard, orderofFactCard, mulmenCard, probsolCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiply_learning_page);

        mulf2Card = findViewById(R.id.mulf2Card);
        mulf3Card = findViewById(R.id.mulf3Card);
        mulf4Card = findViewById(R.id.mulf4Card);
        mulf5and6Card = findViewById(R.id.mulf5and6Card);
        mulf7and8Card = findViewById(R.id.mulf7and8Card);
        mulf9Card = findViewById(R.id.mulf9Card);
        oneandzeroCard = findViewById(R.id.oneandzeroCard);
        orderofFactCard = findViewById(R.id.orderofFactCard);
        mulmenCard = findViewById(R.id.mulmenCard);
        probsolCard = findViewById(R.id.probsolCard);

        mulf2Card.setOnClickListener(createCardClickListener("mulf2"));
        mulf3Card.setOnClickListener(createCardClickListener("mulf3"));
        mulf4Card.setOnClickListener(createCardClickListener("mulf4"));
        mulf5and6Card.setOnClickListener(createCardClickListener("mulf5and6"));
        mulf7and8Card.setOnClickListener(createCardClickListener("mulf7and8"));
        mulf9Card.setOnClickListener(createCardClickListener("mulf9"));
        oneandzeroCard.setOnClickListener(createCardClickListener("oneandzero"));
        orderofFactCard.setOnClickListener(createCardClickListener("orderofFact"));
        mulmenCard.setOnClickListener(createCardClickListener("mulmen"));
        probsolCard.setOnClickListener(createCardClickListener("probsol"));
    }

    private View.OnClickListener createCardClickListener(final String topic) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MultiplyLearningPage.this, MultiplicationContentActivity.class);
                intent.putExtra("TOPIC_KEY", topic);
                startActivity(intent);
            }
        };
    }

//    @Override
//    public void onBackPressed() {
//        if (webViewMultiply.canGoBack()) {
//            webViewMultiply.goBack();
//        } else {
//            super.onBackPressed();
//        }
//    }
}
