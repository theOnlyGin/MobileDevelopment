package ru.mirea.kokuevdd.data_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.concurrent.TimeUnit;

import ru.mirea.kokuevdd.data_thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding	=	ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.textView.setText("public final void runOnUIThread - Runs the " +
                "specified action on the " +
                "UI thread. If the current thread is the UI thread, then the action is executed immediately. " +
                "If the current thread is not the UI thread, the action is posted to " +
                "the event queue of the UI thread" +
                "public boolean post - Causes the Runnable to be added to the " +
                "message queue. The runnable will be run " +
                "on the user interface thread." +
                "public boolean postDelayed - Causes the " +
                "Runnable to be added to the message queue, to be run after" +
                " the specified amount of time elapses. The runnable will be run on the " +
                "user interface thread.");

        final Runnable runn1 = new Runnable() {
            public void run() {
                binding.tvInfo.setText("runn1");
            }
        };
        final Runnable runn2 = new Runnable() {
            public void run() {
                binding.tvInfo.setText("runn2");
            }
        };
        final Runnable runn3 = new Runnable() {
            public void run() {
                binding.tvInfo.setText("runn3");
            }
        };
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    runOnUiThread(runn1);
                    TimeUnit.SECONDS.sleep(1);
                    binding.tvInfo.postDelayed(runn3, 2000);
                    binding.tvInfo.post(runn2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }
}