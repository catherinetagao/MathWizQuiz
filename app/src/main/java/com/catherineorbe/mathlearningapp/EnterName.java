package com.catherineorbe.mathlearningapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

public class EnterName extends AppCompatActivity {

    TextView textView;
    EditText inputText;
    MaterialCardView button;
    ImageView gifImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_enter_name);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView = findViewById(R.id.textView);
        inputText = findViewById(R.id.inputText);
        button = findViewById(R.id.button);
        gifImageView = findViewById(R.id.gifImageView);

        // Load GIF into ImageView using Glide
        Glide.with(this)
                .asGif()
                .load(R.drawable.bg) // Replace with your GIF file name
                .into(gifImageView);

        // Make the GIF ImageView visible once loaded
        gifImageView.setVisibility(ImageView.VISIBLE);

        // Start the background music service
        Intent musicServiceIntent = new Intent(this, BackgroundMusicService.class);
        startService(musicServiceIntent);

        button.setOnClickListener(view -> {
            String enteredName = inputText.getText().toString().trim();

            if (!enteredName.isEmpty()) {
                Intent intent = new Intent(EnterName.this, MainActivity.class);
                intent.putExtra("Name", enteredName);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Stop the background music service
        Intent musicServiceIntent = new Intent(this, BackgroundMusicService.class);
        stopService(musicServiceIntent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Intent musicServiceIntent = new Intent(this, BackgroundMusicService.class);
        stopService(musicServiceIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent musicServiceIntent = new Intent(this, BackgroundMusicService.class);
        startService(musicServiceIntent);
    }
}
