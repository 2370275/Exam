<%@ page contentType="text/html; charset=UTF-8" %>
<link rel="stylesheet" href="../static/style.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
<meta charset="UTF-8">
<title>得点管理システム</title>
<style>
        header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px;
            background-color: #f8f9fa;
        }
        header h1 {
            margin: 0;
        }
        header .user-info {
            display: flex;
            align-items: center;
        }
        header .user-info span {
            margin-right: 10px;
        }
</style>
</head>
<body>
<header>
<h1>得点管理システム</h1>
<div class="user-info">
<c:if test="${not empty sessionScope.teacher}">
<span>${sessionScope.teacher.name} 様</span> <!-- Teacher オブジェクトの name プロパティを表示 -->
<a href="Logout.action">ログアウト</a>
</c:if>
</div>
</header>
<div id="main">
<c:choose>
<c:when test="${empty sessionScope.isLoggedIn}">
<!-- ログインしていない場合の処理 -->
<p>ログインしていません。</p>
</c:when>
<c:otherwise>
<!-- ログインしている場合の処理 -->
<nav id="score_navi">
<ul>
<li><a href="Menu.action">メニュー</a></li>
<li><a href="StudentList.action">学生管理</a></li>
<li>成績管理
<ul>
<li><a href="TestRegist.action">成績登録</a></li>
<li><a href="TestList.action">成績参照</a></li>
</ul>
</li>
<li><a href="SubjectList.action">科目管理</a></li>
</ul>
</nav>
</c:otherwise>
</c:choose>
<div id="base_main">
            ${param.content}
</div>
</div>
<footer>
<small>&copy; 2023 TIC</small><br>
<small>大原学園</small>
</footer>
</body>