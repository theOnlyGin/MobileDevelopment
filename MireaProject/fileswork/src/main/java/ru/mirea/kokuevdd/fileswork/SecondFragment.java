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

    public	static	final	String	ARG_WORD	=	"word";

    TextView tv;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });

        TextView tv = view.findViewById(R.id.textView);

        String text = getTextFromFile();

     //   byte[] secretText = getTextFromFile();

     //   SecretKey originalKey = getSecretFromFile();

    //    String decryptText = decryptMsg(secretText, originalKey);

        tv.setText(text);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public String getTextFromFile() {
        FileInputStream fin = null;
        try {
            fin = getActivity().openFileInput(fileName);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String(bytes);

            return text;
        } catch (IOException ex) {
        } finally {
            try {
                if (fin != null)
                    fin.close();
            } catch (IOException ex) {
            }
        }
        return null;
    }

    public SecretKey getSecretFromFile() {
        FileInputStream fin = null;
        try {
            fin = getActivity().openFileInput(fileName2);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);

            SecretKey originalKey =	new SecretKeySpec(bytes,	0,	bytes.length,	"AES");

            return originalKey;
        } catch (IOException ex) {
        } finally {
            try {
                if (fin != null)
                    fin.close();
            } catch (IOException ex) {
            }
        }
        return null;
    }

    public	static	String	decryptMsg(byte[]	cipherText,	SecretKey	secret){
        /*	Decrypt	the	message	*/
        try	{
            Cipher cipher	=	Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE,	secret);
            return	new	String(cipher.doFinal(cipherText));
        }	catch	(NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
                       | BadPaddingException | InvalidKeyException e)	{
            throw	new	RuntimeException(e);
        }
    }
}

