package ru.mirea.kokuevdd.fileswork;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import ru.mirea.kokuevdd.fileswork.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment  {

    private FragmentFirstBinding binding;
    private String fileName = "textCrypt.txt";

    private String fileName1 = "textNormal.txt";

    private String fileName2 = "textSecret.txt";

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.editTextCrypt.setText(getTextFromFile());

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });


        binding.buttonSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                SecretKey secret = generateKey();

                String editTextText = String.valueOf(binding.editTextCrypt.getText());

                byte[] bytes = encryptMsg(editTextText, secret);


         //       bundle.putByteArray(MyLoader.ARG_WORD,	bytes);
        //        bundle.putByteArray("key",	secret.getEncoded());

                FileOutputStream outputStream;
                try {
                    outputStream = getActivity().openFileOutput(fileName, Context.MODE_PRIVATE);
                    outputStream.write(bytes);
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                FileOutputStream outputStream1;
                try {
                    outputStream1 = getActivity().openFileOutput(fileName1, Context.MODE_PRIVATE);
                    outputStream1.write(editTextText.getBytes(StandardCharsets.UTF_8));
                    outputStream1.close();

                    // Base64.decodeBase64(editTextText)
                } catch (Exception e) {
                    e.printStackTrace();
                }

                FileOutputStream outputStream2;
                try {
                    outputStream2 = getActivity().openFileOutput(fileName2, Context.MODE_PRIVATE);
                    outputStream2.write(secret.getEncoded());
                    outputStream2.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            });
        }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public	static SecretKey generateKey(){
        try	{
            SecureRandom sr	=	SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed("any	data	used	as	random	seed".getBytes());
            KeyGenerator kg	=	KeyGenerator.getInstance("AES");
            kg.init(256,	sr);
            return	new SecretKeySpec((kg.generateKey()).getEncoded(),	"AES");
        }	catch	(NoSuchAlgorithmException e)	{
            throw	new	RuntimeException(e);
        }
    }

    public	static	byte[]	encryptMsg(String	message,	SecretKey	secret)	{
        Cipher cipher	=	null;
        try	{
            cipher	=	Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE,	secret);
            return	cipher.doFinal(message.getBytes());
        }	catch	(NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                       BadPaddingException | IllegalBlockSizeException e)	{
            throw	new	RuntimeException(e);
        }
    }

    public	static	String	decryptMsg(byte[]	cipherText,	SecretKey	secret){
        /*	Decrypt	the	message	*/
        try	{
            Cipher	cipher	=	Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE,	secret);
            return	new	String(cipher.doFinal(cipherText));
        }	catch	(NoSuchAlgorithmException	|	NoSuchPaddingException|	IllegalBlockSizeException
                       |	BadPaddingException	|	InvalidKeyException	e)	{
            throw	new	RuntimeException(e);
        }
    }

    public String getTextFromFile() {
        FileInputStream fin = null;
        try {
            fin = getActivity().openFileInput(fileName1);
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

}