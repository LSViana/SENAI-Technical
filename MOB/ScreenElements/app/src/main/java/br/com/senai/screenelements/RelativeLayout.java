package br.com.senai.screenelements;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

public class RelativeLayout extends AppCompatActivity {

    private ToggleButton buttonPlaySong;
    private MediaPlayer mediaPlayer;
    private boolean playingSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relative_layout);
        // Creating MediaPlayer to play song
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.beautiful_girls);
        // Getting button from view through ID
        buttonPlaySong = findViewById(R.id.btnPlaySong);
        playingSong = buttonPlaySong.isPressed();
        // Attach method to execute OnClick
        buttonPlaySong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleMediaPlayer(playingSong ^= true);
            }
        });
    }

    private void toggleMediaPlayer(boolean play) {
        if(play)
            mediaPlayer.start();
        else
            mediaPlayer.pause();
    }
}
