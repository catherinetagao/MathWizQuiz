// MainActivity.java
package com.catherineorbe.mathlearningapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MainActivity extends AppCompatActivity {

    private Fragment learnFragment;
    private Fragment activitiesFragment;
    private Fragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of finding a TextView and setting its size
        TextView sampleTextView = findViewById(R.id.sample_text_view);
        TextSizeUtils.setTextSize(this, sampleTextView, true); // Set to big or small

        // Initialize fragments
        learnFragment = new LearnFragment();
        activitiesFragment = new ActivitiesFragment();
        profileFragment = new ProfileFragment();

        // Start the background music service
        Intent musicServiceIntent = new Intent(this, BackgroundMusicService.class);
        startService(musicServiceIntent);

        // Set initial fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, learnFragment).commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();
            if (itemId == R.id.menu_learn) {
                selectedFragment = learnFragment;
            } else if (itemId == R.id.menu_activities) {
                selectedFragment = activitiesFragment;
            } else if (itemId == R.id.menu_profile) {
                selectedFragment = profileFragment;
            }
            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            }
            return true;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent musicServiceIntent = new Intent(this, BackgroundMusicService.class);
        startService(musicServiceIntent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Intent musicServiceIntent = new Intent(this, BackgroundMusicService.class);
        stopService(musicServiceIntent);
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

    @Override
    public void onBackPressed() {
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(MainActivity.this);
        materialAlertDialogBuilder.setTitle(R.string.app_name);
        materialAlertDialogBuilder.setMessage("Are you sure you want to exit?");
        materialAlertDialogBuilder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        materialAlertDialogBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        materialAlertDialogBuilder.show();
    }
}
