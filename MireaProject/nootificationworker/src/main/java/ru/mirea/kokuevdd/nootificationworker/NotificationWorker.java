package ru.mirea.kokuevdd.nootificationworker;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class NotificationWorker extends Worker {
    public NotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        // Ваш код для отправки уведомлений
        sendNotification();

        // Возвращаем результат выполнения задачи
        return Result.success();
    }

    private void sendNotification() {
        // Создание и настройка уведомления
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "channel_id")
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("Заголовок уведомления")
                .setContentText("Текст уведомления")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Отображение уведомления
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
        notificationManager.notify(0, builder.build());
    }
}
