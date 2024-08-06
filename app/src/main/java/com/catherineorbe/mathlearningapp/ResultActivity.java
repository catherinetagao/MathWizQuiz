package com.catherineorbe.mathlearningapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.progressindicator.CircularProgressIndicator;

public class ResultActivity extends AppCompatActivity {

    TextView scoreTitle, scoreSubtitle, scoreProgressText;
    CircularProgressIndicator scoreProgressIndicator;
    Button finishButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        scoreTitle = findViewById(R.id.score_title);
        scoreSubtitle = findViewById(R.id.score_subtitle);
        scoreProgressText = findViewById(R.id.score_progress_text);
        scoreProgressIndicator = findViewById(R.id.score_progress_indicator);
        finishButton = findViewById(R.id.finish_btn);

        // Get scores from intent
        Intent intent = getIntent();
        int correct = intent.getIntExtra("correct", 0);
        int wrong = intent.getIntExtra("wrong", 0);
        int total = correct + wrong;

        // Log the values for debugging
        Log.d("ResultActivity", "Correct: " + correct + ", Wrong: " + wrong + ", Total: " + total);

        // Check if total is zero to avoid division by zero
        if (total > 0) {
            // Calculate percentage
            int scorePercentage = (int) ((correct / (float) total) * 100);

            // Log the calculated percentage
            Log.d("ResultActivity", "Score Percentage: " + scorePercentage);

            // Update UI elements with score
            scoreProgressText.setText(scorePercentage + "%");
            scoreProgressIndicator.setMax(100); // Ensure max is set to 100
            scoreProgressIndicator.setProgress(scorePercentage);
            scoreProgressIndicator.setVisibility(View.VISIBLE); // Ensure it's visible
            scoreSubtitle.setText(correct + " out of " + total + " questions are correct");
        } else {
            // Handle the case where total is zero (e.g., show an error message)
            scoreProgressText.setText("0%");
            scoreProgressIndicator.setMax(100); // Ensure max is set to 100
            scoreProgressIndicator.setProgress(0);
            scoreProgressIndicator.setVisibility(View.VISIBLE); // Ensure it's visible
            scoreSubtitle.setText("No questions answered");
        }

        // Set button action
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish activity and go back to main
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Optionally finish the activity or do nothing
        finish();
    }

}
