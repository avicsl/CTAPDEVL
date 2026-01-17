package com.example.labact3item1;

import android.content.Intent;
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
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateInputs();
            }
            @Override public void afterTextChanged(Editable s) {}
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

        boolean valid = true;

        if (name.isEmpty()) {
            tilName.setError("Name is required");
            valid = false;
        } else {
            tilName.setError(null);
        }

        if (!email.contains("@")) {
            tilEmail.setError("Email must contain @");
            valid = false;
        } else {
            tilEmail.setError(null);
        }

        if (bio.isEmpty() || bio.length() > 140) {
            tilBio.setError("Bio must be 1–140 characters");
            valid = false;
        } else {
            tilBio.setError(null);
        }

        btnPreview.setEnabled(valid);
    }
}
