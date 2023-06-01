package ru.mirea.kokuevdd.looper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;


import ru.mirea.kokuevdd.looper.databinding.ActivityMainBinding;

public	class	MainActivity	extends	AppCompatActivity	{
    @Override
    protected	void	onCreate(Bundle	savedInstanceState)	{
        super.onCreate(savedInstanceState);
        ActivityMainBinding	binding	=	ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Handler mainThreadHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Log.d(MainActivity.class.getSimpleName(), "Task execute. This is result: " + msg.getData().getString("result"));
            }
        };

        MyLooper myLooper =	new	MyLooper(mainThreadHandler);
        myLooper.start();
        binding.editTextMirea.setText("Мой номер по списку №11");
        binding.buttonMirea.setOnClickListener(new	View.OnClickListener()	{
            @Override
            public	void	onClick(View	v)	{
                int delay = Integer.parseInt(String.valueOf(binding.editTextAge.getText()));

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                    }
                }, delay);

                Log.d(MainActivity.class.getSimpleName(),	"Возраст: " + binding.editTextAge.getText());
                Log.d(MainActivity.class.getSimpleName(),	"Работа: " + binding.editTextTextWork.getText());

                Message	msg	=	Message.obtain();
                Bundle	bundle	=	new	Bundle();
                bundle.putString("KEY",	"mirea");
                msg.setData(bundle);
                myLooper.mHandler.sendMessage(msg);
            }
        });
    }
}