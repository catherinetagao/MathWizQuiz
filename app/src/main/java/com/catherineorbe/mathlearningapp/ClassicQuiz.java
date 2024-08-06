package com.catherineorbe.mathlearningapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClassicQuiz extends AppCompatActivity {

    TextView quiztext, aans, bans, timerTextView;
    ProgressBar progressBar;
    List<QuestionsItem> questionsItems;
    int currentQuestions = 0;
    int correct = 0, wrong = 0;
    CountDownTimer countDownTimer;
    int timePerQuestion = 60 * 1000; // 1 minute per question

    int progressStep;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_quiz);

        quiztext = findViewById(R.id.quizText);
        aans = findViewById(R.id.aanswer);
        bans = findViewById(R.id.banswer);
        timerTextView = findViewById(R.id.timerTextView);
        progressBar = findViewById(R.id.progressBar);

        loadAllQuestions();
        Collections.shuffle(questionsItems);

        if (!questionsItems.isEmpty()) {
            progressStep = 100 / questionsItems.size();
        }

        setQuestionScreen(currentQuestions);

        aans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(questionsItems.get(currentQuestions).getAnswer1().equals(questionsItems.get(currentQuestions).getCorrect()), aans);
            }
        });

        bans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(questionsItems.get(currentQuestions).getAnswer2().equals(questionsItems.get(currentQuestions).getCorrect()), bans);
            }
        });
    }

    private void setQuestionScreen(int currentQuestions) {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        quiztext.setText(questionsItems.get(currentQuestions).getQuestions());
        aans.setText(questionsItems.get(currentQuestions).getAnswer1());
        bans.setText(questionsItems.get(currentQuestions).getAnswer2());

        progressBar.setProgress(progressStep * currentQuestions);

        countDownTimer = new CountDownTimer(timePerQuestion, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long minutes = millisUntilFinished / 1000 / 60;
                long seconds = (millisUntilFinished / 1000) % 60;
                timerTextView.setText(String.format("Time Left: %02d:%02d", minutes, seconds));
            }

            @Override
            public void onFinish() {
                checkAnswer(false, null); // If time runs out, treat it as a wrong answer
            }
        }.start();
    }

    private void checkAnswer(boolean isCorrect, TextView selectedAnswer) {
        if (isCorrect) {
            correct++;
            if (selectedAnswer != null) {
                selectedAnswer.setBackgroundResource(R.color.green);
                selectedAnswer.setTextColor(getResources().getColor(R.color.white));
            }
        } else {
            wrong++;
            if (selectedAnswer != null) {
                selectedAnswer.setBackgroundResource(R.color.red1);
                selectedAnswer.setTextColor(getResources().getColor(R.color.white));
            }
        }

        if (currentQuestions < questionsItems.size() - 1) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    currentQuestions++;
                    setQuestionScreen(currentQuestions);
                    if (selectedAnswer != null) {
                        selectedAnswer.setBackgroundResource(R.color.white);
                        selectedAnswer.setTextColor(getResources().getColor(R.color.text_secondary_color));
                    }
                }
            }, 500);
        } else {
            Intent intent = new Intent(ClassicQuiz.this, ResultActivity.class);
            intent.putExtra("correct", correct);
            intent.putExtra("wrong", wrong);
            startActivity(intent);
            finish();
        }
    }

    private void loadAllQuestions() {
        questionsItems = new ArrayList<>();
        String jsonquiz = loadJsonFromAsset("add/classicquestions.json");
        try {
            JSONObject jsonObject = new JSONObject(jsonquiz);
            JSONArray questions = jsonObject.getJSONArray("mediumlevelquestions");
            for (int i = 0; i < questions.length(); i++) {
                JSONObject question = questions.getJSONObject(i);

                String questionsString = question.getString("question");
                String answer1String = question.getString("answer1");
                String answer2String = question.getString("answer2");
                String correctString = question.getString("correct");

                questionsItems.add(new QuestionsItem(questionsString, answer1String, answer2String, correctString));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String loadJsonFromAsset(String s) {
        String json = "";
        try {
            InputStream inputStream = getAssets().open(s);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override
    public void onBackPressed() {
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(ClassicQuiz.this);
        materialAlertDialogBuilder.setTitle(R.string.app_name);
        materialAlertDialogBuilder.setMessage("Are you sure you want to exit the quiz?");
        materialAlertDialogBuilder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        materialAlertDialogBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(ClassicQuiz.this, MainActivity2.class));
                finish();
            }
        });

        materialAlertDialogBuilder.show();
    }
}
