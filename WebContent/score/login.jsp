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
            margin-bottom: 1px; /* Optional: Adds space below the title */
            color: #000; /* Ensure the title text is black */
        }
        .card {
            max-width: 500px; /* Adjust the maximum width to make it larger */
            margin: 0 auto; /* Center align the card */
            margin-top: 20px; /* Optional: Adds space above the card */
        }
        .input-container {
            margin-bottom: 1rem;
        }
        .input-label {
            display: block;
            font-size: 0.875rem; /* Font size for the label */
            color: #6c757d; /* Label color */
            margin-bottom: 0.5rem; /* Space below the label */
        }
        .form-control {
            width: 100%;
            padding: 0.5rem; /* Padding for the input */
            border: 1px solid #ced4da; /* Border style for the input */
            border-radius: 0.25rem; /* Border radius for rounded corners */
            font-size: 1rem; /* Font size for the input text */
        }
        .error-message {
            color: black; /* Error message color set to black */
            margin-bottom: 1rem; /* Space below the error message */
            text-align: center; /* Center align the error message text */
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

        function restrictInputToAlphanumeric(event) {
            var input = event.target.value;
            event.target.value = input.replace(/[^A-Za-z0-9]/g, '');
        }

        document.addEventListener('DOMContentLoaded', function() {
            document.getElementById('id').addEventListener('input', restrictInputToAlphanumeric);
            document.getElementById('password').addEventListener('input', restrictInputToAlphanumeric);
            document.getElementById('textPassword').addEventListener('input', restrictInputToAlphanumeric);
        });
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
                    <c:if test='${not empty sessionScope.errorMessage}'>
                        <div class='error-message'>${sessionScope.errorMessage}</div>
                        <c:remove var='errorMessage' scope='session'/>
                    </c:if>
                    <form action='LoginExecute.action' method='post'>
                        <div class='input-container'>
                            <label for='id' class='input-label'>ID</label>
                            <input type='text' class='form-control' id='id' name='id' value='${param.id}' maxlength='20' pattern='[A-Za-z0-9]*' placeholder='半角でご入力ください' required>
                        </div>
                        <div class='input-container'>
                            <label for='password' class='input-label'>パスワード</label>
                            <input type='password' class='form-control' id='password' name='password' maxlength='20' pattern='[A-Za-z0-9]*' placeholder='20文字以内の半角英数字でご入力ください' required>
                            <input type='text' class='form-control' id='textPassword' name='textPassword' style='display: none;'>
                        </div>
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
