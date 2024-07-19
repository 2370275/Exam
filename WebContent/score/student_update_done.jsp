<%-- 学生更新完了JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/score/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報変更</h2>
        </section>
<div class="me-4" style="display: block; text-align: center; background-color: green;">
    <p class="h3 mb-1 fw-normal text-white py-1 px-4" style="margin: 0; font-size: 0.8em;">変更が完了しました</p>
</div>

 <div style="margin-top: 7em;"></div>
<a href="StudentList.action" style="margin-right: 60px;">学生一覧</a>
</c:param>
</c:import>