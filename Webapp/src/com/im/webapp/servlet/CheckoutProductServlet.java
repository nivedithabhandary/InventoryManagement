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
import javax.servlet.http.HttpSession;

import com.im.webapp.beans.UserAccount;
import com.im.webapp.beans.Product;
import com.im.webapp.utils.DBUtils;
import com.im.webapp.utils.UserUtils;

@WebServlet("/checkoutProduct")
public class CheckoutProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckoutProductServlet() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = UserUtils.getStoredConnection(request);
        
        String idStr = (String) request.getParameter("id");
 
        Product product = null;
 
        String errorString = null;
        
        int id = 0;
        try {
            id = Integer.parseInt(idStr);
        } catch (Exception e) {
        }
       
        try {
            product = DBUtils.findProduct(conn, id);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        if (errorString != null && product == null) {
            response.sendRedirect(request.getServletPath() + "/adminDashboard");
            return;
        }
 
        request.setAttribute("errorString", errorString);
        request.setAttribute("product", product);
        
        try {
            DBUtils.checkoutProduct(conn, product);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
         
        if (errorString != null) {
            request.setAttribute("errorString", errorString);
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/adminDashboard.jsp");
            dispatcher.forward(request, response);
        }
        else {
            HttpSession session = request.getSession();
            UserAccount user = UserUtils.getLoginedUser(session);
            System.out.println(user.getUserName());
            if (user.getUserName() == "admin") {
                response.sendRedirect(request.getContextPath() + "/adminDashboard");
            } else {
                response.sendRedirect(request.getContextPath() + "/userDashboard");
            }
        }
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
	}

}
