package com.example.portfolioshowcase.ui.scifiname;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.portfolioshowcase.databinding.FragmentSciFiNameBinding;

public class SciFiNameFragment extends Fragment {

    // Declare the binding object
    private FragmentSciFiNameBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment using View Binding
        binding = FragmentSciFiNameBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set click listener for the Generate button using the binding object
        binding.generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateSciFiName();
            }
        });
    }

    /**
     * Generates the Sci-Fi name and updates the result TextView.
     * This is your original method, adapted to use View Binding.
     */
    private void generateSciFiName() {
        // Capture text input using the binding object
        String firstName = binding.firstNameInput.getText().toString().trim();
        String lastName = binding.lastNameInput.getText().toString().trim();
        String city = binding.cityInput.getText().toString().trim();
        String school = binding.schoolInput.getText().toString().trim();
        String pet = binding.petInput.getText().toString().trim();
        String character = binding.characterInput.getText().toString().trim();

        // Defensive programming: handle short or empty inputs
        if (firstName.length() < 2 || lastName.length() < 3 ||
                city.length() < 2 || school.length() < 3 ||
                pet.isEmpty() || character.isEmpty()) {

            binding.resultTextView.setText("⚠️ Please fill in all fields with enough characters!");
            return;
        }

        // Build Sci-Fi first name (first 2 of first name + first 3 of last name)
        String sciFiFirst = firstName.substring(0, 2) + lastName.substring(0, 3);

        // Build Sci-Fi last name (first 2 of city + first 3 of school)
        String sciFiLast = city.substring(0, 2) + school.substring(0, 3);

        // Build Sci-Fi origin (pet + space + character)
        String sciFiOrigin = pet + " " + character;

        // Combine everything into final sentence
        String finalResult = sciFiFirst.toUpperCase().charAt(0) + sciFiFirst.substring(1).toLowerCase() + " " +
                             sciFiLast.toUpperCase().charAt(0) + sciFiLast.substring(1).toLowerCase() +
                             " from the planet " + sciFiOrigin;

        // Display the result
        binding.resultTextView.setText(finalResult);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Set the binding object to null to avoid memory leaks
        binding = null;
    }
}
