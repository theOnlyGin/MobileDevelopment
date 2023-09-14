package ru.mirea.kokuevdd.mireaproject.ui.data.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.kokuevdd.mireaproject.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Создаем ViewModel для фрагмента галереи с использованием ViewModelProvider
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        // Создаем и связываем макет фрагмента
        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Находим TextView в макете
        final TextView textView = binding.textGallery;

        // Наблюдаем за LiveData из ViewModel и обновляем TextView при изменении данных
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Очищаем ссылку на привязку для избежания утечек памяти
        binding = null;
    }
}
