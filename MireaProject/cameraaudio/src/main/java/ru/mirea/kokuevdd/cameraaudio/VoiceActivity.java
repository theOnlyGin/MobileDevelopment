package ru.mirea.kokuevdd.cameraaudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;

public class VoiceActivity extends AppCompatActivity {
    private String recordFilePath1 = "C://Users//FPS//Documents//audioRecordAS";
    // Путь к файлу записи.

    private final String TAG = VoiceActivity.class.getSimpleName();

    private String recordFilePath = null;
    private Button recordButton = null;
    private Button playButton = null;
    private MediaRecorder recorder = null;
    private MediaPlayer player = null;
    boolean isStartRecording = true;
    boolean isStartPlaying = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);

        recordButton = findViewById(R.id.recordButton);
        playButton = findViewById(R.id.playButton);
        playButton.setEnabled(false);
        // Кнопка Play неактивна до начала записи.
        recordFilePath = (new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC), "C://Users//FPS//Documents//audioRecordAS//audiorecordtest.3gp")).getAbsolutePath();
        // Установка пути для сохранения аудиозаписи.

        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isStartRecording) {
                    recordButton.setText("Stop recording");
                    playButton.setEnabled(false);
                    startRecording();
                } else {
                    recordButton.setText("Start recording");
                    playButton.setEnabled(true);
                    stopRecording();
                }
                isStartRecording = !isStartRecording;
            }
        });
        // Обработчик нажатия на кнопку записи и остановки записи.

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isStartPlaying) {
                    playButton.setText("Stop playing");
                    recordButton.setEnabled(false);
                    startPlaying();
                } else {
                    playButton.setText("Start playing");
                    recordButton.setEnabled(true);
                    stopPlaying();
                }
                isStartPlaying = !isStartPlaying;
            }
        });
        // Обработчик нажатия на кнопку воспроизведения и остановки воспроизведения.
    }

    public void onClickCameraActivityTwo(View view) {
        // Переход к активности CameraActivity
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }

    private void startPlaying() {
        // Начать воспроизведение аудиофайла
        player = new MediaPlayer();
        try {
            player.setDataSource(recordFilePath);
            player.prepare();
            player.start();
        } catch (IOException e) {
            Log.e(TAG, "prepare() failed");
        }
    }

    private void stopPlaying() {
        // Остановить воспроизведение и освободить ресурсы MediaPlayer
        player.release();
        player = null;
    }

    private void startRecording() {
        // Начать запись аудио
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setOutputFile(recordFilePath);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            recorder.prepare();
        } catch (IOException e) {
            Log.e(TAG, "prepare() failed");
        }
        recorder.start();
    }

    private void stopRecording() {
        // Остановить запись и освободить ресурсы MediaRecorder
        recorder.stop();
        recorder.release();
        recorder = null;
    }
}
