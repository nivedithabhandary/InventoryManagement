package com.im.webapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.im.webapp.beans.Product;
import com.im.webapp.utils.DBUtils;
import com.im.webapp.utils.UserUtils;

@WebServlet(urlPatterns = { "/userDashboard" })
public class UserDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserDashboardServlet() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = UserUtils.getStoredConnection(request);
        
        String errorString = null;
        List<Product> list = null;
        try {
            list = DBUtils.queryInventory(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
   
        // Store info in request attribute, before forward to views
        request.setAttribute("errorString", errorString);
        request.setAttribute("productList", list);
         
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/userDashboard.jsp");
        dispatcher.forward(request, response);
	}
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
	}

}
