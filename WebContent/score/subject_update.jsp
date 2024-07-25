<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<c:import url="base.jsp">
    <c:param name="content">
        <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報変更</h2>
        <form action="SubjectUpdateExecute.action" method="post">
            <input type="hidden" name="cd" value="${param.cd}">
            <div class="form-group">
                <label for="subject_cd">科目コード</label>
                <p id="subject_cd" class="form-control-plaintext ml-3">${param.cd}</p>
            </div>
            <c:if test="${not empty errorSubjectNotFound}">
                <div class="text-warning mt-1">${errorSubjectNotFound}</div>
            </c:if>
            <div class="form-group">
                <label for="subject_name">科目名</label>
                <input type="text" class="form-control" id="subject_name" name="name" value="${subject.name}" placeholder="科目名を入力してください" required>
            </div>
            <button type="submit" class="btn btn-primary">変更</button>
            <br><br>
            <a href="SubjectList.action" style="margin-right: 60px;">戻る</a>
        </form>
        <c:if test="${not empty error}">
            <div class="alert alert-danger mt-3">${error}</div>
        </c:if>
    </c:param>
</c:import>
