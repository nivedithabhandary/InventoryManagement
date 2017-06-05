<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Product List</title>
 </head>
 <body>
 
    <h3>Product List</h3>
 
    <p style="color: red;">${errorString}</p>
 
    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>ID</th>
          <th>Product Name</th>
          <th>Product Quantity</th>
          <th>Price</th>
          <th>Edit</th>
          <th>Delete</th>
          <th>Checkout</th>
       </tr>
       <c:forEach items="${productList}" var="product" >
          <tr>
             <td>${product.id}</td>
             <td>${product.product_name}</td>
             <td>${product.product_quantity}</td>
             <td>${product.price}</td>
             <td>
                <a href="editProduct?code=${product.id}">Edit</a>
             </td>
             <td>
                <a href="deleteProduct?code=${product.id}">Delete</a>
             </td>
          </tr>
       </c:forEach>
    </table>
 
    <a href="createProduct" >Create Product</a>
 
 </body>
</html>