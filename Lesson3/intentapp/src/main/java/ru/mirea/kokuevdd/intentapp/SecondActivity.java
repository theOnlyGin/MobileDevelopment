package ru.mirea.kokuevdd.intentapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class SecondActivity extends AppCompatActivity{
    public TextView tvSecAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        String value = (String) getIntent().getSerializableExtra("key");

        tvSecAct = findViewById(R.id.tvSecAct);
        tvSecAct.setText(value);
    }
}
