package com.im.webapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.im.webapp.beans.Product;
import com.im.webapp.utils.DBUtils;
import com.im.webapp.utils.UserUtils;


@WebServlet("/doModifyProduct")
public class DoModifyProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DoModifyProductServlet() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Connection conn = UserUtils.getStoredConnection(request);
    	 
    	String idStr = (String) request.getParameter("id");
        String name = (String) request.getParameter("name");
        String quantityStr = (String) request.getParameter("quantity");
        String priceStr = (String) request.getParameter("price");
        
        int id = 0;
        try {
            id = Integer.parseInt(idStr);
        } catch (Exception e) {
        }
        
        float price = 0;
        try {
            price = Float.parseFloat(priceStr);
        } catch (Exception e) {
        }
        
        int quantity = 0;
        try {
        	quantity = Integer.parseInt(quantityStr);
        } catch (Exception e) {
        }
        
        Product product = new Product(id, name, quantity, price);
        String errorString = null;
  
        try {
            DBUtils.updateProduct(conn, product);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        
        request.setAttribute("errorString", errorString);
        request.setAttribute("product", product);
  
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/modifyProductView.jsp");
            dispatcher.forward(request, response);
        }           
        else {
            response.sendRedirect(request.getContextPath() + "/adminDashboard");
        }
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
