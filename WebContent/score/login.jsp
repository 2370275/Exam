<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>ログイン</title>
    <link rel="stylesheet" type="text/css" href="path/to/your/styles.css">
    <style>
        .login-title {
            text-align: center;
            font-weight: bold;
            font-size: 24px; /* Adjust font size as needed */
            margin-bottom: 20px; /* Optional: Adds space below the title */
        }
        .card {
            max-width: 500px; /* Adjust the maximum width to make it larger */
            margin: 0 auto; /* Center align the card */
            margin-top: 20px; /* Optional: Adds space above the card */
        }
    </style>
    <script>
        function togglePasswordVisibility() {
            var passwordInput = document.getElementById('password');
            var textInput = document.getElementById('textPassword');
            var checkbox = document.getElementById('rememberMe');

            if (checkbox.checked) {
                passwordInput.style.display = 'none';
                textInput.style.display = 'block';
                textInput.value = passwordInput.value;
            } else {
                passwordInput.style.display = 'block';
                textInput.style.display = 'none';
                passwordInput.value = textInput.value;
            }
        }
    </script>
</head>
<body>
    <jsp:include page="base.jsp">
        <jsp:param name="title" value="ログイン"/>
        <jsp:param name="scripts" value=""/>
        <jsp:param name="content" value="
            <div class='card'>
                <div class='card-header'>
                    <div class='login-title'>ログイン</div>
                </div>
                <div class='card-body'>
                    <form action='LoginExecute.action' method='post'>
                        <div class='mb-3'>
                            <label for='id' class='form-label'>ID</label>
                            <input type='text' class='form-control' id='id' name='id' value='${param.id}' maxlength='20' pattern='[A-Za-z0-9]*' placeholder='半角でご入力ください' required>

                        </div>
                        <!-- Hidden input for password -->
                       <label for='password' class='form-label'>パスワード</label>
　　　　　　　　　　　 <input type='password' class='form-control' id='password' name='password' maxlength='20' pattern='[A-Za-z0-9]*' placeholder='20文字以内の半角英数字でご入力ください' required>

                        <!-- Visible input for text -->
                        <input type='text' class='form-control' id='textPassword' name='textPassword' style='display: none;'>

                        <div class='d-flex justify-content-center'>
                            <div class='mb-3 form-check'>
                                <input type='checkbox' class='form-check-input' id='rememberMe' name='rememberMe' onchange='togglePasswordVisibility()'>
                                <label class='form-check-label' for='rememberMe'>パスワードを表示</label>
                            </div>
                        </div>
                        <div class='text-center'>
                            <button type='submit' class='btn btn-primary'>ログイン</button>
                        </div>
                    </form>
                </div>
            </div>
        "/>
    </jsp:include>
</body>
</html>
