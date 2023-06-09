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
    Fragment fragment;
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

        fragment = new FragmentWorker();
    }

    public void click(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (view.getId()){
            case R.id.buttonK:
                fragmentManager.beginTransaction().replace(R.id.frameK, fragment).
                        commit();
                OneTimeWorkRequest myWorkRequest = new OneTimeWorkRequest.Builder(UploadWorker.class).build();
                WorkManager.getInstance().enqueue(myWorkRequest);
                break;
            default:
                break;
        }
    }
}