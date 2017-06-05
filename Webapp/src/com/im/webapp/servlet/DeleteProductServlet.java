package com.im.webapp.servlet;

import java.sql.Connection;
import java.sql.SQLException;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.im.webapp.beans.Product;
import com.im.webapp.utils.DBUtils;
import com.im.webapp.utils.UserUtils;

@WebServlet(urlPatterns = { "/deleteProduct" })
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteProductServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = UserUtils.getStoredConnection(request);
        
        String idStr = (String) request.getParameter("id");
        int id = 0;
        try {
            id = Integer.parseInt(idStr);
        } catch (Exception e) {
        }
 
        String errorString = null;
 
        try {
            DBUtils.deleteProduct(conn, id);
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
            response.sendRedirect(request.getContextPath() + "/adminDashboard");
        }
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
