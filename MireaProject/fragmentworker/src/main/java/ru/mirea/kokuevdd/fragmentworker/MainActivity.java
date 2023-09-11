package ru.mirea.kokuevdd.fragmentworker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Fragment fragment1, fragment2;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .setRequiresCharging(true)
                .build();
        WorkRequest uploadWorkRequest =
                new OneTimeWorkRequest.Builder(UploadWorker.class)
                        .setConstraints(constraints)
                        .build();
        WorkManager
                .getInstance(this)
                .enqueue(uploadWorkRequest);

        fragment1 = new WorkerFragment();
        fragment2 = new WorkerFragmentSecond();
    }

    public void onClick(View view) {
        fragmentManager = getSupportFragmentManager();
        switch (view.getId()){
            case R.id.btnFirstFragment:
                fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment1).
                        commit();
                OneTimeWorkRequest myWorkRequest = new OneTimeWorkRequest.Builder(UploadWorker.class).build();
                 WorkManager.getInstance().enqueue(myWorkRequest);
                break;
            case R.id.btnSecondFragment:
                fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment2).
                        commit();
                 myWorkRequest = new OneTimeWorkRequest.Builder(UploadWorker.class).build();
                WorkManager.getInstance().enqueue(myWorkRequest);
                break;
            default:
                break;
        }
    }
}