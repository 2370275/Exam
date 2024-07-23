<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="base.jsp">
    <c:param name="content">
<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報削除</h2>

<c:if test="${not empty error}">
    <p style="color: red;">${error}</p>
</c:if>

<c:if test="${not empty subject}">
    <p>「${subject.name}(${subject.cd})」を削除してもよろしいですか</p>
    <form action="SubjectDeleteExecute.action" method="post">
        <input type="hidden" name="cd" value="${subject.cd}">
        <button type="submit">削除</button>
    </form>
</c:if>

<a href="SubjectList.action">戻る</a>
</c:param>
</c:import>