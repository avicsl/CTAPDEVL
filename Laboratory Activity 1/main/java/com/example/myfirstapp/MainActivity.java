package com.example.myfirstapp;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    private boolean isCat = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvCaption = findViewById(R.id.tvCaption);
        ImageView imgAnimal = findViewById(R.id.imgAnimal);
        Button btnChange = findViewById(R.id.btnChange);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCat) {
                    imgAnimal.setImageResource(R.drawable.dog); // Make sure you add dog.png too
                    tvCaption.setText("Cute Dog");
                    isCat = false;
                } else {
                    imgAnimal.setImageResource(R.drawable.cat);
                    tvCaption.setText("Cute Cat");
                    isCat = true;
                }
            }
        });
    }
}
