package com.catherineorbe.mathlearningapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
    DatabaseHelper dbHelper;

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

        // Initialize the DatabaseHelper
        dbHelper = new DatabaseHelper(this);

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
                saveNameToDatabase(enteredName);

                Intent intent = new Intent(EnterName.this, MainActivity.class);
                intent.putExtra("Name", enteredName);
                startActivity(intent);
                finish();
            }
        });
    }

    private void saveNameToDatabase(String name) {
        // Get writable database
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, name);

        // Insert the new row, returning the primary key value of the new row
        db.insert(DatabaseHelper.TABLE_NAME, null, values);
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
