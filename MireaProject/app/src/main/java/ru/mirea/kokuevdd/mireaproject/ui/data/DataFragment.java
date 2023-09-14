package ru.mirea.kokuevdd.mireaproject.ui.data;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.kokuevdd.mireaproject.databinding.FragmentDataBinding;

public class DataFragment extends Fragment {

    private FragmentDataBinding binding;

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Создаем ViewModel для фрагмента данных с использованием ViewModelProvider
        DataViewModel dataViewModel =
                new ViewModelProvider(this).get(DataViewModel.class);

        // Создаем и связываем макет фрагмента
        binding = FragmentDataBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Находим TextView в макете
        final TextView textView = binding.textData;
        final TextView textView2 = binding.textData2;

        // Устанавливаем текст в TextView с помощью метода setText
        textView.setText("Android Studio - это официальная интегрированная среда разработки (IDE) " +
                "для разработки приложений для Android. Android Studio, основанная на мощном " +
                "редакторе кода и инструментах разработчика IntelliJ IDEA, предлагает еще больше " +
                "возможностей, повышающих производительность при создании приложений для Android.");

        textView2.setText("Material Design — дизайн-система для создания интерфейсов программного" +
                " обеспечения и приложений, разработанная компанией Google.");

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Очищаем ссылку на привязку для избежания утечек памяти
        binding = null;
    }
}
