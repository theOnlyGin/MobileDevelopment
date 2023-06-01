package ru.mirea.kokuevdd.nootificationworker;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import ru.mirea.kokuevdd.nootificationworker.databinding.ActivityMainBinding;

// Ваша активность или фрагмент, где инициируется выполнение задачи отправки уведомлений
public class MainActivity extends AppCompatActivity {
    // ...

    private void startNotificationTask() {
        // Создаем запрос на выполнение фонового воркера для отправки уведомлений
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(NotificationWorker.class).build();

        // Запускаем задачу с помощью WorkManager
        WorkManager.getInstance(this).enqueue(workRequest);
    }

    // ...
}
