package com.example.andriy.dao;

import com.example.andriy.entities.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private Connection conn;

    public ProductDao(Connection conn) {
        this.conn = conn;
    }

    public List<Product> queryProducts() throws SQLException {
        String sql = "select * from products";

        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();

        List<Product> products = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            Float price = rs.getFloat("price");
            String date_added = rs.getString("date_added");
            Product product = new Product(id, name, price, date_added);
            products.add(product);
        }
        return products;
    }

    public int insertProduct(Product product) throws SQLException {
        String sql = "insert into products (name, price, date_added) values (?,?,?)";
        int result = 0;

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, product.getName());
        pstm.setFloat(2, product.getPrice());
        pstm.setString(3, product.getDate_added());
        result = pstm.executeUpdate();
        return result;
    }

    public Product getProductById(int id) throws SQLException {
        String sql = "select * from products where id = ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);
        ResultSet rs = pstm.executeQuery();

        if(rs.next()) {
            String name = rs.getString("name");
            Float price = rs.getFloat("price");
            String date_added = rs.getString("date_added");
            Product product = new Product(id, name, price, date_added);
            return product;
        }
        return null;
    }

    public Product getProductByName(String name) throws SQLException {
        String sql = "select * from products where name = ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, name);
        ResultSet rs = pstm.executeQuery();

        if(rs.next()) {
            int id = rs.getInt("id");
            Float price = rs.getFloat("price");
            String date_added = rs.getString("date_added");
            Product product = new Product(id, name, price, date_added);
            return product;
        }
        return null;
    }

    public void updateProduct (Product product) throws SQLException {
        String sql = "update products set name = ?, price = ?, date_added = ? where id = ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, product.getName());
        pstm.setFloat(2, product.getPrice());
        pstm.setString(3, product.getDate_added());
        pstm.setInt(4, product.getId());
        pstm.executeUpdate();
    }


    public void deleteProduct(int id) throws SQLException {
        String sql = "delete from products where id= ?";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setInt(1, id);

        pstm.executeUpdate();
    }
}
