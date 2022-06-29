package com.example.andriy.servlets;

import com.example.andriy.dao.ProductDao;
import com.example.andriy.dao.UserDao;
import com.example.andriy.entities.User;
import com.example.andriy.utils.MyUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "BuyServlet", value = "/buy")
public class BuyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("cart");

        Connection conn = MyUtils.getStoredConnection(request);
        UserDao userDao = new UserDao(conn);

        User user = MyUtils.getLoginedUser(request.getSession());

        try {
            userDao.setUserToRegistered(user.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect(request.getContextPath() + "/userPage");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
