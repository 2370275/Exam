<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="d-flex align-items-center mb-3 mb-md-0  me-md-auto text-dark text-decoration-none">
    <h1>得点管理システム</h1>
<div class="user-info">
<c:if test="${not empty sessionScope.teacher}">
	<span>${sessionScope.teacher.name}様</span>
	<a href="Logout.action">ログアウト</a>
</c:if>
</div>
</div>
