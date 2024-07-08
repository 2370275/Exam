<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<c:import url="base.jsp">
    <c:param name="content">
<h2 class="mt-4">科目管理</h2>
<a href="SubjectCreate.action" class="btn btn-primary mb-3">新規登録</a>

<table class="table table-bordered">
    <thead>
        <tr>
            <th>科目コード</th>
            <th>科目名</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="subject" items="${subjects}">
            <tr>
                <td>${subject.cd}</td>
                <td>${subject.name}</td>
                <td>
                    <a href="SubjectUpdate.action?cd=${subject.cd}" class="btn btn-warning">変更</a>
                    <a href="SubjectDelete.action?cd=${subject.cd}" class="btn btn-danger">削除</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</c:param>
</c:import>
