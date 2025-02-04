package com.cesur.pmdmtareaenero;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

public class WebActivity extends AppCompatActivity {

    private ImageButton webGoesMain;
    private ImageButton playWeb;
    private ImageButton stopWeb;
    private ImageButton pauseWeb;
    private TextView singNameWeb;
    private TextView actualTimeWeb;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        webGoesMain = (ImageButton) findViewById(R.id.webGoesMain);
        playWeb = (ImageButton) findViewById(R.id.playWeb);
        stopWeb = (ImageButton) findViewById(R.id.stopWeb);
        pauseWeb = (ImageButton) findViewById(R.id.pauseWeb);
        singNameWeb = (TextView) findViewById(R.id.singNameWeb);
        actualTimeWeb = (TextView) findViewById(R.id.actualTimeWeb);

        //mediaPlayer = MediaPlayer.create(this, R.raw.battle_stirling);
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource("https://assets.mixkit.co/music/130/130.mp3");
            mediaPlayer.prepare();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        singNameWeb.setText("Nombre de la canción: Tech House Vibes / Alejandro Magaña");

        webGoesMain.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(WebActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
        );

        playWeb.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mediaPlayer.start();
                        actualTimeWeb.setVisibility(View.INVISIBLE);
                    }
                }
        );

        stopWeb.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mediaPlayer.stop();
                        try {
                            mediaPlayer.prepare();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );

        pauseWeb.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mediaPlayer.pause();
                        actualTimeWeb.setText("total time: " + mediaPlayer.getDuration()
                                + " / actual time: " + mediaPlayer.getCurrentPosition());
                        actualTimeWeb.setVisibility(View.VISIBLE);
                    }
                }
        );


    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.stop();
    }
}