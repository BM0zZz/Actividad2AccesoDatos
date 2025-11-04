package com.example.dao;

import com.example.DatabaseConnection;
import com.example.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    /** CREATE */
    public Product addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO products (name, price, stock, category, description) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());   // <-- double
            ps.setInt(3, product.getStock());
            ps.setString(4, product.getCategory());
            ps.setString(5, product.getDescription());

            int rows = ps.executeUpdate();
            if (rows == 0) return null;

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    return new Product(
                            id,
                            product.getName(),
                            product.getPrice(),   // double
                            product.getStock(),
                            product.getCategory(),
                            product.getDescription()
                    );
                }
            }
            return null;
        }
    }

    /** READ by id */
    public Product getProductById(int id) throws SQLException {
        String sql = "SELECT * FROM products WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;
                return map(rs);
            }
        }
    }

    /** READ all */
    public List<Product> getAllProducts() throws SQLException {
        String sql = "SELECT * FROM products ORDER BY id";
        List<Product> out = new ArrayList<>();

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) out.add(map(rs));
        }
        return out;
    }

    /** UPDATE */
    public boolean updateProduct(Product p) throws SQLException {
        String sql = "UPDATE products SET name=?, price=?, stock=?, category=?, description=? WHERE id=?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());   // <-- double
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

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        }
    }


    private Product map(ResultSet rs) throws SQLException {
        return new Product(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getDouble("price"),     // <-- double
                rs.getInt("stock"),
                rs.getString("category"),
                rs.getString("description")
        );
    }
}
