<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*" %>

<c:import url="base.jsp">
    <c:param name="title" value="得点管理システム" />
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績一覧(科目)</h2>

           <!-- 検索フォーム -->
<form method="get" action="TestList.action">
    <div class="border mx-3 mb-3 py-2 rounded">
        <!-- 科目情報 -->
        <div class="row align-items-center mb-3">
            <div class="col-2" style="margin-left: 10px;">
                <p class="mb-0">科目情報</p>
            </div>

            <!-- 入学年度選択 -->
            <div class="col-2">
                <label class="form-label" for="student-f1-select">入学年度</label>
                <select class="form-select" id="student-f1-select" name="f1">
                    <option value="0">-------</option>
                    <c:forEach var="year" items="${ent_year_set}">
                        <option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
                    </c:forEach>
                </select>
            </div>

            <!-- クラス選択 -->
            <div class="col-2">
                <label class="form-label" for="student-f2-select">クラス</label>
                <select class="form-select" id="student-f2-select" name="f2">
                    <option value="0">-------</option>
                    <c:forEach var="num" items="${class_num_set}">
                        <option value="${num}" <c:if test="${num == f2}">selected</c:if>>${num}</option>
                    </c:forEach>
                </select>
            </div>

            <!-- 科目選択 -->
            <div class="col-2">
                <label class="form-label" for="student-f3-select">科目</label>
                <select class="form-select" id="student-f3-select" name="f3">
                    <option value="0">-------</option>
                    <c:forEach var="subject" items="${subject_cd_set}">
                        <option value="${subject}" <c:if test="${subject == f3}">selected</c:if>>${subject}</option>
                    </c:forEach>
                </select>
            </div>

            <!-- 検索ボタン -->
            <div class="col-2 text-center">
                <button class="btn btn-secondary" id="filter-button">検索</button>
            </div>
        </div>

        <!-- Separator Line -->
        <hr class="my-4">

        <!-- 学生情報 -->
        <div class="row align-items-center mb-3">
            <div class="col-2" style="margin-left: 10px;">
                <p class="mb-0">学生情報</p>
            </div>
            <div class="col-4">
                <label class="form-label" for="student-number-input">学生番号</label>
                <input type="text" class="form-control" id="student-number-input" name="f4" maxlength="10" placeholder="学生番号を入力してください" value="${f4}" required>
            </div>
            <div class="col-2 text-center">
                <button class="btn btn-secondary" id="filter-button-student">検索</button>
            </div>
        </div>
    </div>
</form>

            <!-- 成績一覧(科目)テーブル -->
            <c:choose>
                <c:when test="${not empty students}">
                    <div class="my-2">科目: ${subject_name}</div>
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>入学年度</th>
                                <th>クラス</th>
                                <th>学生番号</th>
                                <th>氏名</th>
                                <th>1回</th>
                                <th>2回</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="student" items="${students}">
                                <tr>
                                    <td>${student.entYear}</td>
                                    <td>${student.classNum}</td>
                                    <td>${student.no}</td>
                                    <td>${student.name}</td>
                                    <td>${student.score1}</td>
                                    <td>${student.score2}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <div class="text-black">学生情報が存在しませんでした</div>
                </c:otherwise>
            </c:choose>
        </section>
    </c:param>
</c:import>
