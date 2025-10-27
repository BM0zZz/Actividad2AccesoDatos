package com.example;

import com.example.dao.ProductDAO;
import com.example.model.Product;

import java.math.BigDecimal;
import java.util.List;

/**
 * Clase de prueba para verificar el DAO.
 */
public class Main {
    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();

        try {
            // CREATE
            Product nuevo = new Product("Teclado mecánico", new BigDecimal("79.90"), 10,
                    "Periféricos", "Switches rojos");
            nuevo = dao.addProduct(nuevo);
            System.out.println("Creado: " + nuevo);

            // READ by id
            Product p = dao.getProductById(nuevo.getId());
            System.out.println("Leído: " + p);

            // UPDATE
            p.setStock(15);
            p.setPrice(new BigDecimal("74.90"));
            boolean upd = dao.updateProduct(p);
            System.out.println("Actualizado?: " + upd);

            // READ all
            List<Product> all = dao.getAllProducts();
            System.out.println("Listado:");
            all.forEach(System.out::println);

            // DELETE
            boolean del = dao.deleteProduct(p.getId());
            System.out.println("Eliminado?: " + del);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
