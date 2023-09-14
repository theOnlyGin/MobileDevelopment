package ru.mirea.kokuevdd.fileswork;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import ru.mirea.kokuevdd.fileswork.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import java.io.FileInputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private String fileName = "textCrypt.txt"; // Имя файла для зашифрованного текста
    private String fileName1 = "textNormal.txt"; // Имя файла для незашифрованного текста

    public static final String ARG_WORD = "word"; // Ключ для передачи данных между фрагментами

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = getTextFromFile(); // Получение текста из файла

                Snackbar.make(view, "Текст: " + text, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Надуть меню; это добавит элементы в панель действий, если она присутствует.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Обработка нажатий на элементы панели действий.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            // Действие, выполняемое при выборе опции "Настройки" в меню.
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Обработка нажатия кнопки "Назад" на панели действий.
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public String getTextFromFile() {
        FileInputStream fin = null;
        try {
            fin = openFileInput(fileName1); // Открытие файла для чтения
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String(bytes);

            return text; // Возвращение прочитанного текста
        } catch (IOException ex) {
            // Обработка исключения, если чтение файла не удалось
        } finally {
            try {
                if (fin != null)
                    fin.close(); // Закрытие потока чтения файла
            } catch (IOException ex) {
                // Обработка исключения при закрытии файла
            }
        }
        return null; // Возвращение null, если чтение файла не удалось
    }
}
