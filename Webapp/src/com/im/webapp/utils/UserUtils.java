package com.im.webapp.utils;

import java.sql.Connection;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.im.webapp.beans.UserAccount;


public class UserUtils {
	   public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";
	   
	   // Store Connection in request attribute.
	   public static void storeConnection(ServletRequest request, Connection conn) {
	       request.setAttribute(ATT_NAME_CONNECTION, conn);
	   }
	 
	   // Get the Connection object has been stored in one attribute of the request.
	   public static Connection getStoredConnection(ServletRequest request) {
	       Connection conn = (Connection) request.getAttribute(ATT_NAME_CONNECTION);
	       return conn;
	   }
	 
	   // Store user info in Session.
	   public static void storeLoginedUser(HttpSession session, UserAccount loginedUser) {
	 
	       // On the JSP can access ${loginedUser}
	       session.setAttribute("loginedUser", loginedUser);
	   }
	 
	   // Get the user information stored in the session.
	   public static UserAccount getLoginedUser(HttpSession session) {
	       UserAccount loginedUser = (UserAccount) session.getAttribute("loginedUser");
	       return loginedUser;
	   }
}
