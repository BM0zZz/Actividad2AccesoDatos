package com.example.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Modelo de Producto con ID inmutable (lo asigna la BD).
 * Validaciones: name/category no vacíos, price >= 0, stock >= 0.
 */
public class Product {
    private final int id;               // 0 si no está persistido aún; inmutable
    private String name;
    private BigDecimal price;
    private int stock;
    private String category;
    private String description;

    // Alta (sin id, stock arranca a 0 por diseño)
    public Product(String name, BigDecimal price, String category, String description) {
        this(0, name, price, 0, category, description);
    }

    // Existente (con id asignado por la BD)
    public Product(int id, String name, BigDecimal price, int stock, String category, String description) {
        if (id < 0) throw new IllegalArgumentException("id inválido");
        this.id = id;
        setName(name);
        setPrice(price);
        setStock(stock);
        setCategory(category);
        setDescription(description);
    }

    // Getters (sin setter de id)
    public int getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getPrice() { return price; }
    public int getStock() { return stock; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }

    // Setters con validación
    public void setName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name obligatorio");
        this.name = name.trim();
    }

    public void setPrice(BigDecimal price) {
        Objects.requireNonNull(price, "precio obligatorio");
        if (price.signum() < 0) throw new IllegalArgumentException("price no puede ser negativo");
        this.price = price;
    }

    public void setStock(int stock) {
        if (stock < 0) throw new IllegalArgumentException("stock no puede ser negativo");
        this.stock = stock;
    }

    public void setCategory(String category) {
        if (category == null || category.isBlank()) throw new IllegalArgumentException("category obligatoria");
        this.category = category.trim();
    }

    public void setDescription(String description) {
        this.description = (description == null) ? "" : description.trim();
    }


    public void increaseStock(int units) {
        if (units <= 0) throw new IllegalArgumentException("unidades > 0");
        this.stock += units;
    }
    public void decreaseStock(int units) {
        if (units <= 0) throw new IllegalArgumentException("unidades > 0");
        if (units > this.stock) throw new IllegalArgumentException("no hay stock suficiente");
        this.stock -= units;
    }

    @Override
    public String toString() {
        return "Product{id=" + id + ", name='" + name + '\'' +
                ", price=" + price + ", stock=" + stock +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' + '}';
    }
}
