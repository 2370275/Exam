<%-- 学生登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*" %>


<script>
    function redirectToPage() {
        // window.location.href = "student_create_done.jsp";

        val form = document.getElementById('student_create');
        form.submit();
    }
</script>
<fmt:setBundle basename="messages" var="msg"/>
<c:set var="title" value="学生情報登録" />
<c:set var="content">
    <section class="me-4">
        <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報登録</h2>

        <form id="student_create" action="/Score/StudentCreateExecute" method="post">
            <div class="row mb-3">
                <div class="col-12">
                    <label class="form-label" for="student-ent-year-select">入学年度</label>
                    <select class="form-select" id="student-ent-year-select" name="ent_year">
                        <option value="0">-------</option>
                        <c:forEach var="year" items="${ent_year_set}">
                            <option value="${year}" <c:if test="${year == param.ent_year}">selected</c:if>>${year}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <label class="form-label" for="student-number-input">学生番号</label>
                    <input type="text" class="form-control" id="student-number-input" name="student-number" value="${no}" maxlength="10" required placeholder="学生番号を入力してください">
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <label class="form-label" for="student-name-input">氏名</label>
                    <input type="text" class="form-control" id="student-name-input" name="student-name" value="${name}" maxlength="30" required placeholder="氏名を入力してください">
                </div>
            </div>
            <div class="row mb-3">
                <div class="col-12">
                    <label class="form-label" for="class-num-select">クラス</label>
                    <select class="form-control" id="class-num-select" name="class_num">
                        <option value="0">101</option>
                        <c:forEach var="classItem" items="${class_num_set}">
                            <option value="${classItem}" <c:if test="${classItem eq param.class_num}">selected</c:if>>${classItem}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>


<div id="insert_button">
    			<input type="submit" onclick="location.href='student_create_done.jsp';" value="登録して終了">
			</div>


</form>

        <div class="my-2 text-start px-2">
            <a href="student_list.jsp">戻る</a>
        </div>
    </section>
</c:set>
<c:import url="base.jsp">
    <c:param name="title" value="${title}" />
    <c:param name="content" value="${content}" />
</c:import>
