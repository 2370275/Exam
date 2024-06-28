<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.List, bean.Subject" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>a</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <%@ include file="menu.jsp" %>
        <div class="main">
            <table border="1">
                <thead>
                    <tr>
                        <th>科目コード</th>
                        <th>科目名</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="subject" items="${subjectList}">
                        <tr>
                            <td>${subject.name}</td>
                            <td>(Code: ${subject.cd})</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <a href="base.jsp">Topページへ</a>
        </div>
    </div>
</body>
</html>
