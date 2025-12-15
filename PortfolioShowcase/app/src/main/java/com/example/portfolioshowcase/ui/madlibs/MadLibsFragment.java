package com.example.portfolioshowcase.ui.madlibs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.portfolioshowcase.databinding.FragmentMadLibsBinding;

import java.util.Random;

public class MadLibsFragment extends Fragment {

    private FragmentMadLibsBinding binding;
    private EditText[] inputs;

    int selectedStory = 0; // 1, 2, 3, or random
    Random random = new Random();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMadLibsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize the EditText array from the binding object
        inputs = new EditText[]{
                binding.input1, binding.input2, binding.input3, binding.input4,
                binding.input5, binding.input6, binding.input7, binding.input8
        };

        // Button Listeners
        binding.story1Button.setOnClickListener(v -> startInput(1));
        binding.story2Button.setOnClickListener(v -> startInput(2));
        binding.story3Button.setOnClickListener(v -> startInput(3));
        binding.randomButton.setOnClickListener(v -> startInput(4));

        binding.generateButton.setOnClickListener(v -> generateStory());
        binding.restartButton.setOnClickListener(v -> restartApp());
    }

    private void startInput(int story) {
        selectedStory = story;
        // Hide selection buttons
        binding.story1Button.setVisibility(View.GONE);
        binding.story2Button.setVisibility(View.GONE);
        binding.story3Button.setVisibility(View.GONE);
        binding.randomButton.setVisibility(View.GONE);
        binding.titleTextView.setVisibility(View.GONE);

        // Show input fields
        binding.inputLayout.setVisibility(View.VISIBLE);
    }

    private void generateStory() {
        // Collect words
        String[] words = new String[8];
        for (int i = 0; i < inputs.length; i++) {
            words[i] = inputs[i].getText().toString().trim();
        }

        // Choose random story if "I'm Feeling Lucky"
        if (selectedStory == 4) {
            selectedStory = random.nextInt(3) + 1;
        }

        String story = "";

        switch (selectedStory) {
            case 1:
                story = "Once upon a time in " + words[4] + ", there was a " + words[2] +
                        " " + words[0] + " who loved to " + words[1] + ". Every day, the " +
                        words[0] + " would visit the " + words[5] + " to meet a " + words[3] +
                        ". One day, they found " + words[7] + " " + words[6] + " treasures!";
                break;
            case 2:
                story = "In the year " + words[6] + ", a " + words[2] + " " + words[0] +
                        " decided to travel to " + words[4] + ". Armed only with a " +
                        words[5] + " and the courage of a " + words[3] + ", they " +
                        words[1] + " into the sunset under the " + words[7] + " sky.";
                break;
            case 3:
                story = "Deep in the " + words[4] + ", a " + words[2] + " " + words[3] +
                        " was best friends with a " + words[0] + ". They loved to " + words[1] +
                        " together, painting the world " + words[7] + " with laughter and " +
                        words[5] + ". Their adventures became the stuff of legends after " +
                        words[6] + " days.";
                break;
        }

        // Hide inputs, show story
        binding.inputLayout.setVisibility(View.GONE);
        binding.outputLayout.setVisibility(View.VISIBLE);
        binding.storyTextView.setText(story);
    }

    private void restartApp() {
        // Clear inputs
        for (EditText et : inputs) {
            et.setText("");
        }

        // Hide story, show selection again
        binding.outputLayout.setVisibility(View.GONE);
        binding.story1Button.setVisibility(View.VISIBLE);
        binding.story2Button.setVisibility(View.VISIBLE);
        binding.story3Button.setVisibility(View.VISIBLE);
        binding.randomButton.setVisibility(View.VISIBLE);
        binding.titleTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}