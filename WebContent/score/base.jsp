<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>得点管理システム</title>
    <link rel="stylesheet" href="../static/style.css">
</head>
<body>
    <h1 id="base_h1">得点管理システム</h1>
    <c:choose>
        <c:when test="${empty sessionScope.isLoggedIn}">
            <!-- ログインしていない場合の処理 -->
        </c:when>
        <c:otherwise>
            <!-- ログインしている場合の処理 -->
            <p><a href="Logout.action">ログアウト</a></p>
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
        </c:otherwise>
    </c:choose>
    <div id="base_main">
        ${param.content}
    </div>
    <footer>
        <small>&copy; 2023 TIC</small><br>
        <small>大原学園</small>
    </footer>
</body>
</html>
