package com.example.labact3item2;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.labact3item2.models.CatalogItem;
import com.google.android.material.appbar.MaterialToolbar;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // TOOLBAR (Pink + Back)
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(v -> finish());

        // VIEWS
        ImageView imgDetail = findViewById(R.id.imgDetail);
        TextView tvName = findViewById(R.id.tvName);
        TextView tvPrice = findViewById(R.id.tvPrice);
        TextView tvDescription = findViewById(R.id.tvDescription);

        // GET PARCELABLE
        CatalogItem item = getIntent().getParcelableExtra("item");

        if (item != null) {
            imgDetail.setImageResource(item.getImageResId());
            tvName.setText(item.getName());
            tvPrice.setText(item.getPrice());
            tvDescription.setText(item.getDescription());
        }
    }
}
