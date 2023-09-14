package ru.mirea.kokuevdd.mireaproject.ui.data.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GalleryViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public GalleryViewModel() {
        // Создаем MutableLiveData с типом String
        mText = new MutableLiveData<>();

        // Устанавливаем начальное значение в MutableLiveData
        mText.setValue("This is gallery fragment");
    }

    // Метод для получения LiveData с текстовыми данными
    public LiveData<String> getText() {
        return mText;
    }
}
