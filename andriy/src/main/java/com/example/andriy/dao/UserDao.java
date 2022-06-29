package com.example.andriy.dao;

import com.example.andriy.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private Connection conn;

    public UserDao(Connection conn) {
        this.conn = conn;
    }

    public List<User> queryUsers () throws SQLException {
        String sql = "select * from users;";
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();

        List<User> users = new ArrayList<User>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            String password = "";
            String role = rs.getString("role");
            String status = rs.getString("status");
            User user = new User(id, name, email, password, role, status);
            users.add(user);
        }
        return users;
    }

    public List<User> queryNonAdminUsers () throws SQLException {
        String sql = "select * from users where role <> 'ADMIN';";
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();

        List<User> users = new ArrayList<User>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            String password = "";
            String role = rs.getString("role");
            String status = rs.getString("status");
            User user = new User(id, name, email, password, role, status);
            users.add(user);
        }
        return users;
    }

    public int insertUser(User user) throws SQLException {
        String sql = "insert into users (name, email, password, role, status) values (?,?,?,?,?);";
        int result = 0;

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, user.getName());
        pstm.setString(2, user.getEmail());
        pstm.setString(3, user.getPassword());
        pstm.setString(4, user.getRole());
        pstm.setString(5, user.getStatus());
        result = pstm.executeUpdate();
        return result;
    }

    public User findUserByEmail(String email, String password) throws SQLException {

        String sql = "Select * from users u  where u.email = ? and u.password= ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, email);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String role = rs.getString("role");
            String status = rs.getString("status");
            User user = new User(id, name, email, password, role, status);
            return user;
        }
        return null;
    }

    public User findUserByUsername(String name, String password) throws SQLException {

        String sql = "Select * from users u where u.name = ? and u.password= ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, name);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("id");
            String email = rs.getString("email");
            String role = rs.getString("role");
            String status = rs.getString("status");
            User user = new User(id, name, email, password, role, status);
            return user;
        }
        return null;
    }

    public User findUserByUsername(String name) throws SQLException {

        String sql = "Select * from users u where u.name = ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, name);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("id");
            String email = rs.getString("email");
            String password = rs.getString("password");
            String role = rs.getString("role");
            String status = rs.getString("status");
            User user = new User(id, name, email, password, role, status);
            return user;
        }
        return null;
    }

    public void updateUser(User user) throws SQLException {
        String sql = "update users set name = ?, role = ?, status =? where id = ?;";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, user.getName());
        pstm.setString(2, user.getRole());
        pstm.setString(3, user.getStatus());
        pstm.setInt(4, user.getId());
        pstm.executeUpdate();
    }

    public void deleteUser(int id) throws SQLException {
        String sql = "delete from users where id= ?";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setInt(1, id);

        pstm.executeUpdate();
    }

    public User findUserById(int id) throws SQLException {
        String sql = "Select * from users u where u.id = ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            String name = rs.getString("name");
            String email = rs.getString("email");
            String password = rs.getString("password");
            String role = rs.getString("role");
            String status = rs.getString("status");
            User user = new User(id, name, email, password, role, status);
            return user;
        }
        return null;
    }

    public void setUserToRegistered(int id) throws SQLException {
        String sql = "update users set status = 'registered' where id = ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);
        pstm.executeUpdate();
    }

    public void blockUserById(int id) throws SQLException {
        String sql = "update users set status = 'blocked' where id = ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);
        pstm.executeUpdate();
    }

    public void unblockUserById(int id) throws SQLException {
        String sql = "update users set status = 'unblocked' where id = ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);
        pstm.executeUpdate();
    }
}
