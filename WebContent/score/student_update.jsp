<%-- 学生変更JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <c:import url="/common/base.jsp">
      <c:param name="title">
          得点管理システム
      </c:param>

      <c:param name="scripts"></c:param>

      <c:param name="content">
           <section class="me-4">
    <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報登録</h2>
    <label for="student-ent-year-select" class="form-label">入学年度</label>
    <input type="text" class="form-control" id="student-ent-year-select" value="${ent_year}" readonly>
</section>
        <div class="form-group mb-3">
            <label class="form-label">学生番号</label>
            <input type="text" class="form-control" value="${no}" readonly>
        </div>
 <div class="form-group mb-3">
            <label for="name" class="form-label">氏名</label>
            <input type="text" id="name" name="name" class="form-control" value="${name}" readonly>
        </div>
        <div class="form-group mb-3">
            <label for="class_num" class="form-label">クラス</label>
            <select id="class_num" name="class_num" class="form-control">
                <option value="1" ${class_num == '101' ? 'selected' : ''}>101</option>
                <option value="2" ${class_num == '201' ? 'selected' : ''}>201</option>
                <option value="3" ${class_num == '202' ? 'selected' : ''}>202</option>
                <!-- クラス番号は必要に応じて追加 -->
            </select>
        </div>
        <div class="form-group mb-3">
            <label class="form-label">在学中</label>
            <input type="checkbox" class="form-check-input" name="si_attend" ${si_attend ? 'checked' : ''} readonly>
        </div>
        <div class="form-group mb-3">
            <button type="button" class="btn btn-primary" onclick="update()">変更</button>
        </div>
        <div class="links">
            <a href="student_list.jsp" class="btn btn-link">戻る</a>
        </div>



</c:param>
</c:import>