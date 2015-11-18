<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Your selections</title>
</head>
<body>
<a href="${pageContext.request.contextPath }/login.jsp">login</a>
<a href="${pageContext.request.contextPath }/logout">logout</a>
<%
    String selections = (String) session.getAttribute("selections");
    selections = selections == null ? "" : selections;
%>
<form action="${pageContext.request.contextPath }/html/submit.jsp" method="post">
    <p>What do you prefer?</p>

    <p><input type="checkbox" name="car" value="car" <%
        if (selections.indexOf("car") > -1) out.print("checked=\"checked\""); %> />Car</p>

    <p><input type="checkbox" name="bike" value="bike" <%
        if (selections.indexOf("bike") > -1) out.print("checked=\"checked\""); %> />Bike</p>

    <p><input type="checkbox" name="train" value="train" <%
        if (selections.indexOf("train") > -1) out.print("checked=\"checked\""); %> />Train</p>

    <p><input type="checkbox" name="plane" value="plane" <%
        if (selections.indexOf("plane") > -1) out.print("checked=\"checked\""); %> />Plane</p>
    <input type="hidden" name="<c:out value="${_csrf.parameterName}"/>" value="<c:out value="${_csrf.token}"/>"/>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>