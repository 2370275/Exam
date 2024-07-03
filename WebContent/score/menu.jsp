<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="../static/style.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<c:import url="base.jsp">
    <c:param name="content">
		<h2 id="menu_h2">メニュー</h2>
		<div id="student_menu">
			<a href="StudentList.action">学生管理</a>
		</div>
		<div id="test_menu">
			学生管理<br>
			<a href="test_regist.jsp">成績登録</a><br>
			<a href="test_list.jsp">成績参照</a><br>
		</div>
		<div id="subject_menu">
			<a href="SubjectList.action">科目管理</a>
		</div>
	</c:param>
</c:import>