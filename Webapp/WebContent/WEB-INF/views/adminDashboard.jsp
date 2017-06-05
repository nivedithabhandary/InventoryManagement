<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
 </head>
 <body>
 
    <h3>Admin Dashboard</h3>
    
    <h5>Product List</h5>
 
    <p style="color: red;">${errorString}</p>
 
    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>ID</th>
          <th>Product Name</th>
          <th>Product Quantity</th>
          <th>Price</th>
          <th>Edit</th>
          <th>Checkout</th>
          <th>Delete</th>
       </tr>
       <c:forEach items="${productList}" var="product" >
          <tr>
             <td>${product.id}</td>
             <td>${product.name}</td>
             <td>${product.quantity}</td>
             <td>${product.price}</td>
             <td>
                <a href="modifyProduct?id=${product.id}">Modify</a>
             </td>
             <td>
                <a href="checkoutProduct?id=${product.id}">Checkout</a>
             </td>
             <td>
                <a href="deleteProduct?id=${product.id}">Delete</a>
             </td>
             
          </tr>
       </c:forEach>
    </table>
 
    <a href="addProduct" >Add Product</a>
    <br>
    <a href="logout">Logout</a>
 
 </body>
</html>