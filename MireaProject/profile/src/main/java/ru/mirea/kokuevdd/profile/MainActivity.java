package ru.mirea.kokuevdd.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPref;

    EditText editText;
    Fragment fragment1, fragment2;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация фрагментов
        fragment1 = new ProfileFragment();
        fragment2 = new Profile2Fragment();
    }

    public void onClick(View view) {
        fragmentManager = getSupportFragmentManager(); // Получение менеджера фрагментов
        sharedPref = getSharedPreferences("mirea_settings", Context.MODE_PRIVATE); // Получение доступа к SharedPreferences
        SharedPreferences.Editor editor = sharedPref.edit(); // Получение редактора SharedPreferences

        switch (view.getId()){
            case R.id.btnFirstFragment:
                fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment1).
                        commit(); // Замена фрагмента в контейнере

                break;
            case R.id.btnSecondFragment:
                fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment2).
                        commit(); // Замена фрагмента в контейнере

                break;
            default:
                break;
        }
    }
}
