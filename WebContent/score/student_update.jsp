<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <form action="StudentUpdateExecute.action" method="post">
         <input type="hidden" name="no" value="${student.no}">
            <section class="me-4">
                <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">学生情報変更</h2>
                <div class="form-group mb-3">
                    <label for="student_entYear" class="form-label">入学年度</label>
                    <input type="number" class="form-control" id="student_entYear" name="ent_year" value="${student.entYear}" readonly>
                </div>
                <div class="form-group mb-3">
                    <label for="student_no" class="form-label">学生番号</label>
                    <input type="text" class="form-control" id="student_no" name="student_number" value="${student.no}" readonly>
                </div>
                <div class="form-group mb-3">
                    <label for="student_name" class="form-label">氏名</label>
                    <input type="text" class="form-control" id="student_name" name="name" value="${student.name}" placeholder="氏名を入力してください" required>
                </div>
                <div class="form-group mb-3">
                    <label for="student_classNum" class="form-label">クラス</label>
                    <select class="form-control" id="student_classNum" name="class_num">
                        <c:forEach var="classNum" items="${class_num_set}">
                            <option value="${classNum}" <c:if test="${classNum == student.classNum}">selected</c:if>>${classNum}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-check mb-3">
                    <input type="checkbox" class="form-check-input" id="student_isAttend" name="is_attend" <c:if test="${student.attend}">checked</c:if>>
                    <label class="form-check-label" for="student_isAttend">在学中</label>
                </div>
                <button type="submit" class="btn btn-primary">変更</button>
                <br><br>
                <a href="StudentList.action">戻る</a>
            </section>
        </form>
    </c:param>
</c:import>
