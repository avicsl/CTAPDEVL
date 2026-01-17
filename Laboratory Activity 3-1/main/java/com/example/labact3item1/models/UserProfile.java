package com.example.labact3item1.models;

import android.os.Parcel;
import android.os.Parcelable;

public class UserProfile implements Parcelable {

    private String fullName;
    private String email;
    private String bio;

    public UserProfile(String fullName, String email, String bio) {
        this.fullName = fullName;
        this.email = email;
        this.bio = bio;
    }

    protected UserProfile(Parcel in) {
        fullName = in.readString();
        email = in.readString();
        bio = in.readString();
    }

    public static final Creator<UserProfile> CREATOR = new Creator<UserProfile>() {
        @Override
        public UserProfile createFromParcel(Parcel in) {
            return new UserProfile(in);
        }

        @Override
        public UserProfile[] newArray(int size) {
            return new UserProfile[size];
        }
    };

    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getBio() { return bio; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(fullName);
        parcel.writeString(email);
        parcel.writeString(bio);
    }
}
