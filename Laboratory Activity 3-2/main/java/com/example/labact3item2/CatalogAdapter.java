package com.example.labact3item2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.labact3item2.models.CatalogItem;

import java.util.List;

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.ViewHolder> {

    private List<CatalogItem> items;
    private Context context;

    public CatalogAdapter(Context context, List<CatalogItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_catalog, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CatalogItem item = items.get(position);

        holder.tvName.setText(item.getName());
        holder.tvPrice.setText(item.getPrice());
        holder.imgItem.setImageResource(item.getImageResId());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("item", item);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() { return items.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPrice;
        ImageView imgItem;

        ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            imgItem = itemView.findViewById(R.id.imgItem);
        }
    }
}
