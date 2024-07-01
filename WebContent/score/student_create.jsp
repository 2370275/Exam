<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="base.jsp" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<h2 class="mt-4">学生新規登録</h2>
<form action="StudentCreateExecute.action" method="post">
    <div class="form-group">
        <label for="student_entYear">入学年度</label>
        <select class="form-control" id="student_entYear" name="ent_year">
           <option value="0">----------</option>
            <c:forEach var="entYear" items="${ent_year_set}">
                <option value="${entYear}">${entYear}</option>
            </c:forEach>
        </select>
        <c:if test="${not empty sessionScope.errorEntYear}">
            <div class="text-danger">${sessionScope.errorEntYear}</div>
            <c:remove var="errorEntYear" scope="session"/>
        </c:if>
    </div>
    <div class="form-group">
        <label for="student_no">学生番号</label>
        <input type="text" class="form-control" id="student_no" name="student_number" placeholder="学生番号を入力してください" required>
        <c:if test="${not empty sessionScope.errorStudentNumber}">
            <div class="text-danger">${sessionScope.errorStudentNumber}</div>
            <c:remove var="errorStudentNumber" scope="session"/>
        </c:if>
    </div>
    <div class="form-group">
        <label for="student_name">氏名</label>
        <input type="text" class="form-control" id="student_name" name="student_name" placeholder="氏名を入力してください" required>
    </div>
    <div class="form-group">
        <label for="student_classNum">クラス</label>
        <select class="form-control" id="student_classNum" name="class_num">
            <c:forEach var="classNum" items="${class_num_set}">
                <option value="${classNum}">${classNum}</option>
            </c:forEach>
        </select>
    </div>
    <button type="submit" class="btn btn-primary">登録して終了</button>
    <br><br>
    <a href="StudentCreate.action" style="margin-right: 60px;">戻る</a>
</form>
<c:if test="${not empty sessionScope.error}">
    <div class="alert alert-danger mt-3">${sessionScope.error}</div>
    <c:remove var="error" scope="session"/>
</c:if>
