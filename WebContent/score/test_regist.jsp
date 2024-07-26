<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
/* セレクトボックスのデザイン */
.custom-select {
    appearance: none;
    -webkit-appearance: none;
    -moz-appearance: none;
    background: url('data:image/svg+xml;charset=US-ASCII,<svg xmlns="http://www.w3.org/2000/svg" width="292.362" height="292.362" viewBox="0 0 292.362 292.362"><path fill="%23333" d="M287.309 69.245L166.02 190.534c-7.06 7.06-18.518 7.06-25.578 0L5.053 69.245c-7.06-7.06-7.06-18.518 0-25.578s18.518-7.06 25.578 0L153.731 139.92 261.731 43.667c7.06-7.06 18.518-7.06 25.578 0s7.06 18.518 0 25.578z"/></svg>') no-repeat right 0.75rem center/8px 10px;
    padding-right: 1.5rem;
}

/* ボックスのデザイン */
.custom-box {
    border: 1px solid #ced4da;
    border-radius: 0.25rem;
    padding: 1rem;
    display: flex;
    align-items: center;
    justify-content: space-between;
    gap: 1rem;
}

.custom-box .form-group {
    flex: 1;
}

.custom-box .form-group:last-child {
    flex: none;
    margin-left: auto;
}

.table th, .table td {
    vertical-align: middle;
}

@media (max-width: 768px) {
    .custom-box {
        flex-direction: column;
        align-items: flex-start;
    }

    .custom-box .form-group {
        margin-right: 0;
        margin-bottom: 1rem;
    }

    .custom-box .form-group:last-child {
        margin-bottom: 0;
    }

    .custom-box button {
        width: 100%;
    }
}
</style>

<c:import url="base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>

            <!-- エラーメッセージ表示部分の追加 -->
            <c:if test="${not empty message}">
                <div class="alert alert-danger" role="alert">
                    ${message}
                </div>
            </c:if>

        </section>
        <form method="post" action="TestRegistExecute.action">
            <div class="custom-box">
                <div class="form-group">
                    <label for="entry_year">入学年度</label>
                    <select name="f1" id="entry_year" class="form-control custom-select">
                        <option value="0">----------</option>
                        <c:forEach var="entYear" items="${ent_year_set}">
                            <option value="${entYear}" <c:if test="${entYear == param.f1 || entYear == selectedYear}">selected</c:if>>${entYear}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="class_num">クラス</label>
                    <select name="f2" id="class_num" class="form-control custom-select">
                        <option value="0">----------</option>
                        <c:forEach var="classNum" items="${class_num_set}">
                            <option value="${classNum}" <c:if test="${classNum == param.f2 || classNum == selectedClass}">selected</c:if>>${classNum}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="subject">科目</label>
                    <select name="f3" id="subject" class="form-control custom-select">
                        <option value="0">----------</option>
                        <c:forEach var="subject" items="${subjects}">
                            <option value="${subject.cd}" <c:if test="${subject.cd == param.f3 || subject.cd == selectedSubject.cd}">selected</c:if>>${subject.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="num">回数</label>
                    <select name="f4" id="num" class="form-control custom-select">
                        <option value="0">----------</option>
                        <c:forEach var="num" items="${nums}">
                            <option value="${num}" <c:if test="${num == param.f4 || num == selectedNum}">selected</c:if>>${num}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-secondary">検索</button>
                </div>
            </div>
        </form>

        <c:if test="${not empty students}">
            <form method="post" action="TestRegistDone.action">
                <input type="hidden" name="selectedSubject" value="${selectedSubject.cd}">
                <input type="hidden" name="selectedNum" value="${selectedNum}">
                <input type="hidden" name="selectedClass" value="${selectedClass}">
                <input type="hidden" name="selectedYear" value="${selectedYear}">
                <p>科目：${selectedSubject.name}（${selectedNum}回）</p>
                <table class="table table-bordered">
                    <thead class="thead-light">
                        <tr>
                            <th>入学年度</th>
                            <th>クラス</th>
                            <th>学生番号</th>
                            <th>氏名</th>
                            <th>点数</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="student" items="${students}">
                            <tr>
                                <td>${student.getStudent().getEntYear()}</td>
                                <td>${student.getStudent().getClassNum()}</td>
                                <td>${student.getStudent().getNo()}</td>
                                <td>${student.getStudent().getName()}</td>
                                <td><input type="number" name="point_${student.getStudent().getNo()}" value="${student.getPoint()}" class="form-control" min="0" max="100"></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <button type="submit" class="btn btn-secondary">登録して終了</button>
            </form>
        </c:if>
    </c:param>
</c:import>
