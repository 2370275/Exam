<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <div class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
            <h1 class="me-auto">得点管理システム</h1>
            <div class="ms-auto">
                <c:if test="${not empty sessionScope.teacher}">
                    <span>${sessionScope.teacher.name}様</span>
                    <a href="Logout.action">ログアウト</a>
                </c:if>
            </div>
        </div>
    </div>
</body>
</html>
