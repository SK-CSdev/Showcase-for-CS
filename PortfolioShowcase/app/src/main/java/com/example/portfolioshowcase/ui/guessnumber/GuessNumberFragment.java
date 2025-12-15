package com.example.portfolioshowcase.ui.guessnumber;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.portfolioshowcase.R;

public class GuessNumberFragment extends Fragment {

    private int secretNumber;
    private int strikes;
    private int score;

    private EditText guessInput;
    private Button submitButton;
    private TextView feedbackText;
    private TextView strikesCount;
    private TextView scoreValue;
    private ImageView resultImage;
    private Button newGameButton;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_guess_number, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        guessInput = view.findViewById(R.id.guess_input);
        submitButton = view.findViewById(R.id.submit_button);
        feedbackText = view.findViewById(R.id.feedback_text);
        strikesCount = view.findViewById(R.id.strikes_count);
        scoreValue = view.findViewById(R.id.score_value);
        resultImage = view.findViewById(R.id.result_image);
        newGameButton = view.findViewById(R.id.new_game_button);

        newGame();

        submitButton.setOnClickListener(v -> checkGuess());

        newGameButton.setOnClickListener(v -> newGame());
    }

    private void newGame() {
        secretNumber = (int) (Math.random() * 100) + 1;
        strikes = 10;
        score = 0;

        strikesCount.setText(String.valueOf(strikes));
        scoreValue.setText(String.valueOf(score));
        feedbackText.setText("");
        guessInput.setText("");
        resultImage.setVisibility(View.GONE);
        submitButton.setEnabled(true);
        newGameButton.setVisibility(View.GONE);
    }

    private void checkGuess() {
        String guessString = guessInput.getText().toString();
        if (guessString.isEmpty()) {
            feedbackText.setText("Please enter a number.");
            return;
        }

        int guess = Integer.parseInt(guessString);

        if (guess == secretNumber) {
            feedbackText.setText("You got it!");
            score += 50 + (strikes * 5);
            scoreValue.setText(String.valueOf(score));
            resultImage.setImageResource(R.drawable.ic_menu_camera); // Placeholder for win image
            resultImage.setVisibility(View.VISIBLE);
            submitButton.setEnabled(false);
            newGameButton.setVisibility(View.VISIBLE);
        } else if (guess > secretNumber) {
            feedbackText.setText("Too high!");
            strikes--;
        } else {
            feedbackText.setText("Too low!");
            strikes--;
        }

        strikesCount.setText(String.valueOf(strikes));

        if (strikes == 0) {
            feedbackText.setText("Game Over! The number was " + secretNumber);
            resultImage.setImageResource(R.drawable.ic_menu_gallery); // Placeholder for lose image
            resultImage.setVisibility(View.VISIBLE);
            submitButton.setEnabled(false);
            newGameButton.setVisibility(View.VISIBLE);
        }
    }
}