package ru.mirea.kokuevdd.mireaproject.ui.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DataViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public DataViewModel() {
        // Создаем MutableLiveData с типом String
        mText = new MutableLiveData<>();

        // Устанавливаем начальное значение в MutableLiveData
        mText.setValue("This is slideshow fragment");
    }

    // Метод для получения LiveData с текстовыми данными
    public LiveData<String> getText() {
        return mText;
    }
}
