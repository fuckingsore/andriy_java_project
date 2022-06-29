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

@WebServlet(name = "AddProductServlet", value = "/addProduct")
public class AddProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/addProductView.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        ProductDao productDao = new ProductDao(conn);

        String name = request.getParameter("name");
        String priceStr = request.getParameter("price");
        String date_added = request.getParameter("date_added");
        float price = 0;
        try {
            price = Float.parseFloat(priceStr);
        } catch (Exception e) {
        }
        Product product = new Product(name, price, date_added);

        String errorString = null;

        String regex = "\\w+";

        if (name == null || !name.matches(regex)) {
            errorString = "Product name invalid!";
        }

        if (errorString == null) {
            try {
                productDao.insertProduct(product);
            } catch (SQLException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            }
        }

        request.setAttribute("errorString", errorString);
        request.setAttribute("product", product);

        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/addProductView.jsp");
            dispatcher.forward(request, response);
        }
        else {
            response.sendRedirect(request.getContextPath() + "/products");
        }
    }
}
