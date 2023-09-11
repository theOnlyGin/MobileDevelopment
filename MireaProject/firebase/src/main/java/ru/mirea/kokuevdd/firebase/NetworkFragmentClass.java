package ru.mirea.kokuevdd.firebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class NetworkFragmentClass extends AppCompatActivity {
    public String timeData;

    private final String host = "time.nist.gov"; // или time-a.nist.gov
    private final int port = 13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_fragment_class);

        FragmentManager fragmentManager = getSupportFragmentManager();


    }

    public void onClick(View view){
        Intent intent = new Intent(NetworkFragmentClass.this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickUpdate(View view){
        FragmentManager fragmentManager = getSupportFragmentManager();
        GetTimeTask timeTask = new GetTimeTask();
        timeTask.execute();

        // Получаем ссылку на фрагмент по ID
        NetworkFragment fragment = (NetworkFragment) fragmentManager.findFragmentById(R.id.fragment);

// Сетим нужную информацию
        if (fragment != null)
            fragment.setText(timeData);
    }

    private class GetTimeTask extends AsyncTask<Void, Void, String> {
        String[] spisokStrok;

        @Override
        protected String doInBackground(Void... params) {
            String timeResult = "";
            try {
                Socket socket = new Socket(host, port);
                BufferedReader reader = SocketUtils.getReader(socket);
                reader.readLine(); // игнорируем первую строку
                timeResult = reader.readLine(); // считываем вторую строку

                timeData = timeResult;

                spisokStrok = timeResult.split(" ");
                //     Log.d(TAG,timeResult);
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return timeResult;
        }


        /*
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);



        }

         */
    }
}