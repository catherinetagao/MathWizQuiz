package com.catherineorbe.mathlearningapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    ImageView logo_anim;
    TextView appName;
    Handler handler;
    MediaPlayer soundEffect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Initialize views
        logo_anim = findViewById(R.id.logo_anim);
        appName = findViewById(R.id.appName);

        // Set the activity to full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Load animations
        Animation logoAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        Animation textAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        // Start animations
        logo_anim.startAnimation(logoAnimation);
        appName.startAnimation(textAnimation);

        // Load sound effect
        soundEffect = MediaPlayer.create(this, R.raw.splash_sound); // Ensure you have a splash_sound.mp3 in res/raw folder
        soundEffect.start();

        // Handle transition to the next activity
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, EnterName.class);
                startActivity(intent);
                finish();
                soundEffect.release(); // Release the media player
            }
        }, 3000);
    }
}
