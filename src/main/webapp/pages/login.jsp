<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вход в систему</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #ecf0f1;
            margin: 0;
            padding: 0;
        }

        .login-container {
            width: 400px;
            margin: 80px auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0,0,0,0.2);
        }

        h2 {
            text-align: center;
            color: #2c3e50;
        }

        form {
            display: flex;
            flex-direction: column;
            margin-top: 20px;
        }

        input[type="email"],
        input[type="password"],
        select {
            padding: 12px;
            margin: 10px 0;
            border-radius: 6px;
            border: 1px solid #bdc3c7;
            font-size: 14px;
        }

        input[type="submit"] {
            background: #27ae60;
            color: white;
            border: none;
            padding: 12px;
            border-radius: 6px;
            font-size: 16px;
            cursor: pointer;
            margin-top: 15px;
        }

        input[type="submit"]:hover {
            background: #1e8449;
        }

        .footer-text {
            text-align: center;
            margin-top: 15px;
            font-size: 13px;
            color: #7f8c8d;
        }
    </style>
</head>
<body>

<div class="login-container">
    <h2>Вход в систему</h2>

    <form action="<%=request.getContextPath()%>/login" method="post">
        <input type="email" name="email" placeholder="Email" required>
        <input type="password" name="password" placeholder="Пароль" required>
        <select name="role">
            <option value="APPLICANT">Абитуриент</option>
            <option value="ADMIN">Администратор</option>
        </select>

        <input type="submit" value="Войти">
    </form>

    <div class="footer-text">
        Нет аккаунта? Обратитесь в приёмную комиссию.
    </div>
</div>

</body>
</html>
