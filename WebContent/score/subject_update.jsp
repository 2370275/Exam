<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<c:import url="base.jsp">
    <c:param name="content">
        <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報変更</h2>
        <c:choose>
            <c:when test="${not empty subject}">
                <form action="SubjectUpdateExecute.action" method="post">
                    <input type="hidden" name="cd" value="${subject.cd}">
                    <div class="form-group">
                        <label for="subject_cd">科目コード</label>
                        <p id="subject_cd" class="form-control-plaintext ml-3">${subject.cd}</p>
                    </div>

                    <div class="form-group">
                        <label for="subject_name">科目名</label>
                        <input type="text" class="form-control" id="subject_name" name="name" value="${subject.name}" placeholder="科目名を入力してください" required>
                    </div>
                    <button type="submit" class="btn btn-primary">変更</button>
                    <br><br>
                    <a href="SubjectList.action" style="margin-right: 60px;">戻る</a>
                </form>
            </c:when>
            <c:otherwise>
                <div class="alert alert-danger mt-3">科目が存在していません</div>
                <a href="SubjectList.action" style="margin-right: 60px;">戻る</a>
            </c:otherwise>
        </c:choose>
        <c:if test="${not empty error}">
            <div class="alert alert-danger mt-3">${error}</div>
        </c:if>
    </c:param>
</c:import>
