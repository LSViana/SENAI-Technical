package br.com.senai.screenelements;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.senai.screenelements.musicplayer.model.Song;

public class MusicPlayer extends AppCompatActivity {

    private ListView lvSongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_player);
        // Getting all Songs
        List<Song> songs = GetSongs();
        // Getting UI Elements
        lvSongs = findViewById(R.id.lvSongs);
        // Creating Adapter to Songs
        ArrayAdapter<Song> songAdapter = new ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1, songs);
        lvSongs.setAdapter(songAdapter);
    }

    private List<Song> GetSongs() {
        List<Song> result = new ArrayList<>();
        //
        result.add(new Song("Song1", "Author1", 0));
        result.add(new Song("Song2", "Author2", 0));
        result.add(new Song("Song3", "Author3", 0));
        //
        return result;
    }
}
