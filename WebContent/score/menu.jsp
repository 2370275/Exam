<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="../static/style.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<c:import url="base.jsp">
    <c:param name="content">
        <!-- メニューのタイトル -->
        <h2 id="menu_h2">メニュー</h2>
        <div class="menu-container">
            <!-- 学生管理のセクション -->
            <div class="menu-item student-management">
                <a href="StudentList.action">学生管理</a>
            </div>
            <!-- 成績管理のセクション -->
            <div class="menu-item score-management">
                <div>成績管理</div>
                <a href="TestRegist.action">成績登録</a><br>
                <a href="TestList.action">成績参照</a>
            </div>
            <!-- 科目管理のセクション -->
            <div class="menu-item subject-management">
                <a href="SubjectList.action">科目管理</a>
            </div>
        </div>
    </c:param>
</c:import>
