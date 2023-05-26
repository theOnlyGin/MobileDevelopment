package ru.mirea.kokuevdd.emptyactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnPress;
    private Button btnFIO;
    private Button btnToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPress = findViewById(R.id.btnPress);
        btnFIO = findViewById(R.id.btnFIO);
        btnToast = findViewById(R.id.btnToast);

        View.OnClickListener oclBtnPress = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri address = Uri.parse("https://www.mirea.ru/");
                Intent openLinkIntent = new Intent(Intent.ACTION_VIEW, address);
                startActivity(openLinkIntent);
            }
        };
        btnPress.setOnClickListener(oclBtnPress);

        View.OnClickListener oclBtnFIO = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "MIREA");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "ФАМИЛИЯ ИМЯ ОТЧЕСТВО");
                startActivity(Intent.createChooser(shareIntent, "МОИ ФИО"));
            }
        };
        btnFIO.setOnClickListener(oclBtnFIO);

        View.OnClickListener oclBtnToast = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Здравствуй MIREA!",
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        };
        btnToast.setOnClickListener(oclBtnToast);
    }
}