package srent.senai.com.srent.models;

public class Bus extends Vehicle {

    public Bus() {
    }

    public Bus(Long id, String name, String description, byte[] image, Double price, Integer capacity) {
        super(id, name, description, image, price, capacity);
    }

}
