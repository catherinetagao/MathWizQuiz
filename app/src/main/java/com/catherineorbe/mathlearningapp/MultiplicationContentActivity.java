package com.catherineorbe.mathlearningapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;

public class MultiplicationContentActivity extends AppCompatActivity {

    private TextView objectivesTextView;
    private TextView examplesTextView;
    private TextView practiceProblemTextView;
    private EditText userAnswerEditText;
    private Button submitButton;
    private TextView feedbackTextView;
    private TextView scoreTextView;
    private TextView completionRateTextView;
    private TextView areasOfImprovementTextView;

    private String[] topics = {
            "mulf2", "mulf3", "mulf4", "mulf5and6", "mulf7and8", "mulf9", "oneandzero", "orderofFact", "mulmen", "probsol"
    };
    private int currentTopicIndex = 0;
    private int score = 0;
    private int totalTopics = topics.length;
    private Set<Integer> incorrectTopics = new HashSet<>(); // To track topics with incorrect answers

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_multiply_content);

        // Initialize UI elements
        objectivesTextView = findViewById(R.id.objectives);
        examplesTextView = findViewById(R.id.examples);
        practiceProblemTextView = findViewById(R.id.practice_problem);
        userAnswerEditText = findViewById(R.id.user_answer);
        submitButton = findViewById(R.id.submit_button);
        feedbackTextView = findViewById(R.id.feedback);
        scoreTextView = findViewById(R.id.score_text_view);
        completionRateTextView = findViewById(R.id.completion_rate_text_view);
        areasOfImprovementTextView = findViewById(R.id.areas_of_improvement_text_view);

        // Set up submit button listener
        submitButton.setOnClickListener(v -> {
            checkAnswer();
        });

        // Start with the first topic
        loadTopic(currentTopicIndex);
    }

    private void loadTopic(int topicIndex) {
        if (topicIndex < topics.length) {
            String topic = topics[topicIndex];
            switch (topic) {
                case "mulf2":
                    setContentForTopic("multiplication of 2", "Examples: \n2 x 1 = 2\n2 x 2 = 4", "Practice Problem:\n What is 2 x 5?", 2 * 5);
                    break;
                case "mulf3":
                    setContentForTopic("multiplication of 3", "Examples: \n3 x 1 = 3\n3 x 2 = 6", "Practice Problem:\n What is 3 x 4?", 3 * 4);
                    break;
                case "mulf4":
                    setContentForTopic("multiplication of 4", "Examples: \n4 x 1 = 4\n4 x 2 = 8", "Practice Problem:\n What is 4 x 7?", 4 * 7);
                    break;
                case "mulf5and6":
                    setContentForTopic("multiplication of 5 and 6", "Examples:\n 5 x 1 = 5, 6 x 1 = 6\n5 x 2 = 10, 6 x 2 = 12", "Practice Problem:\n What is 5 x 6?", 5 * 6);
                    break;
                case "mulf7and8":
                    setContentForTopic("multiplication of 7 and 8", "Examples:\n 7 x 1 = 7, 8 x 1 = 8\n7 x 2 = 14, 8 x 2 = 16", "Practice Problem:\n What is 7 x 8?", 7 * 8);
                    break;
                case "mulf9":
                    setContentForTopic("multiplication of 9", "Examples:\n 9 x 1 = 9\n9 x 2 = 18", "Practice Problem: \n What is 9 x 3?", 9 * 3);
                    break;
                case "oneandzero":
                    setContentForTopic("1 and 0 as Factors", "Examples:\n 1 x 1 = 1, 0 x 1 = 0\n1 x 2 = 2, 0 x 2 = 0", "Practice Problem: \n What is 1 x 0?", 1 * 0);
                    break;
                case "orderofFact":
                    setContentForTopic("Order of Factors", "Examples:\n 2 x 3 = 6 is the same as 3 x 2 = 6", "Practice Problem: \n What is 4 x 5?", 4 * 5);
                    break;
                case "mulmen":
                    setContentForTopic("Multiplying Mentally", "Examples:\n 7 x 8 = 56, 12 x 12 = 144", "Practice Problem: \n What is 15 x 15?", 15 * 15);
                    break;
                case "probsol":
                    setContentForTopic("Problem Solving", "Examples:\n Solve problems like 5 x 3 + 2 x 4", "Practice Problem:\n What is 7 x 6 - 8?", 7 * 6 - 8);
                    break;
                default:
                    setContentForTopic("Unknown", "No content available for this topic.", "Practice Problem: N/A", -1);
                    break;
            }
        }
    }

    private void setContentForTopic(String topic, String examples, String practiceProblem, int correctAnswer) {
        objectivesTextView.setText("Objectives:\n- Understand the " + topic);
        examplesTextView.setText(examples);
        practiceProblemTextView.setText(practiceProblem);
        submitButton.setTag(correctAnswer); // Store the correct answer in the button's tag
    }

    private void checkAnswer() {
        String userAnswerStr = userAnswerEditText.getText().toString();
        if (!userAnswerStr.isEmpty()) {
            try {
                int userAnswer = Integer.parseInt(userAnswerStr);
                int correctAnswer = (int) submitButton.getTag();
                if (userAnswer == correctAnswer) {
                    feedbackTextView.setText("Correct! Well done.");
                    score++; // Increase score on correct answer
                    currentTopicIndex++;
                    if (currentTopicIndex < topics.length) {
                        loadTopic(currentTopicIndex);
                        updateProgress();
                    } else {
                        feedbackTextView.setText("Congratulations! You've completed all topics.");
                        submitButton.setEnabled(false);
                        updateProgress();

                        // Automatically navigate back to the main activity
                        new Handler().postDelayed(() -> {
                            Intent intent = new Intent(MultiplicationContentActivity.this, MainActivity2.class);
                            startActivity(intent);
                            finish(); // Close the current activity
                        }, 1000); // Delay of 1 seconds before navigating back
                    }
                } else {
                    feedbackTextView.setText("Incorrect. Try again!");
                    incorrectTopics.add(currentTopicIndex); // Track incorrect topics
                }
            } catch (NumberFormatException e) {
                feedbackTextView.setText("Please enter a valid number.");
            }

            // Clear the user_answer field after processing the answer
            userAnswerEditText.setText("");
        } else {
            feedbackTextView.setText("Please enter an answer.");
        }
    }



    private void updateProgress() {
        int completedTopics = currentTopicIndex;
        int totalTopics = topics.length;
        int completionRate = (int) ((completedTopics / (float) totalTopics) * 100);
        scoreTextView.setText("Score: " + score);
        completionRateTextView.setText("Completion Rate: " + completionRate + "%");

        // Display areas of improvement
        if (!incorrectTopics.isEmpty()) {
            StringBuilder areasOfImprovement = new StringBuilder("Areas of Improvement: \n");
            for (int topicIndex : incorrectTopics) {
                areasOfImprovement.append(topics[topicIndex]).append("\n");
            }
            areasOfImprovementTextView.setText(areasOfImprovement.toString());
        } else {
            areasOfImprovementTextView.setText("Areas of Improvement: None");
        }
    }
}

