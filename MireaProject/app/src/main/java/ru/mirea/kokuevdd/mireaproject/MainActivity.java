package ru.mirea.kokuevdd.mireaproject;

import android.os.Bundle;
import android.view.Menu;

import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.kokuevdd.mireaproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Инициализация привязки для активности
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Установка панели инструментов (toolbar) в качестве ActionBar
        setSupportActionBar(binding.appBarMain.toolbar);

        // Получение ссылок на DrawerLayout и NavigationView из привязки
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        // Конфигурация верхних уровней навигации для панели инструментов и боковой панели
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_browser, R.id.nav_gallery, R.id.nav_data)
                .setOpenableLayout(drawer)
                .build();

        // Инициализация NavController для навигации
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);

        // Настройка ActionBar для работы с NavController
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

        // Настройка боковой панели (NavigationView) для работы с NavController
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Надуваем (inflate) меню; это добавляет элементы в панель инструментов, если она присутствует.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Получение NavController для текущего фрагмента и попытка выполнить навигацию назад.
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
