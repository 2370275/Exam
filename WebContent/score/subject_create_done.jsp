<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.List, bean.Subject" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>a</title>
    <link rel="stylesheet" href="style.css">
</head>
<html>
<body>
    <div class="container">
        <%@ include file="menu.jsp" %>
        <div class="main">
            <table border="1">
                <tbody>
                    <c:forEach var="subject" items="${subjectList}">
                        <li>${subject.name} (Code: ${subject.cd}, School Code: ${subject.school_cd})</li>
                    </c:forEach>
                </tbody>
            </table>
            <a href="base.jsp">Topページへ</a>
        </div>
    </div>
</body>
</html>
