package com.example;

import com.example.model.Product;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        try {
            Product p1 = new Product(null, new BigDecimal("20.00"), "Varios", "Sin nombre");
        } catch (Exception e) {
            System.out.println("✅ name nulo: " + e.getMessage());
        }

        try {
            Product p2 = new Product("Producto X", new BigDecimal("20.00"), null, "Sin categoría");
        } catch (Exception e) {
            System.out.println("✅ category nulo: " + e.getMessage());
        }
    }
}
