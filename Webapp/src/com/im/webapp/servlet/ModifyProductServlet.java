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

@WebServlet("/modifyProduct")
public class ModifyProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ModifyProductServlet() {
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
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/modifyProductView.jsp");
        dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
	}

}
