<%-- 
    Document   : orders
    Created on : 8 Dec 2021, 12:33:44
    Author     : Ally Cox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
// request set in controller
//    request.setAttribute("selectedPage","orders");
%>
<jsp:include page="header.jsp" />
<!-- Begin page content -->
<main role="main" class="container">
    <H1>Orders</H1>

    <p>${user.firstName} ${user.secondName}</p>

    <p>${reciept}</p>
</main>




<jsp:include page="footer.jsp" />
