package com.catherineorbe.mathlearningapp;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import java.util.Locale;

public class LearnFragment extends Fragment {

    private TextToSpeech textToSpeech;

    private TextView tvDisplayName;
    private TextView textUser;

    private String enteredName;
    private TextView textTopic;
    private TextView textAddition;
    private TextView textSubtraction;
    private TextView textMultiply;
    private TextView textDivision;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public LearnFragment() {
        // Required empty public constructor
    }

    public static LearnFragment newInstance(String param1, String param2) {
        LearnFragment fragment = new LearnFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_learn, container, false);


        // Initialize TextToSpeech
        textToSpeech = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });

        // Find all TextViews
        tvDisplayName = view.findViewById(R.id.tvDisplayName);
        textUser = view.findViewById(R.id.text_user);
        textTopic = view.findViewById(R.id.text_topic);
        textAddition = view.findViewById(R.id.text_addition);
        textSubtraction = view.findViewById(R.id.text_subtraction);
        textMultiply = view.findViewById(R.id.text_multiply);
        textDivision = view.findViewById(R.id.text_division);


        // Check if arguments are passed and set textUser
//        if (getArguments() != null) {
//            String enteredName = getArguments().getString("Name", "");
//            textUser.setText(enteredName);
//        }

        enteredName = getActivity().getIntent().getExtras().getString("Name");

        textUser.setText(enteredName);

        // Set click listener to read text
        view.findViewById(R.id.fragment_container).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                readAllText();
            }
        });

        return view;
    }

    private void readAllText() {
        String text = tvDisplayName.getText().toString() + " " +
                textUser.getText().toString() + " " +
                textTopic.getText().toString() + "What do you want to learn today?" +
                textAddition.getText().toString() + " " +
                textSubtraction.getText().toString() + " " +
                textMultiply.getText().toString() + " " +
                textDivision.getText().toString();

        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    @Override
    public void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup click listener for the Addition CardView
        CardView card_readWrite = view.findViewById(R.id.card_readWrite);
        card_readWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateCardView(card_readWrite);
                // Add your logic for handling click event
                // For example, navigate to another fragment or perform some action
                Intent intent = new Intent(getActivity(), ReadWriteNumber.class);
                startActivity(intent);
            }
        });

        CardView cardAddition = view.findViewById(R.id.card_addition);
        cardAddition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateCardView(cardAddition);
                // Add your logic for handling click event
                // For example, navigate to another fragment or perform some action
                Intent intent = new Intent(getActivity(), AddLearningPage.class);
                startActivity(intent);
            }
        });

        CardView cardSubtraction = view.findViewById(R.id.card_subtraction);
        cardSubtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                animateCardView(cardSubtraction);
                Intent intent = new Intent(getActivity(), SubLearningPage.class);
                startActivity(intent);
            }
        });

        CardView cardMultiplication = view.findViewById(R.id.card_multiplication);
        cardMultiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                animateCardView(cardMultiplication);
                Intent intent = new Intent(getActivity(), MultiplyLearningPage.class);
                startActivity(intent);
            }
        });

        CardView cardDivision = view.findViewById(R.id.card_division);
        cardDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                animateCardView(cardDivision);
                Intent intent = new Intent(getActivity(), DivisionLearningPage.class);
                startActivity(intent);
            }
        });
    }

    private void animateCardView(CardView cardView) {
        // Load animation from XML
        Animation scaleAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.scale_up);

        // Apply animation to the CardView
        cardView.startAnimation(scaleAnimation);
    }
}
