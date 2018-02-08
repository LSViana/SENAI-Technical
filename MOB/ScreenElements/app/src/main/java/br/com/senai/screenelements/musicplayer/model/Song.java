package br.com.senai.screenelements.musicplayer.model;

public class Song {

    private String name;
    private String author;
    private Integer albumResId;

    public Song(String name, String author, Integer albumResId) {
        this.name = name;
        this.author = author;
        this.albumResId = albumResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getAlbumResId() {
        return albumResId;
    }

    public void setAlbumResId(Integer albumResId) {
        this.albumResId = albumResId;
    }

    @Override
    public String toString() {
        return String.format("%s\n%s", name, author);
    }
}
