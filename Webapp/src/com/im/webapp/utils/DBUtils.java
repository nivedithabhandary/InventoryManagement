package com.im.webapp.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.im.webapp.beans.Product;
import com.im.webapp.beans.UserAccount;

public class DBUtils {
	
	  public static UserAccount findUser(Connection conn, String userName, String password) throws SQLException {
		  
	      String sql = "Select a.username, a.passwrd, a.firstname, a.lastname from users a "
	              + " where a.username = ? and a.passwrd= ?";
	 
	      PreparedStatement pstm = conn.prepareStatement(sql);
	      pstm.setString(1, userName);
	      pstm.setString(2, password);
	      ResultSet rs = pstm.executeQuery();
	 
	      if (rs.next()) {
	          String firstName = rs.getString("firstname");
	          String lastName = rs.getString("lastname");
	          UserAccount user = new UserAccount();
	          user.setUserName(userName);
	          user.setPassword(password);
	          user.setFirstName(firstName);
	          user.setLastName(lastName);
	          return user;
	      }
	      return null;
	  }
	  
	  public static UserAccount findUser(Connection conn, String userName) throws SQLException {
		  
	      String sql = "Select a.username, a.passwrd, a.firstname, a.lastname from users a "
	              + " where a.username = ?";
	 
	      PreparedStatement pstm = conn.prepareStatement(sql);
	      pstm.setString(1, userName);
	      ResultSet rs = pstm.executeQuery();
	 
	      if (rs.next()) {
	          String password = rs.getString("passwrd");
	          String firstName = rs.getString("firstname");
	          String lastName = rs.getString("lastname");
	          UserAccount user = new UserAccount();
	          user.setUserName(userName);
	          user.setPassword(password);
	          user.setFirstName(firstName);
	          user.setLastName(lastName);
	          return user;
	      }
	      return null;
	  }
	  
	  public static List<Product> queryInventory(Connection conn) throws SQLException {
	      String sql = "Select a.id, a.product_name, a.product_quantity, a.price from inventory a ";
	 
	      PreparedStatement pstm = conn.prepareStatement(sql);
	 
	      ResultSet rs = pstm.executeQuery();
	      List<Product> list = new ArrayList<Product>();
	      while (rs.next()) {
	          int id = rs.getInt("id");
	          String productName = rs.getString("product_name");
	          int productQuantity = rs.getInt("product_quantity");
	          float price = rs.getFloat("price");
	          Product product = new Product();
	          product.setID(id);
	          product.setProductName(productName);
	          product.setProductQuantity(productQuantity);
	          product.setPrice(price);
	          list.add(product);
	      }
	      return list;
	  }
	  
	  public static Product findProduct(Connection conn, int id) throws SQLException {
	      String sql = "Select a.id, a.product_name, a.product_quantity, a.price from inventory a where a.id=?";
	 
	      PreparedStatement pstm = conn.prepareStatement(sql);
	      pstm.setInt(1, id);
	 
	      ResultSet rs = pstm.executeQuery();
	 
	      while (rs.next()) {
	          String productName = rs.getString("product_name");
	          int productQuantity = rs.getInt("product_quantity");
	          float price = rs.getFloat("price");
	          Product product = new Product(id, productName, productQuantity, price);
	          return product;
	      }
	      return null;
	  }
	 
	  public static void updateProduct(Connection conn, Product product) throws SQLException {
	      String sql = "Update Product set product_name =?, product_quantity =?, price=? where id=? ";
	 
	      PreparedStatement pstm = conn.prepareStatement(sql);
	 
	      pstm.setString(1, product.getProductName());
	      pstm.setInt(2, product.getProductQuantity());
	      pstm.setFloat(3, product.getPrice());
	      pstm.setInt(4, product.getID());
	      pstm.executeUpdate();
	  }
	 
	  public static void insertProduct(Connection conn, Product product) throws SQLException {
	      String sql = "Insert into Product(id, product_name, product_quantity, price) values (?,?,?,?)";
	 
	      PreparedStatement pstm = conn.prepareStatement(sql);
	 
	      pstm.setInt(1, product.getID());
	      pstm.setString(2, product.getProductName());
	      pstm.setInt(3, product.getProductQuantity());
	      pstm.setFloat(4, product.getPrice());
	 
	      pstm.executeUpdate();
	  }
	 
	  public static void deleteProduct(Connection conn, int id) throws SQLException {
	      String sql = "Delete from inventory where id= ?";
	 
	      PreparedStatement pstm = conn.prepareStatement(sql);
	 
	      pstm.setInt(1, id);
	 
	      pstm.executeUpdate();
	  }

}
