package com.example.labact3item1;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.labact3item1.models.UserProfile;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class MainActivity extends AppCompatActivity {

    private TextInputEditText etName, etEmail, etBio;
    private TextInputLayout tilName, tilEmail, tilBio;
    private Button btnPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tilName = findViewById(R.id.tilName);
        tilEmail = findViewById(R.id.tilEmail);
        tilBio = findViewById(R.id.tilBio);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etBio = findViewById(R.id.etBio);
        btnPreview = findViewById(R.id.btnPreview);

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };

        etName.addTextChangedListener(watcher);
        etEmail.addTextChangedListener(watcher);
        etBio.addTextChangedListener(watcher);

        btnPreview.setOnClickListener(v -> {
            UserProfile profile = new UserProfile(
                    etName.getText().toString().trim(),
                    etEmail.getText().toString().trim(),
                    etBio.getText().toString().trim()
            );

            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            intent.putExtra("profile", profile);
            startActivity(intent);
        });
    }

    private void validateInputs() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String bio = etBio.getText().toString().trim();

        boolean isValid = true;

        // NAME validation (required)
        if (name.isEmpty()) {
            isValid = false;
        }

        // EMAIL validation (required + @)
        if (email.isEmpty() || !email.contains("@")) {
            tilEmail.setError("Email must contain @");
            isValid = false;
        } else {
            tilEmail.setError(null);
        }

        // BIO validation (required + max length)
        if (bio.isEmpty()) {
            tilBio.setError("Bio is required");
            isValid = false;
        } else if (bio.length() > 140) {
            tilBio.setError("Bio must be 140 characters or less");
            isValid = false;
        } else {
            tilBio.setError(null);
        }

        // BUTTON STATE
        btnPreview.setEnabled(isValid);
        btnPreview.setClickable(isValid);

        btnPreview.setBackgroundTintList(
                ColorStateList.valueOf(
                        Color.parseColor(isValid ? "#EC407A" : "#F8BBD0")
                )
        );
    }

}