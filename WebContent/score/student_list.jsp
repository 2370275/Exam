<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<%@ include file="base.jsp" %>


<h2 class="mt-4">学生管理</h2>
<div class="mb-3">
    <a href="StudentCreate.action" class="btn btn-primary">新規登録</a>
</div>
<form method="get" class="mb-4">
    <div class="form-row">
        <div class="form-group col-md-3">
            <label for="student_list_year">入学年度</label>
            <select name="f1" id="student_list_year" class="form-control">
                <option value="0">----------</option>
                <c:forEach var="entYear" items="${ent_year_set}">
                    <option value="${entYear}" <c:if test="${entYear == param.f1}">selected</c:if>>${entYear}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group col-md-3">
            <label for="student_list_class">クラス</label>
            <select name="f2" id="student_list_class" class="form-control">
                <option value="0">----------</option>
                <c:forEach var="classNum" items="${class_num_set}">
                    <option value="${classNum}" <c:if test="${classNum == param.f2}">selected</c:if>>${classNum}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group col-md-3">
            <div class="form-check mt-4">
                <input type="checkbox" name="f3" class="form-check-input" id="student_list_attend" <c:if test="${not empty param.f3}">checked</c:if>>
                <label for="student_list_attend" class="form-check-label">在学中</label>
            </div>
        </div>
        <div class="form-group col-md-3 mt-4">
            <button type="submit" class="btn btn-primary">絞り込み</button>
        </div>
    </div>
</form>

<c:choose>
    <c:when test="${not empty students}">
        <div>検索結果：${students.size()}件</div>
        <table class="table table-hover">
            <thead>
                <tr>
                    <th>入学年度</th>
                    <th>学生番号</th>
                    <th>氏名</th>
                    <th>クラス</th>
                    <th class="text-center">在学中</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="student" items="${students}">
                    <tr>
                        <td>${student.entYear}</td>
                        <td>${student.no}</td>
                        <td>${student.name}</td>
                        <td>${student.classNum}</td>
                        <td class="text-center">
                            <c:choose>
                                <c:when test="${student.attend}">
                                    〇
                                </c:when>
                                <c:otherwise>
                                    ×
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td><a href="StudentUpdate.action?no=${student.no}" class="btn btn-sm btn-secondary">変更</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <div>学生情報が存在しませんでした。</div>
    </c:otherwise>
</c:choose>

