<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
	<link rel="stylesheet" href="../static/style.css">
	<nav id="score_navi">
		<ul>
        	<li><a href="menu.jsp">メニュー</a></li>
            <li><a href="StudentList.action">学生管理</a></li>
            <li>成績管理
       			<ul>
        			<li><a href="test_regist.jsp">成績登録</a></li>
        			<li><a href="test_list.jsp">成績参照</a></li>
        		</ul>
        	</li>
        	<li><a href="subject_list.jsp">科目管理</a></li>
        </ul>
	</nav>
</html>