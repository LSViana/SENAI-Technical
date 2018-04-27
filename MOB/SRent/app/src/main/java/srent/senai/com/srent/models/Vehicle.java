package srent.senai.com.srent.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;

public class Vehicle {

    private Long id;
    private String name;
    private String description;
    private byte[] image;
    private Double price;
    private Integer capacity;

    public Vehicle() {

    }

    public Vehicle(Long id, String name, String description, byte[] imagePath, Double price, Integer capacity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = imagePath;
        this.price = price;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Bitmap getBitmap() {
        if(image != null) {
            Bitmap imgBmp = BitmapFactory.decodeByteArray(image, 0, image.length);
            return imgBmp;
        }
        return null;
    }
}
