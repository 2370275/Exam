<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:import url="base.jsp">
        <c:param name="content">
            <div class="container">
                <h2>科目情報削除</h2>
                <p>「${subject.name} (コード: ${subject.cd})」を削除してもよろしいですか？</p>
                <form action="SubjectDeleteExecute.action" method="post">
                    <input type="hidden" name="cd" value="${subject.cd}" />
                    <a href="subject_delete_done.jsp" class="btn btn-danger">削除</button>
                </form>
                <a href="subject_list.jsp" class="btn btn-secondary mt-3">戻る</a>
            </div>
        </c:param>
    </c:import>
</body>
</html>
