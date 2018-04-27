package srent.senai.com.srent.models;

public class Van extends Vehicle {

    public Van() {
    }

    public Van(Long id, String name, String description, byte[] image, Double price, Integer capacity) {
        super(id, name, description, image, price, capacity);
    }

}
