package com.example.labact3item2;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.labact3item2.models.CatalogItem;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

public class CatalogActivity extends AppCompatActivity {

    private List<CatalogItem> allItems = new ArrayList<>();
    private List<CatalogItem> filteredItems = new ArrayList<>();
    private CatalogAdapter adapter;
    private TextView tvEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // TOOLBAR (NO BACK – LAUNCHER SCREEN)
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // VIEWS
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        EditText etSearch = findViewById(R.id.etSearch);
        tvEmpty = findViewById(R.id.tvEmpty);

        // DATA
        setupData();

        filteredItems.addAll(allItems);
        adapter = new CatalogAdapter(this, filteredItems);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // SEARCH FILTER
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterItems(s.toString());
            }
        });
    }

    private void setupData() {
        allItems.add(new CatalogItem("Chocolate Bar", "₱140.00", R.mipmap.ic_launcher,
                "Sweet milk chocolate snack perfect for quick cravings."));
        allItems.add(new CatalogItem("Potato Chips", "₱110.00", R.mipmap.ic_launcher,
                "Crispy salted potato chips with a classic flavor."));
        allItems.add(new CatalogItem("Soda Drink", "₱85.00", R.mipmap.ic_launcher,
                "Refreshing carbonated beverage served chilled."));
        allItems.add(new CatalogItem("Cookies", "₱180.00", R.mipmap.ic_launcher,
                "Soft baked chocolate chip cookies, freshly packed."));
        allItems.add(new CatalogItem("Candy Mix", "₱55.00", R.mipmap.ic_launcher,
                "Assorted colorful candies with fruity flavors."));
        allItems.add(new CatalogItem("Energy Drink", "₱165.00", R.mipmap.ic_launcher,
                "Energy boost drink to keep you active all day."));
    }

    private void filterItems(String query) {
        filteredItems.clear();

        for (CatalogItem item : allItems) {
            if (item.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredItems.add(item);
            }
        }

        adapter.notifyDataSetChanged();
        tvEmpty.setVisibility(filteredItems.isEmpty()
                ? TextView.VISIBLE
                : TextView.GONE);
    }
}
