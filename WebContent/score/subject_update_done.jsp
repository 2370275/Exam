<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<c:import url="base.jsp">
    <c:param name="content">
<h2 class="mt-4">科目情報更新完了</h2>
<div class="alert alert-success mt-3">更新が完了しました</div>
<a href="SubjectList.action" class="btn btn-primary mt-3">科目一覧に戻る</a>
</c:param>
</c:import>