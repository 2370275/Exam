<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:import url="base.jsp">
        <c:param name="content">
            <div class="container">
                <h2>削除完了</h2>
                <div class="message">
                    ${message}
                </div>
                <div class="buttons mt-3">
                    <a href="subject_list.jsp" class="btn btn-primary">科目リストに戻る</a>
                </div>
            </div>
        </c:param>
    </c:import>
</body>
</html>
