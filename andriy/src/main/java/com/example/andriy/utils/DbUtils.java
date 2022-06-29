package com.example.andriy.utils;

import com.example.andriy.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbUtils {

    public static User findUserByUsername(Connection conn, //
                                       String username, String password) throws SQLException {

        String sql = "Select * from users u "
                + " where u.username = ? and u.password= ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, username);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("id");
            String email = rs.getString("email");
            String role = rs.getString("role");
            String status = rs.getString("status");
            float money = rs.getFloat("money");
            User user = new User(id, username, email, password, role, status);
            return user;
        }
        return null;
    }

    public static User findUserByUsername(Connection conn, //
                                          String username) throws SQLException {

        String sql = "Select * from users u "
                + " where u.username = ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, username);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("id");
            String email = rs.getString("email");
            String password = rs.getString("password");
            String role = rs.getString("role");
            String status = rs.getString("status");
            float money = rs.getFloat("money");
            User user = new User(id, username, email, password, role, status);
            return user;
        }
        return null;
    }
}
