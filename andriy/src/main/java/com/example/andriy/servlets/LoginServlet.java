package com.example.andriy.servlets;

import com.example.andriy.dao.UserDao;
import com.example.andriy.utils.MyUtils;
import com.example.andriy.entities.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMeStr = request.getParameter("rememberMe");
        boolean remember = "Y".equals(rememberMeStr);

        User user = null;
        boolean hasError = false;
        String errorString = null;

        if (username == null || password == null || username.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
        } else {
            Connection conn = MyUtils.getStoredConnection(request);
            try {
                user = new UserDao(conn).findUserByUsername(username, password);

                if (user == null) {
                    hasError = true;
                    errorString = "User Name or password invalid";
                }
            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }
        if (hasError) {
            user = new User();
            user.setName(username);
            user.setPassword(password);

            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);

            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");

            dispatcher.forward(request, response);
        } else {
            HttpSession session = request.getSession();
            MyUtils.storeLoginedUser(session, user);

            if (remember) {
                MyUtils.storeUserCookie(response, user);
            } else {
                MyUtils.deleteUserCookie(response);
            }

            int redirectId = -1;
            try {
                redirectId = Integer.parseInt(request.getParameter("redirectId"));
            } catch (Exception e) {
            }
            String requestUri = MyUtils.getRedirectAfterLoginUrl(request.getSession(), redirectId);
            if (requestUri != null) {
                response.sendRedirect(requestUri);
            } else {
                if(user.getRole().equals("ADMIN")) {
                    response.sendRedirect(request.getContextPath() + "/adminMenu");
                }
                else if (user.getRole().equals("USER")) {
                    response.sendRedirect(request.getContextPath() + "/userPage");
                }
            }
        }
    }
}
