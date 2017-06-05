<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Edit Product</title>
 </head>
 <body>
 
    <h3>Edit Product</h3>
 
    <p style="color: red;">${errorString}</p>
 
	<c:if test="${not empty product}">
       <form method="POST" action="doModifyProduct">
          <input type="hidden" name="id" value="${product.id}" />
          <table border="0">
             <tr>
                <td>Id</td>
                <td style="color:red;">${product.id}</td>
             </tr>
             <tr>
                <td>Name</td>
                <td><input type="text" name="name" value="${product.name}" /></td>
             </tr>
             <tr>
                <td>Quantity</td>
                <td><input type="text" name="quantity" value="${product.quantity}" /></td>
             </tr>
             <tr>
                <td>Price</td>
                <td><input type="text" name="price" value="${product.price}" /></td>
             </tr>
             <tr>
                <td colspan = "2">
                    <input type="submit" value="Submit" />
                    <a href="${pageContext.request.contextPath}/adminDashboard">Cancel</a>
                </td>
             </tr>
          </table>
       </form>
	</c:if>
 
 </body>
</html>