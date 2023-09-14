package ru.mirea.kokuevdd.cameraaudio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import ru.mirea.kokuevdd.cameraaudio.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private ActivityMainBinding binding;
    private TextView magneticxTextView;
    private TextView magneticyTextView;
    private TextView magneticzTextView;
    private SensorManager sensorManager;
    private Sensor magneticSensor;

    private static final int REQUEST_CODE_PERMISSION = 100;
    private static final int CAMERA_REQUEST = 0;
    private boolean isWork = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация менеджера сенсоров и магнитного сенсора
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        magneticSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        // Проверка разрешений для камеры, записи и хранения данных
        int cameraPermissionStatus = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int storagePermissionStatus = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int audioRecordPermissionStatus = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);

        if (cameraPermissionStatus == PackageManager.PERMISSION_GRANTED &&
                storagePermissionStatus == PackageManager.PERMISSION_GRANTED &&
                audioRecordPermissionStatus == PackageManager.PERMISSION_GRANTED) {
            isWork = true;
        } else {
            // Запрос разрешений у пользователя
            ActivityCompat.requestPermissions(this,
                    new String[] {
                            Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.RECORD_AUDIO
                    },
                    REQUEST_CODE_PERMISSION);
        }

        magneticxTextView = findViewById(R.id.textViewAzimuth);
        magneticyTextView = findViewById(R.id.textViewPitch);
        magneticzTextView = findViewById(R.id.textViewRoll);
    }

    public void onClickNewActivity(View view) {
        // Запуск новой активности при нажатии на кнопку
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Отключение регистрации сенсоров при приостановке активности
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Регистрация сенсоров при восстановлении активности
        sensorManager.registerListener(this, magneticSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            float valueX = event.values[0];
            float valueY = event.values[1];
            float valueZ = event.values[2];

            // Установка значений сенсора в текстовые поля
            magneticxTextView.setText("Magnit field x: " + valueX);
            magneticyTextView.setText("Magnit field y: " + valueY);
            magneticzTextView.setText("Magnit field z: " + valueZ);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Обработка изменения точности сенсора (пустой метод)
    }
}
