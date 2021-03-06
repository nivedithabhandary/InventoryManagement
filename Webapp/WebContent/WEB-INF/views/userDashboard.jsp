<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>User Dashboard</title>
 </head>
 <body>
 
    <h3>User Dashboard</h3>
    
    <h5>Product List</h5>
 
    <p style="color: red;">${errorString}</p>
 
    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>ID</th>
          <th>Product Name</th>
          <th>Product Quantity</th>
          <th>Price</th>
          <th>Checkout</th>
       </tr>
       <c:forEach items="${productList}" var="product" >
          <tr>
             <td>${product.id}</td>
             <td>${product.name}</td>
             <td>${product.quantity}</td>
             <td>${product.price}</td>
             <td>
                <a href="checkoutProduct?id=${product.id}">Checkout</a>
             </td>
          </tr>
       </c:forEach>
    </table>
    <br>
    <a href="logout">Logout</a>
 </body>
</html>