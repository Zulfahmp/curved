package com.zulfa.furnitureapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class RegiterActivity extends AppCompatActivity {

    private TextView tvnama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regiter);

        tvnama = findViewById(R.id.tvnama);
        tvnama.setText(getIntent().getExtras().getString("Email"));
    }
}
