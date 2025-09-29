package com.programacion.javacrud.dtos;

public class ProductResponseDTO {
    private Integer id;
    private String name;
    private String description;
    private Float price;
    private boolean isAvailable;
    private Integer categoryId;
    private String categoryName;

    public ProductResponseDTO(Integer id,
                              String name,
                              String description,
                              Float price,
                              boolean isAvailable,
                              Integer categoryId,
                              String categoryName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.isAvailable = isAvailable;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
