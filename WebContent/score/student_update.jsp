<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="base.jsp" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<h2 class="mt-4">学生情報変更</h2>
<form action="StudentUpdateExecute.action" method="post">
    <input type="hidden" name="no" value="${student.no}">
    <div class="form-group">
        <label for="student_entYear">入学年度</label>
        <input type="number" class="form-control" id="student_entYear" name="ent_year" value="${student.entYear}" required>
    </div>
    <div class="form-group">
        <label for="student_name">氏名</label>
        <input type="text" class="form-control" id="student_name" name="name" value="${student.name}" required>
    </div>
    <div class="form-group">
        <label for="student_classNum">クラス</label>
        <select class="form-control" id="student_classNum" name="class_num">
            <c:forEach var="classNum" items="${class_num_set}">
                <option value="${classNum}" <c:if test="${classNum == student.classNum}">selected</c:if>>${classNum}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-check">
        <input type="checkbox" class="form-check-input" id="student_isAttend" name="is_attend" <c:if test="${student.attend}">checked</c:if>>
        <label class="form-check-label" for="student_isAttend">在学中</label>
    </div>
    <button type="submit" class="btn btn-primary">変更</button>
    <a href="StudentList.action" style="margin-right: 60px;">戻る</a>
</form>
