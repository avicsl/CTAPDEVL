package com.example.labact3item2.models;

import android.os.Parcel;
import android.os.Parcelable;

public class CatalogItem implements Parcelable {

    private String name;
    private String price;
    private int imageResId;
    private String description;

    public CatalogItem(String name, String price, int imageResId, String description) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
        this.description = description;
    }

    protected CatalogItem(Parcel in) {
        name = in.readString();
        price = in.readString();
        imageResId = in.readInt();
        description = in.readString();
    }

    public static final Creator<CatalogItem> CREATOR = new Creator<CatalogItem>() {
        @Override
        public CatalogItem createFromParcel(Parcel in) {
            return new CatalogItem(in);
        }

        @Override
        public CatalogItem[] newArray(int size) {
            return new CatalogItem[size];
        }
    };

    public String getName() { return name; }
    public String getPrice() { return price; }
    public int getImageResId() { return imageResId; }
    public String getDescription() { return description; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(name);
        parcel.writeString(price);
        parcel.writeInt(imageResId);
        parcel.writeString(description);
    }
}
