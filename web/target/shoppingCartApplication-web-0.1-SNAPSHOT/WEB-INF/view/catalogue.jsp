<%-- 
    Document   : content
    Created on : Jan 4, 2020, 11:19:47 AM
    Author     : cgallen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
// request set in controller
//    request.setAttribute("selectedPage","catalogue");
%>
<jsp:include page="header.jsp" />
<!-- Begin page content -->
<main role="main" class="container">

    <p> Total Amount of Items: ${availableItemsSize} </p><!-- comment -->
    <table class="table">

        <tr>
            <th>Item Name</th>
            <th>Price</th>
            <th>Quantity in Stock</th>
                <c:if test="${user.userRole =='ADMINISTRATOR'}">
                <th>Activate </th>
                <th>Deactivate </th>
                </c:if>  
            <th></th>
        </tr>

        <c:forEach var="item" items="${availableItems}">

            <tr>
                <td>${item.name}</td>
                <td>£${item.price}</td>
                <td>${item.stock}</td>
                <c:if test="${user.userRole =='ADMINISTRATOR'}">
                <form action="./catalogue" method="post">
                    <input type="hidden" name="itemUUID" value="${item.uuid}">
                    <input type="hidden" name="action" value="activate">
                    <td><button type="submit" >Activate </button></td>

                </form>
                <form action="./catalogue" method="post">
                    <input type="hidden" name="itemUUID" value="${item.uuid}">
                    <input type="hidden" name="action" value="deactivate">
                    <td><button type="submit" >Deactivate </button></td>

                </form>
                <c:if test="${ShoppingItem.active == true}">
                    <td> Item Active</td>
                </c:if>
                <c:if test="${ShoppingItem.active == false}">
                    <td> Item Deactivated </td>    
                </c:if>
                <td>

                </td>

                </tr>
            </c:if>
        </c:forEach>

        <c:if test="${user.userRole =='ADMINISTRATOR'}">
            <p> Add New Item </p>
            <form action="./catalogue" method="post">
                <input type="hidden" name="action" value="addNewItem">
                <p>Item Name <input type="text" name="name" ></input></p>
                <p>Price <input type="double" name="price" ></input></p>
                <p>Stock Level <input type="integer" name="stock" ></input></p>
                <p><button type="submit" >Add New Item</button></p>
            </form>
        </c:if>        

    </table>
</main>




<jsp:include page="footer.jsp" />
