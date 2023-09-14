package ru.mirea.kokuevdd.fragmentworker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WorkerFragmentSecond#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WorkerFragmentSecond extends Fragment {

    // TODO: Переименуйте параметры имен так, чтобы они соответствовали вашему использованию.
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    static final String TAG = "WorkerFragmentSecond";

    // TODO: Переименуйте и измените типы параметров, если это необходимо для вашего использования.
    private String mParam1;
    private String mParam2;

    public WorkerFragmentSecond() {
        // Обязательный пустой публичный конструктор.
    }

    /**
     * Используйте этот фабричный метод для создания нового экземпляра фрагмента с заданными параметрами.
     *
     * @param param1 Параметр 1.
     * @param param2 Параметр 2.
     * @return Новый экземпляр фрагмента WorkerFragmentSecond.
     */
    // TODO: Переименуйте и измените типы и количество параметров, если это необходимо для вашего использования.
    public static WorkerFragmentSecond newInstance(String param1, String param2) {
        WorkerFragmentSecond fragment = new WorkerFragmentSecond();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "WorkFragmentSecond Start");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "WorkFragmentSecond Destroy");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Надуйте макет для этого фрагмента
        return inflater.inflate(R.layout.fragment_worker_second, container, false);
    }
}
