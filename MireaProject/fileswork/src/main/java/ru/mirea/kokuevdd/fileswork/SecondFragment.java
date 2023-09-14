package ru.mirea.kokuevdd.fileswork;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import ru.mirea.kokuevdd.fileswork.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    private String fileName = "textNormal.txt";
    private String fileName2 = "textSecret.txt";

    public static final String ARG_WORD = "word";

    TextView tv;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Настроить привязку для второго фрагмента
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Установить слушатель для кнопки во втором фрагменте
        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Перейти к первому фрагменту при нажатии кнопки
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });

        // Найти TextView по его идентификатору
        TextView tv = view.findViewById(R.id.textView);

        // Получить текст из файла и установить его в TextView
        String text = getTextFromFile();
        tv.setText(text);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // Метод для чтения текста из файла
    public String getTextFromFile() {
        FileInputStream fin = null;
        try {
            fin = getActivity().openFileInput(fileName);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String(bytes);
            return text;
        } catch (IOException ex) {
            // Обработка ошибок чтения файла
        } finally {
            try {
                if (fin != null)
                    fin.close();
            } catch (IOException ex) {
                // Обработка ошибок закрытия файла
            }
        }
        return null;
    }

    // Метод для получения секретного ключа из файла
    public SecretKey getSecretFromFile() {
        FileInputStream fin = null;
        try {
            fin = getActivity().openFileInput(fileName2);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            SecretKey originalKey = new SecretKeySpec(bytes, 0, bytes.length, "AES");
            return originalKey;
        } catch (IOException ex) {
            // Обработка ошибок чтения файла
        } finally {
            try {
                if (fin != null)
                    fin.close();
            } catch (IOException ex) {
                // Обработка ошибок закрытия файла
            }
        }
        return null;
    }

    // Метод для расшифровки текста с использованием секретного ключа
    public static String decryptMsg(byte[] cipherText, SecretKey secret) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secret);
            return new String(cipher.doFinal(cipherText));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException |
                 BadPaddingException | InvalidKeyException e) {
            // Обработка ошибок расшифровки
            throw new RuntimeException(e);
        }
    }
}


