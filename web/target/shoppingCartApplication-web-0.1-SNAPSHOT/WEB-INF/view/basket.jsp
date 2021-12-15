<%-- 
    Document   : basket
    Created on : 8 Dec 2021, 09:40:41
    Author     : Ally Cox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
// request set in controller
//    request.setAttribute("selectedPage","basket");
%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<!-- Begin page content -->
<main role="main" class="container">
    <H1>Shopping Basket</H1>
    <table class="table">

        <tr>
            <th>Item Name</th>
            <th>Price</th>
            <th>Quantity</th>
        </tr>

        <c:forEach var="item" items="${shoppingCartItems}">

            <tr>
                <td>${item.name}</td>
                <td>£${item.price}</td>
                <td>${item.quantity}</td>
                <td>
                    <!-- post avoids url encoded parameters -->
                    <form action="./home" method="post">
                        <input type="hidden" name="itemUUID" value="${item.uuid}">
                        <input type="hidden" name="itemName" value="${item.name}">
                        <input type="hidden" name="action" value="removeItemFromCart">
                        <button type="submit" >Remove Item</button>
                    </form> 
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td>TOTAL</td>
            <td>£${shoppingcartTotal}</td>
        </tr>
        </td>

        <td>
            <H1>Card Details</H1>
            <form action="./basket" method="post">
                <input type="hidden" name="action" value="payment">
                <p>Name <input type="text" name="name" ></input></p>
                <p>End Date <input type="text" name="endDate" ></input></p>
                <p>Card Number <input type="text" name="cardnumber" ></input></p>
                <p>CVV <input type="text" name="cvv" ></input></p>
                <p>Issue Number <input type="text" name="issueNumber" ></input></p>
                <p>Total £${shoppingcartTotal} </p>
                <p><button type="submit" >Purchase</button></p>
            </form>
            
            

        </td>
    </table>

</main>

<jsp:include page="footer.jsp" />
