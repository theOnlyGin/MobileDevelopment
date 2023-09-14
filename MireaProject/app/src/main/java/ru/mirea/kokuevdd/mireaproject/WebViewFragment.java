package ru.mirea.kokuevdd.mireaproject;


import android.webkit.WebView;
import android.webkit.WebViewClient;

// class is extended to WebViewClient to access the WebView
public class WebViewFragment extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        // Функция loadUrl загрузит URL, который мы передадим, в наш WebView.
        view.loadUrl(url);

        // Возвращаем true, чтобы указать, что мы обработали переход по URL сами.
        return true;
    }
}

