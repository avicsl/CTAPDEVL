package com.example.labact3item4;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NotesActivity extends AppCompatActivity {

    private static final String PREF_NAME = "notes_pref";
    private static final String KEY_NOTE = "saved_note";

    private EditText etNote;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        etNote = findViewById(R.id.etNote);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnLoad = findViewById(R.id.btnLoad);
        Button btnDelete = findViewById(R.id.btnDelete);

        preferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        // AUTO-LOAD on launch
        loadNoteOnStart();

        btnSave.setOnClickListener(v -> saveNote());
        btnLoad.setOnClickListener(v -> loadNote());
        btnDelete.setOnClickListener(v -> deleteNote());
    }

    private void loadNoteOnStart() {
        String note = preferences.getString(KEY_NOTE, null);
        if (note != null) {
            etNote.setText(note);
        }
    }

    private void saveNote() {
        String note = etNote.getText().toString().trim();

        if (note.isEmpty()) {
            Toast.makeText(this, "Note is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        preferences.edit().putString(KEY_NOTE, note).apply();
        Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show();
    }

    private void loadNote() {
        String note = preferences.getString(KEY_NOTE, null);

        if (note == null) {
            Toast.makeText(this, "No saved note found", Toast.LENGTH_SHORT).show();
        } else {
            etNote.setText(note);
            Toast.makeText(this, "Note loaded", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteNote() {
        if (!preferences.contains(KEY_NOTE)) {
            Toast.makeText(this, "Nothing to delete", Toast.LENGTH_SHORT).show();
            return;
        }

        preferences.edit().remove(KEY_NOTE).apply();
        etNote.setText("");
        Toast.makeText(this, "Note deleted", Toast.LENGTH_SHORT).show();
    }
}
