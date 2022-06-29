package com.example.andriy.servlets;

import com.example.andriy.dao.ProductDao;
import com.example.andriy.entities.Product;
import com.example.andriy.utils.MyUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddToCartServlet", value = "/addToCart")
public class AddToCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = (String) request.getParameter("id");
        int id = 0;

        try{
            id = Integer.parseInt(idStr);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        Connection conn = MyUtils.getStoredConnection(request);
        ProductDao productDao = new ProductDao(conn);

        String errorString = null;

        try {
            List<Product> cart = (List<Product>) request.getSession().getAttribute("cart");

            if(cart == null) {
                cart = new ArrayList<>();
            }

            Product product = null;
            try {
                product = productDao.getProductById(id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            cart.add(product);

            request.getSession().setAttribute("cart", cart);
        } catch (Exception e) {
            errorString = e.getMessage();
        }

        if (errorString != null) {
            request.setAttribute("errorString", errorString);
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/userPageView.jsp");
            dispatcher.forward(request, response);
        }
        else {
            response.sendRedirect(request.getContextPath() + "/userPage");
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
