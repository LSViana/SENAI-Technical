package srent.senai.com.srent.models;

public class Vehicle {

    private Long id;
    private String name;
    private String description;
    private Integer imageResId;
    private Double price;

    public Vehicle() {

    }

    public Vehicle(Long id, String name, String description, Integer imageResId, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageResId = imageResId;
        this.price = price;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getImageResId() {
        return imageResId;
    }

    public void setImageResId(Integer imageResId) {
        this.imageResId = imageResId;
    }
}
