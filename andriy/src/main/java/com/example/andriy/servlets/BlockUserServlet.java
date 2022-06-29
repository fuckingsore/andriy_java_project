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

@WebServlet(name = "BlockUserServlet", value = "/blockUser")
public class BlockUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        UserDao userDao = new UserDao(conn);

        String idStr = (String) request.getParameter("id");

        int id = 0;
        try {
            id = Integer.parseInt(idStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String errorString = null;

        try {
            userDao.blockUserById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        if (errorString != null) {
            request.setAttribute("errorString", errorString);
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/userListView.jsp");
            dispatcher.forward(request, response);
        }
        else {
            response.sendRedirect(request.getContextPath() + "/users");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
