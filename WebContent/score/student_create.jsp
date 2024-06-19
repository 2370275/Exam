<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="base.jsp">
    <c:param name="content">
		<h2 id="student_create_h2">学生情報登録</h2>
		<form method="get">
			<div class="insert">
				<div class="insert_year">
					<label class="form-label">入学年度</label><br>
					<select name="ent_year" >
						<option value="0">----------</option>
					</select>
				</div>
				<div id="insert_no">
					<label class="form-lavel">学生番号</label><br>
					<input type="text" size = "10" name="no" placeholder="学生番号を入力して下さい。">
				</div>
				<div id="insert_name">
					<label class="form-lavel">氏名</label><br>
					<input type="text" size = "30" name="name" placeholder="氏名を入力して下さい。">
				</div>
				<div class="insert_class">
					<label class="form-label">クラス</label><br>
					<select name="class_num" >
						<option value="0">----------</option>
					</select>
				</div>
			</div>
			<br>
			<div id="insert_button">
    			<input type="submit" onclick="location.href='student_create_done.jsp';" value="登録して終了">
			</div>
			<a href="StudentList.action">戻る</a>
		</form>
    </c:param>
</c:import>
