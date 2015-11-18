
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Insert title here</title>
</head>
<body>
<div class="container">
  <h1>This is secured!</h1>
  <p>
    <%--Hello <b><c:out value="${pageContext.request.remoteUser}"/></b>--%>
  </p>
  <form class="form-inline" action="j_spring_security_check" method="post">
    <c:url var="logoutUrl" value="/logout"/>
    <input type="text" name="j_username"/>
    <input type="password" name="j_password"/>
    <input type="submit">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
  </form>
</div>
</body>
