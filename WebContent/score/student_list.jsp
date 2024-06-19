<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="base.jsp">
    <c:param name="content">
		<h2 id=student_list_h2>学生管理</h2>
		<div class=new_student>
			<a href="student_create.jsp">新規登録</a>
		</div>
		<form method="get">
			<div class="filter">
				<div class="year">
					<label class="form-label">入学年度</label><br>
					<select name="f1" id=student_list_year>
						<option value="0">----------</option>
							<%-- 年度の選択肢がでるようにする --%>
					</select>
				</div>
				<div class="class">
					<label class="f2">クラス</label><br>
					<select name="form-slect" id=student_list_class>
						<option value="0">----------</option>
						<%-- クラスの選択肢がでるようにする --%>
					</select>
				</div>
			</div>
			<div class="form_check">
				<label class="form-check-label">
					<input class="form_check_input" type="checkbox" name="f3">
					<%-- チェックだけできる --%>
					在学中
				</label>
			</div>
			<div class="text-center">
				<button class="fillter_button">絞り込み</button>
			</div>
		</form>

		<c:choose>
			<c:when test="${student.size()>0 }">
				<div>検索結果：${students.size() }件</div>
				<table class="table-hover">
					<tr>
						<th>入学年度</th>
						<th>学生番号</th>
						<th>氏名</th>
						<th>クラス</th>
						<th class="text/center">在学中</th>
					</tr>
					<c:forEach var="student" items="${students }">
						<tr>
							<td>${student.entYear }</td>
							<td>${student.no }</td>
							<td>${student.name }</td>
							<td>${student.classNum }</td>
							<td class="text-center">
								<c:choose>
									<c:when test="${student.isAttend() }">
										〇
									</c:when>
									<c:otherwise>
										×
									</c:otherwise>
								</c:choose>
							</td>
							<td><a href="student_update.jsp">変更</a></td>
						</tr>
					</c:forEach>
				</table>
			</c:when>
			<c:otherwise>
				<div>学生情報が存在しませんでした。</div>
			</c:otherwise>
		</c:choose>
    </c:param>
</c:import>
