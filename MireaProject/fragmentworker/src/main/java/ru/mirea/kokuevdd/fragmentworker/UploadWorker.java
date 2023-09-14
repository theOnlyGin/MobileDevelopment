package ru.mirea.kokuevdd.fragmentworker;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.concurrent.TimeUnit;

public class UploadWorker extends Worker {
    FragmentManager fragmentManager;
    static final String TAG = "UploadWorker";

    public UploadWorker(
            @NonNull Context context,
            @NonNull WorkerParameters params) {
        super(context, params);
    }

    @Override
    public Result doWork() {
        Log.d(TAG, "doWork: start"); // Точка начала выполнения работы

        try {
            TimeUnit.SECONDS.sleep(10); // Имитация выполнения работы в течение 10 секунд
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "doWork: end"); // Точка завершения выполнения работы
        return Result.success(); // Возвращение результата успешного выполнения работы
    }
}
