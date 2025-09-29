package com.programacion.javacrud.dtos;

public class ProductDTO {
    private Integer id;
    private String name;
    private String description;
    private Float price;
    private boolean isAvailable;
    private Integer categoryId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategory(Integer category) {
        this.categoryId = category;
    }
}
