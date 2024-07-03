<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="base.jsp" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<h2 class="mt-4">科目新規登録</h2>
<form action="SubjectCreateExecute.action" method="post">
    <div class="form-group">
        <label for="subject_cd">科目コード</label>
        <input type="text" class="form-control" id="subject_cd" name="subject_cd" required>
    </div>
    <div class="form-group">
        <label for="subject_name">科目名</label>
        <input type="text" class="form-control" id="subject_name" name="subject_name" required>
    </div>
    <button type="submit" class="btn btn-primary">登録</button>
    <a href="SubjectList.action" class="btn btn-secondary">戻る</a>
</form>
<c:if test="${not empty error}">
    <div class="alert alert-danger mt-3">${error}</div>
</c:if>
