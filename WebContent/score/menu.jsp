<%@page contentType="text/html; charset=UTF-8" %>
<link rel="stylesheet" href="../static/style.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<%@include file="base.jsp" %>
<div class ="main">
	<%@include file="side.jsp" %>
<div class ="main_content">

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
		<a href="subject_list.jsp">科目管理</a>
	</div>
</div>
</div>

<%@include file="footer.jsp" %>