package com.example.labact3item3;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

public class ImagePickerViewModel extends ViewModel {

    private Uri selectedImageUri;

    public Uri getSelectedImageUri() {
        return selectedImageUri;
    }

    public void setSelectedImageUri(Uri uri) {
        this.selectedImageUri = uri;
    }

    public void clearImage() {
        selectedImageUri = null;
    }
}
