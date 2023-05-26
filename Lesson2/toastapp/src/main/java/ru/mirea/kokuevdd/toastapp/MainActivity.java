package ru.mirea.kokuevdd.toastapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText etPole;
    private Button btnMagic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etPole = findViewById(R.id.etPole);
        btnMagic = findViewById(R.id.btnMagic);



        View.OnClickListener oclBtnMagic = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numberOfCharacters = etPole.getText().length();
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Number of characters: " + numberOfCharacters,
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        };
        btnMagic.setOnClickListener(oclBtnMagic);
    }
}