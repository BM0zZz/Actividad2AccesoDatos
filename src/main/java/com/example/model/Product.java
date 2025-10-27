package com.example.model;

import java.math.BigDecimal;

/**
 * Clase modelo de Producto que representa una entidad 'products'.
 */
public class Product {
    private int id;
    private String name;
    private BigDecimal price;
    private int stock;
    private String category;
    private String description;

    // Constructor por defecto
    public Product() {}

    // Constructor sin ID (para crear nuevos productos)
    public Product(String name, BigDecimal price, int stock, String category, String description) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.description = description;
    }

    // Constructor con ID (para productos existentes)
    public Product(int id, String name, BigDecimal price, int stock, String category, String description) {
        this(name, price, stock, category, description);
        this.id = id;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
