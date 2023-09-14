package ru.mirea.kokuevdd.fragmentworker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WorkerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WorkerFragment extends Fragment {

    // TODO: Переименуйте параметры имен так, чтобы они соответствовали вашему использованию.
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    static final String TAG = "WorkerFragment";

    // TODO: Переименуйте и измените типы параметров, если это необходимо для вашего использования.
    private String mParam1;
    private String mParam2;

    public WorkerFragment() {
        // Обязательный конструктор по умолчанию.
    }

    /**
     * Используйте этот фабричный метод для создания нового экземпляра фрагмента с заданными параметрами.
     *
     * @param param1 Параметр 1.
     * @param param2 Параметр 2.
     * @return Новый экземпляр фрагмента WorkerFragment.
     */
    // TODO: Переименуйте и измените типы и количество параметров, если это необходимо для вашего использования.
    public static WorkerFragment newInstance(String param1, String param2) {
        WorkerFragment fragment = new WorkerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "WorkFragment Start");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "WorkFragment Destroy");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Runnable runn1 = new Runnable() {
            public void run() {
                Log.d(TAG, "Worker Fragment");
            }
        };

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Надуйте макет для этого фрагмента
        View view = inflater.inflate(R.layout.fragment_worker, container, false);

        // Получите доступ к элементам интерфейса пользователя, если это необходимо.

        return view;
    }
}
