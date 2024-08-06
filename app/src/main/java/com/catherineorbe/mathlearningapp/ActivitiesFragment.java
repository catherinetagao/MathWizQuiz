package com.catherineorbe.mathlearningapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ActivitiesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActivitiesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ActivitiesFragment() {
        // Required empty public constructor
    }
    public static ActivitiesFragment newInstance(String param1, String param2) {
        ActivitiesFragment fragment = new ActivitiesFragment();
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
        return inflater.inflate(R.layout.fragment_activities, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup click listener for the Addition CardView
        CardView cardQuiz = view.findViewById(R.id.card_quiz);
        cardQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateCardView(cardQuiz);
                // Add your logic for handling click event
                // For example, navigate to another fragment or perform some action
                Intent intent = new Intent(getActivity(), MainActivity2.class);
                startActivity(intent);
            }
        });

        CardView cardGame = view.findViewById(R.id.card_game);
        cardGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                animateCardView(cardGame);
                Intent intent = new Intent(getActivity(), GamePage.class);
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