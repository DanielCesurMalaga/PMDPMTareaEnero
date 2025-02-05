package com.cesur.pmdmtareaenero;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LocalActivity extends AppCompatActivity {

    private ImageButton localGoesMain;
    private ImageButton playLocal;
    private ImageButton stopLocal;
    private TextView singName;
    private TextView actualTime;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);

        localGoesMain = (ImageButton) findViewById(R.id.localGoesMain);
        playLocal = (ImageButton) findViewById(R.id.playLocal);
        stopLocal = (ImageButton) findViewById(R.id.stopLocal);
        singName = (TextView) findViewById(R.id.singName);
        actualTime = (TextView) findViewById(R.id.actualTimeWeb);



        mediaPlayer = MediaPlayer.create(this, R.raw.battle_stirling);


        singName.setText("Nombre de la canción: The battle of Stirling");

        localGoesMain.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(LocalActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
        );

        playLocal.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if  (mediaPlayer.isPlaying()){
                            mediaPlayer.pause();
                            actualTime.setText("total time: " + formatoTiempo(mediaPlayer.getDuration())
                                    + "\nActual time: " + formatoTiempo(mediaPlayer.getCurrentPosition()));
                            actualTime.setVisibility(View.VISIBLE);

                            playLocal.setImageResource(android.R.drawable.ic_media_play);
                        } else {
                            mediaPlayer.start();
                            actualTime.setVisibility(View.INVISIBLE);
                            playLocal.setImageResource(android.R.drawable.ic_media_pause);
                        }


                    }
                }
        );

        stopLocal.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mediaPlayer.stop();
                        actualTime.setVisibility(View.INVISIBLE);
                        playLocal.setImageResource(android.R.drawable.ic_media_play);
                        try {
                            mediaPlayer.prepare();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer.isPlaying())
            mediaPlayer.stop();
    }

    private String formatoTiempo(int miliSeg) {
        int segundos = miliSeg / 1000;
        int minutos = segundos / 60;
        segundos = segundos - (minutos * 60);
        return ("MIN: " + minutos + " / SEC: " + segundos);

    }
}