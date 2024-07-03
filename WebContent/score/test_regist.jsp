
<%-- 成績管理JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*" %>

<c:import url="base.jsp">
    <c:param name="title" value="得点管理システム" />
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>

            <!-- 検索フォーム -->
            <form method="get" action="TestRegist.action">
                <div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
                    <!-- 入学年度選択 -->
                    <div class="col-3">
                        <label class="form-label" for="student-f1-select">入学年度</label>
                        <select class="form-select" id="student-f1-select" name="f1">
                            <option value="0">-------</option>
                            <c:forEach var="year" items="${ent_year_set}">
                                <option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- クラス選択 -->
                    <div class="col-3">
                        <label class="form-label" for="student-f2-select">クラス</label>
                        <select class="form-select" id="student-f2-select" name="f2">
                            <option value="0">-------</option>
                            <c:forEach var="num" items="${class_num_set}">
                                <option value="${num}" <c:if test="${num == f2}">selected</c:if>>${num}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- 科目選択 -->
                    <div class="col-3">
                        <label class="form-label" for="student-f3-select">科目</label>
                        <select class="form-select" id="student-f3-select" name="f3">
                            <option value="0">-------</option>
                            <c:forEach var="subject" items="${subject_cd_set}">
                                <option value="${subject}" <c:if test="${subject == f3}">selected</c:if>>${subject}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- 回数選択 -->
                    <div class="col-2">
                        <label class="form-label" for="student-f4-select">回数</label>
                        <select class="form-select" id="student-f4-select" name="f4">
                            <option value="0">-------</option>
                            <c:forEach var="num" items="${subject_num_set}">
                                <option value="${num}" <c:if test="${num == f4}">selected</c:if>>${num}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- 検索ボタン -->
                    <div class="col-1 text-center">
                        <button class="btn btn-secondary" id="filter-button">検索</button>
                    </div>
                </div>
            </form>

            <!-- 成績一覧テーブル -->
            <c:choose>
                <c:when test="${not empty students}">
                    <div class="my-2">科目: ${subject_name}（${f4}回）</div>
                    <form method="post" action="TestRegistDone.action">
                        <table class="table table-hover">
                            <thead>
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
                                        <td>${student.entYear}</td>
                                        <td>${student.classNum}</td>
                                        <td>${student.no}</td>
                                        <td>${student.name}</td>
                                        <td>
                                            <input type="number" class="form-control" name="point_${student.no}" value="${student.point}" min="0" max="100" required>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <div class="text-end">
                            <button type="submit" class="btn btn-primary">登録して終了</button>
                        </div>
                    </form>
                </c:when>
                <c:otherwise>
                    <div class="text-warning">成績情報が存在しませんでした</div>
                </c:otherwise>
            </c:choose>
        </section>
    </c:param>
</c:import>
