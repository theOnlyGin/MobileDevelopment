package ru.mirea.kokuevdd.mireaproject.ui.browser;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.kokuevdd.mireaproject.R;
import ru.mirea.kokuevdd.mireaproject.WebViewFragment;
import ru.mirea.kokuevdd.mireaproject.databinding.FragmentBrowserBinding;

public class BrowserFragment extends Fragment {

    private FragmentBrowserBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Создаем ViewModel для фрагмента браузера с использованием ViewModelProvider
        BrowserViewModel browserViewModel =
                new ViewModelProvider(this).get(BrowserViewModel.class);

        // Создаем и связываем макет фрагмента
        binding = FragmentBrowserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Находим WebView в макете
        WebView webView = root.findViewById(R.id.web_view_g);

        // Загружаем URL в WebView (в данном случае, google.com)
        webView.loadUrl("https://google.com/");

        // Устанавливаем WebViewClient для обработки загрузки URL в WebView
        webView.setWebViewClient(new WebViewFragment());

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Очищаем ссылку на привязку для избежания утечек памяти
        binding = null;
    }
}
