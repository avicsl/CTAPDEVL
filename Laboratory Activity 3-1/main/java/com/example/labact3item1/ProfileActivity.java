package com.example.labact3item1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.labact3item1.models.UserProfile;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView tvName = findViewById(R.id.tvName);
        TextView tvEmail = findViewById(R.id.tvEmail);
        TextView tvBio = findViewById(R.id.tvBio);
        Button btnShare = findViewById(R.id.btnShare);

        UserProfile profile = getIntent().getParcelableExtra("profile");

        if (profile != null) {
            tvName.setText(profile.getFullName());
            tvEmail.setText(profile.getEmail());
            tvBio.setText(profile.getBio());
        }

        btnShare.setOnClickListener(v -> {
            String shareText =
                    "Name: " + profile.getFullName() +
                            "\nEmail: " + profile.getEmail() +
                            "\nBio: " + profile.getBio();

            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);

            startActivity(Intent.createChooser(shareIntent, "Share Profile"));
        });
    }
}
