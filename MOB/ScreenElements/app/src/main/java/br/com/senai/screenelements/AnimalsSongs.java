package br.com.senai.screenelements;

import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnimalsSongs extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivCat;
    private ImageView ivCow;
    private ImageView ivDog;
    private ImageView ivLion;
    private ImageView ivMonkey;
    private ImageView ivSheep;
    private boolean playingSong;
    private HashMap<ImageView, MediaPlayer> players;
    private int currentResource;
    private MediaPlayer currentPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animal_song);
        //
        ivCat = findViewById(R.id.ivCat);
        ivCow = findViewById(R.id.ivCow);
        ivDog = findViewById(R.id.ivDog);
        ivLion = findViewById(R.id.ivLion);
        ivMonkey = findViewById(R.id.ivMonkey);
        ivSheep = findViewById(R.id.ivSheep);
        playingSong = false;
        players = new HashMap<>();
        //
        Field[] animalsSongsFields = this.getClass().getDeclaredFields();
        Field[] resourcesRaws = R.raw.class.getDeclaredFields();
        //
        for(Field animalSongField : animalsSongsFields) {
            try {
                Object obj = animalSongField.get(this);
                if(obj instanceof View) {
                    ImageView iv = (ImageView)obj;
                    iv.setOnClickListener(this);
                    String viewName = animalSongField.getName().substring(2);
                    //
                    for(Field rawField : resourcesRaws) {
                        if(rawField.getName().equalsIgnoreCase(viewName)) {
                            int resId = (int)rawField.get(this);
                            MediaPlayer player = MediaPlayer.create(this, resId);
                            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mediaPlayer) {
                                    playingSong = false;
                                }
                            });
                            players.put(iv, player);
                            break;
                        }
                    }
                    //
                    iv.setOnClickListener(this);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View view) {
        if(view instanceof ImageView) {
            ImageView iv = (ImageView)view;
            MediaPlayer mediaPlayer = players.get(iv);
            //
            if(currentPlayer == null) {
                currentPlayer = mediaPlayer;
            }
            if(mediaPlayer == currentPlayer)
            {
                if(playingSong) {
                    mediaPlayer.pause();
                }
                else {
                    mediaPlayer.start();
                }
                playingSong ^= true;
            }
            else {
                currentPlayer.pause();
                currentPlayer = mediaPlayer;
                mediaPlayer.start();
            }
        }
    }

    @Override
    protected void onDestroy() {
        // Removing all created MediaPlayers
        Set<Map.Entry<ImageView, MediaPlayer>> entries = players.entrySet();
        for(Map.Entry<ImageView, MediaPlayer> entry : entries) {
            entry.getValue().release();
        }
        // Standard Implementation
        super.onDestroy();
    }
}
