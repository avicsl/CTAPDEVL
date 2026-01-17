package com.example.labact3item3;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class ImagePickerActivity extends AppCompatActivity {

    private ImageView imageView;
    private ImagePickerViewModel viewModel;

    private final ActivityResultLauncher<Intent> imagePickerLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                            Uri uri = result.getData().getData();
                            viewModel.setSelectedImageUri(uri);
                            imageView.setImageURI(uri);
                        } else {
                            Toast.makeText(this, "Image selection cancelled", Toast.LENGTH_SHORT).show();
                        }
                    }
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_picker);

        imageView = findViewById(R.id.imageView);
        Button btnPick = findViewById(R.id.btnPick);
        Button btnClear = findViewById(R.id.btnClear);

        // VIEWMODEL (BONUS)
        viewModel = new ViewModelProvider(this).get(ImagePickerViewModel.class);

        // Restore image after rotation
        if (viewModel.getSelectedImageUri() != null) {
            imageView.setImageURI(viewModel.getSelectedImageUri());
        }

        btnPick.setOnClickListener(v -> openGallery());

        btnClear.setOnClickListener(v -> {
            viewModel.clearImage();
            imageView.setImageResource(android.R.drawable.ic_menu_gallery);
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        imagePickerLauncher.launch(intent);
    }
}
