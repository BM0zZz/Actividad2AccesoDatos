package com.example;

import com.example.dao.ProductDAO;
import com.example.model.Product;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        ProductDAO dao = new ProductDAO();

        // 1) Insertar con datos inválidos (revienta por validación de modelo)
        try {
            Product bad1 = new Product(null, -99.99, null, null);
            dao.addProduct(bad1);
        } catch (IllegalArgumentException | SQLException e) {
            System.out.println("OK (esperado): " + e.getMessage());
        }

        // 2) String demasiado largo (debería fallar en BD si VARCHAR(100))
        try {
            String longName = "A".repeat(500);
            Product bad2 = new Product(longName, 20.0, "Test", "Overflow");
            dao.addProduct(bad2);
        } catch (IllegalArgumentException | SQLException e) {
            System.out.println("OK (esperado): " + e.getMessage());
        }

        // 3) Happy path completo
        Product p = new Product("Teclado mecánico", 79.90, "Periféricos", "Switches rojos");
        p.increaseStock(10);
        p = dao.addProduct(p);
        System.out.println("CREADO: " + p);

        Product leido = dao.getProductById(p.getId());
        System.out.println("LEÍDO: " + leido);

        leido.setPrice(69.90);
        leido.increaseStock(5);
        System.out.println("UPDATE OK? " + dao.updateProduct(leido));

        List<Product> todos = dao.getAllProducts();
        System.out.println("LISTADO:");
        todos.forEach(System.out::println);

        System.out.println("DELETE OK? " + dao.deleteProduct(leido.getId()));
    }
}
