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
	      String sql = "Select a.id, a.name, a.quantity, a.price from inventory a ";
	 
	      PreparedStatement pstm = conn.prepareStatement(sql);
	 
	      ResultSet rs = pstm.executeQuery();
	      List<Product> list = new ArrayList<Product>();
	      while (rs.next()) {
	          int id = rs.getInt("id");
	          String name = rs.getString("name");
	          int quantity = rs.getInt("quantity");
	          float price = rs.getFloat("price");
	          Product product = new Product();
	          product.setId(id);
	          product.setName(name);
	          product.setQuantity(quantity);
	          product.setPrice(price);
	          list.add(product);
	      }
	      return list;
	  }
	  
	  public static Product findProduct(Connection conn, int id) throws SQLException {
	      String sql = "Select a.id, a.name, a.quantity, a.price from inventory a where a.id=?";
	 
	      PreparedStatement pstm = conn.prepareStatement(sql);
	      pstm.setInt(1, id);
	 
	      ResultSet rs = pstm.executeQuery();
	 
	      while (rs.next()) {
	          String name = rs.getString("name");
	          int quantity = rs.getInt("quantity");
	          float price = rs.getFloat("price");
	          Product product = new Product(id, name, quantity, price);
	          return product;
	      }
	      return null;
	  }
	 
	  public static void updateProduct(Connection conn, Product product) throws SQLException {
	      String sql = "Update inventory set name =?, quantity =?, price=? where id=? ";
	 
	      PreparedStatement pstm = conn.prepareStatement(sql);
	 
	      pstm.setString(1, product.getName());
	      pstm.setInt(2, product.getQuantity());
	      pstm.setFloat(3, product.getPrice());
	      pstm.setInt(4, product.getId());
	      pstm.executeUpdate();
	  }
	 
	  public static void insertProduct(Connection conn, Product product) throws SQLException {
	      String sql = "Insert into inventory(id, name, quantity, price) values (?,?,?,?)";
	 
	      PreparedStatement pstm = conn.prepareStatement(sql);
	 
	      pstm.setInt(1, product.getId());
	      pstm.setString(2, product.getName());
	      pstm.setInt(3, product.getQuantity());
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
