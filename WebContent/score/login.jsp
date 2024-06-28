<%@page contentType="text/html; charset=UTF-8" %>
<link rel="stylesheet" href="../static/style.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<%@include file="base.jsp" %>

<div class ="main">
<%@include file="side.jsp" %>
<div class ="main_content">

		<h2 id="login_h2">ログイン</h2>
		<form action="LoginExecute.action" method="post">
		<p><input type="text" name="id" size="20" placeholder="半角でご入力ください" style="ime-mode: disabled"></p>
		<p><input type="password" name="password" size="20" placeholder="20文字以内の半角英数字でご入力ください" style="ime-mode: disabled"></p>
		<input type="checkbox" id="chk_d_ps">
		<label for="chk_d_ps">パスワードを表示</label>
		<p><input type="submit" name="login" value="ログイン"></p>
		</form>
</div>
</div>

<%@include file="footer.jsp" %>