package com.example.playground.MainActivity.ImageView;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.playground.R;

public class ImageViewActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_image_view);

        Button changeButton = findViewById(R.id.changeButton);
        ImageView imageView = findViewById(R.id.imageView);
        changeButton.setOnClickListener(v -> {
            imageView.setImageResource(R.drawable.car2);
        });
    }
}
