package ru.mirea.kokuevdd.cameraaudio;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;

import ru.mirea.kokuevdd.cameraaudio.databinding.ActivityMainBinding;

public class CameraActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_PERMISSION = 100;
    private static final int CAMERA_REQUEST = 0;
    private boolean isWork = false;
    private Uri imageUri;
    ImageView imageViewCamera;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        imageViewCamera = findViewById(R.id.imageView);

        // Создание и отображение изображения на ImageView
        Bitmap bitmap = Bitmap.createBitmap(100, 100,
                Bitmap.Config.ARGB_8888);
        imageViewCamera.setImageBitmap(bitmap);
        bitmap.eraseColor(Color.BLUE);
        bitmap.setPixel(50, 50, Color.RED);

        // Проверка разрешений на камеру и хранение данных
        int cameraPermissionStatus = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA);
        int storagePermissionStatus = ContextCompat.checkSelfPermission(this, android.Manifest.permission.
                WRITE_EXTERNAL_STORAGE);

        if (cameraPermissionStatus == PackageManager.PERMISSION_GRANTED && storagePermissionStatus
                == PackageManager.PERMISSION_GRANTED) {
            isWork = true;
        } else {
            // Запрос разрешений у пользователя
            ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.CAMERA,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION);
        }

        // Callback для обработки результата активности камеры
        ActivityResultCallback<ActivityResult> callback = new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();

                    // Установка полученного изображения на ImageView
                    imageViewCamera.setImageURI(imageUri);
                }
            }
        };

        // Регистрация обработчика результата активности камеры
        ActivityResultLauncher<Intent> cameraActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                callback);

        // Обработчик нажатия на ImageView для запуска камеры
        imageViewCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // Проверка разрешений перед запуском камеры
                if (isWork) {
                    try {
                        // Создание временного файла для сохранения изображения
                        File photoFile = createImageFile();
                        // Генерирование URI для сохранения изображения
                        String authorities = getApplicationContext().getPackageName() + ".fileprovider";
                        imageUri = FileProvider.getUriForFile(CameraActivity.this, authorities, photoFile);
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                        // Запуск активности камеры с использованием обработчика результата
                        cameraActivityResultLauncher.launch(cameraIntent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    // Создание временного файла для сохранения изображения
    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
        String imageFileName = "IMAGE_" + timeStamp + "_";
        File storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(imageFileName, ".jpg", storageDirectory);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // Обработка результата запроса разрешений
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            // Проверка, предоставлены ли разрешения
            isWork = grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED;
        }
    }

    public void onClickMainActivity(View view) {
        // Переход к активности MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickVoiceActivity(View view) {
        // Переход к активности VoiceActivity
        Intent intent = new Intent(this, VoiceActivity.class);
        startActivity(intent);
    }
}