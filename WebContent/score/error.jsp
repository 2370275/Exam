<%@ page contentType="text/html; charset=UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="../static/style.css">
<c:import url="base.jsp">
   <c:param name="title">エラページ</c:param>
    <c:param name="content">
        <p>エラーが発生しました。</p>
        <p>${errror}</p>
    </c:param>
</c:import>
