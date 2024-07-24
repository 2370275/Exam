<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<c:import url="base.jsp">
    <c:param name="content">
        <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報登録</h2>
        <form action="SubjectCreateExecute.action" method="post">
            <div class="form-group">
                <label for="subject_cd">科目コード</label>
                <input type="text" class="form-control" id="subject_cd" name="subject_cd" placeholder="科目コードを入力してください" value="${param.subject_cd}" required>
                <c:if test="${not empty errorSubjectCd}">
                    <div class="text-warning mt-1">${errorSubjectCd}</div>
                </c:if>
            </div>
            <div class="form-group">
                <label for="subject_name">科目名</label>
                <input type="text" class="form-control" id="subject_name" name="subject_name" placeholder="科目名を入力してください" value="${param.subject_name}" required>
                <c:if test="${not empty errorSubjectName}">
                    <div class="text-warning mt-1">${errorSubjectName}</div>
                </c:if>
            </div>
            <button type="submit" class="btn btn-primary">登録</button>
            <a href="SubjectList.action" class="btn btn-secondary">戻る</a>
        </form>
        <c:if test="${not empty error}">
            <div class="alert alert-danger mt-3">${error}</div>
        </c:if>
    </c:param>
</c:import>
