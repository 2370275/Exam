<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="base.jsp">
    <c:param name="content">
		<h2 id="subject_create_h2">科目情報登録</h2>
		<form method="get">
			<div class="insert">
				<div id="insert_no">
					<label class="form-lavel">科目コード</label><br>
					<input type="text" size = "30" name="no" placeholder="科目コードを入力して下さい。">
				</div>
				<div id="insert_name">
					<label class="form-lavel">科目名</label><br>
					<input type="text" size = "30" name="name" placeholder="科目名を入力して下さい">
				</div>
			</div>
			<br>
			<div id="insert_button">
    			<input type="submit" onclick="location.href='	subject_create_done.jsp';" value="登録">
			</div>
			<a href="StudentList.action">戻る</a>
		</form>
    </c:param>
</c:import>

