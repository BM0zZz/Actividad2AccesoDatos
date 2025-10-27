package com.example.dao;

import com.example.DatabaseConnection;
import com.example.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO de Producto: CRUD completo usando JDBC.
 */
public class ProductDAO {

    /** CREATE */
    public Product addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO products (name, price, stock, category, description) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, product.getName());
            ps.setBigDecimal(2, product.getPrice());
            ps.setInt(3, product.getStock());
            ps.setString(4, product.getCategory());
            ps.setString(5, product.getDescription());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) product.setId(keys.getInt(1));
                }
                return product;
            }
            return null;
        }
    }

    /** READ by id */
    public Product getProductById(int id) throws SQLException {
        String sql = "SELECT * FROM products WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getBigDecimal("price"),
                            rs.getInt("stock"),
                            rs.getString("category"),
                            rs.getString("description")
                    );
                }
                return null;
            }
        }
    }

    /** READ all */
    public List<Product> getAllProducts() throws SQLException {
        String sql = "SELECT * FROM products ORDER BY id";
        List<Product> list = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getBigDecimal("price"),
                        rs.getInt("stock"),
                        rs.getString("category"),
                        rs.getString("description")
                ));
            }
        }
        return list;
    }

    /** UPDATE */
    public boolean updateProduct(Product p) throws SQLException {
        String sql = "UPDATE products SET name=?, price=?, stock=?, category=?, description=? WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getName());
            ps.setBigDecimal(2, p.getPrice());
            ps.setInt(3, p.getStock());
            ps.setString(4, p.getCategory());
            ps.setString(5, p.getDescription());
            ps.setInt(6, p.getId());

            return ps.executeUpdate() == 1;
        }
    }

    /** DELETE */
    public boolean deleteProduct(int id) throws SQLException {
        String sql = "DELETE FROM products WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        }
    }
}
