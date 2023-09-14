package ru.mirea.kokuevdd.mireaproject.ui.browser;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BrowserViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public BrowserViewModel() {
        // Создаем MutableLiveData с типом String
        mText = new MutableLiveData<>();

        // Устанавливаем начальное значение в MutableLiveData
        mText.setValue("This is home fragment");
    }

    // Метод для получения LiveData с текстовыми данными
    public LiveData<String> getText() {
        return mText;
    }
}
