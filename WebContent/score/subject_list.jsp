<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    /* Remove the top border of the table headers */
    .table thead th {
        border-top: none;
    }
    /* Align subject name to the left */
    .subject-name {
        text-align: left;
        padding-left: 20px; /* Adjust as needed */
    }
    /* Flex container for link alignment */
    .link-container {
        display: flex;
        justify-content: flex-end;
        gap: 80px; /* Adjust spacing between the links for greater separation */
    }
    /* Offset the '削除' link slightly from the right edge */
    .link-container a.delete {
        margin-right: 20px; /* Adjust this value for desired right offset */
    }
</style>
<c:import url="base.jsp">
    <c:param name="content">
<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目管理</h2>
<div class="my-2 text-end px-4">
    <a href="SubjectCreate.action">新規登録</a>
</div>
<table class="table table-hover">
    <thead>
        <tr>
            <th>科目コード</th>
            <th class="subject-name">科目名</th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="subject" items="${subjects}">
            <tr>
                <td>${subject.cd}</td>
                <td class="subject-name">${subject.name}</td>
                <td>
                    <div class="link-container">
                        <a class="change" href="SubjectUpdate.action?cd=${subject.cd}">変更</a>
                        <a class="delete" href="SubjectDelete.action?cd=${subject.cd}">削除</a>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</c:param>
</c:import>
