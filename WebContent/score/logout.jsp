<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>ログアウト</title>
    <link rel="stylesheet" type="text/css" href="path/to/your/styles.css">
</head>
<body>
    <jsp:include page="base.jsp">
        <jsp:param name="title" value="ログアウト"/>
        <jsp:param name="scripts" value=""/>
        <jsp:param name="content" value="
            <div class='card'>
                <div class='card-header'>ログアウト</div>
                <div class='card-body'>
                    <p>ログアウトしました。</p>
                    <a href='login.jsp' class='btn btn-primary'>ログイン</a>
                </div>
            </div>
        "/>
    </jsp:include>
</body>
</html>
