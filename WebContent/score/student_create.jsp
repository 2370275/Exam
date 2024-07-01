<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<c:import url="base.jsp">
	<c:param name="content">
<h2 class="mt-4">学生新規登録</h2>
<form action="StudentCreateExecute.action" method="post">
    <div class="form-group">
        <label for="student_no">学生番号</label>
        <input type="text" class="form-control" id="student_no" name="student-number" required>
    </div>
    <div class="form-group">
        <label for="student_name">氏名</label>
        <input type="text" class="form-control" id="student_name" name="student-name" required>
    </div>
    <div class="form-group">
        <label for="student_entYear">入学年度</label>
        <select class="form-control" id="student_entYear" name="ent_year">
            <c:forEach var="entYear" items="${ent_year_set}">
                <option value="${entYear}">${entYear}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <label for="student_classNum">クラス</label>
        <select class="form-control" id="student_classNum" name="class_num">
            <c:forEach var="classNum" items="${class_num_set}">
                <option value="${classNum}">${classNum}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-check">
        <input type="checkbox" class="form-check-input" id="student_isAttend" name="isAttend">
        <label class="form-check-label" for="student_isAttend">在学中</label>
    </div>
    <button type="submit" class="btn btn-primary">登録</button>
</form>
<c:if test="${not empty error}">
    <div class="alert alert-danger mt-3">${error}</div>
</c:if>
</c:param>
</c:import>