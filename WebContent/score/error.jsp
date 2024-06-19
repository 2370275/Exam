<%@ page contentType="text/html; charset=UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="../static/style.css">
<c:import url="base.jsp">
    <c:param name="content">
        <p>学生情報が存在しませんでした。</p>
        <p>${errror}</p>
    </c:param>
</c:import>
