package ru.mirea.kokuevdd.fileswork;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MyLoader extends AsyncTaskLoader<String> {

    private String firstName;

    public static final String ARG_WORD = "word";

    // Конструктор для MyLoader, принимающий контекст и аргументы
    public MyLoader(@NonNull Context context, Bundle args) {
        super(context);

        if (args != null) {
            byte[] cryptText = args.getByteArray(ARG_WORD); // Получение зашифрованного текста из аргументов
            byte[] key = args.getByteArray("key"); // Получение ключа из аргументов

            Log.d(MyLoader.class.getSimpleName(), "Key: " + key.length);
            Log.d(MyLoader.class.getSimpleName(), "cryptkey: " + String.valueOf(cryptText));

            SecretKey originalKey = new SecretKeySpec(key, 0, key.length, "AES"); // Создание секретного ключа
            String decryptText = decryptMsg(cryptText, originalKey); // Расшифровка текста

            Log.d(MyLoader.class.getSimpleName(), "decryptText: " + decryptText);

            SecondFragment secondFragment = new SecondFragment();
            Bundle bundle = new Bundle();
            bundle.putString("String", decryptText);

            secondFragment.setArguments(bundle); // Установка аргументов для второго фрагмента
        }
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad(); // Запуск задачи загрузки в фоновом потоке
    }

    @Override
    public String loadInBackground() {
        // Эмуляция долгой операции в фоновом потоке
        SystemClock.sleep(5000);
        return firstName; // Возвращение результата загрузки
    }

    // Метод для расшифровки текста с использованием секретного ключа
    public static String decryptMsg(byte[] cipherText, SecretKey secret) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secret);
            return new String(cipher.doFinal(cipherText)); // Расшифрованный текст
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException |
                 BadPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}

