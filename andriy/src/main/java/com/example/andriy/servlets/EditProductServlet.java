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

@WebServlet(name = "EditProductServlet", value = "/editProduct")
public class EditProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        ProductDao productDao = new ProductDao(conn);

        String idStr = (String) request.getParameter("id");

        Product product = null;

        String errorString = null;
        int id = 0;

        try {
            id = Integer.parseInt(idStr);
            product = productDao.getProductById(id);
        } catch (Exception e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        if (errorString != null && product == null) {
            response.sendRedirect(request.getServletPath() + "/products");
            return;
        }

        request.setAttribute("errorString", errorString);
        request.setAttribute("product", product);

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/editProductView.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        ProductDao productDao = new ProductDao(conn);
        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        String priceStr = request.getParameter("price");
        String date_added = request.getParameter("date_added");
        float price = 0;
        int id = 0;
        try {
            price = Float.parseFloat(priceStr);
            id = Integer.parseInt(idStr);
        } catch (Exception e) {
        }
        Product product = new Product(id, name, price, date_added);

        String errorString = null;

        try {
            productDao.updateProduct(product);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        request.setAttribute("errorString", errorString);
        request.setAttribute("product", product);

        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/editProductView.jsp");
            dispatcher.forward(request, response);
        }
        else {
            response.sendRedirect(request.getContextPath() + "/products");
        }
    }
}
