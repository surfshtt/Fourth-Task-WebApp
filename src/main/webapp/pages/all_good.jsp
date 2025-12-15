<%@ page import="by.innowise.task.servlet.Constant" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Главная страница — Подача документов</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f5f5f5;
            margin: 0;
            padding: 0;
        }

        .top-bar {
            background: #2c3e50;
            padding: 10px 40px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            color: white;
            font-size: 22px;
            font-weight: bold;
        }

        .top-bar form input[type="submit"] {
            background: #3498db;
            color: white;
            padding: 12px 28px;
            font-size: 18px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
        }

        .container {
            width: 70%;
            margin: 30px auto;
            background: white;
            padding: 25px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        h1, h3 {
            text-align: center;
        }

        form {
            margin-top: 15px;
            text-align: center;
        }

        select, input[type="text"], input[type="fio"], input[type="number"] {
            width: 60%;
            padding: 10px;
            margin-top: 8px;
            border-radius: 5px;
            border: 1px solid #bbb;
        }

        input[type="submit"] {
            background: #27ae60;
            color: white;
            padding: 10px 18px;
            border: none;
            margin-top: 15px;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background: #1e8449;
        }

        hr {
            margin: 40px 0;
        }
    </style>
</head>

<body>

<div class="top-bar">
    <div style="font-size: 25px">МИНСКИЙ УНИВЕРСИТЕТ ИННОВАЙЗ</div>

    <%
        HttpSession currentSession = request.getSession();
        boolean isLogged = Boolean.TRUE.equals(currentSession.getAttribute(Constant.IS_LOG_ATTRIBUTE));

        if(isLogged){
    %>
    <form action="<%=request.getContextPath() + Constant.ACCOUNT_PAGE_REDIRECT%>" method="get">
        <input type="submit" value="Аккаунт">
    </form>
    <%
    }
    else{
    %>
    <form action="<%=request.getContextPath() + Constant.LOGIN_PAGE_REDIRECT%>" method="get">
        <input type="submit" value="Войти">
    </form>
    <%
        }
    %>
</div>

<div cla ss="container">

    <br>
    <br>

    <h1>Успешно!</h1>

    <h3>Ваша анкета подана! Следите за ее состоянием в профиле. </h3>

    <br>
    <br>
    <br>

    <hr>
    <h2 style="text-align:center; margin-top:20px;">Наш университет и студенческая жизнь</h2>

    <div style="display: flex; justify-content: center; gap: 20px; flex-wrap: wrap; margin-top: 20px;">

        <img src="https://images.unsplash.com/photo-1503676260728-1c00da094a0b"
             alt="Университет"
             style="width: 45%; border-radius: 10px; box-shadow: 0 0 6px rgba(0,0,0,0.2);">

        <img src="https://images.unsplash.com/photo-1504384308090-c894fdcc538d"
             alt="Учеба"
             style="width: 45%; border-radius: 10px; box-shadow: 0 0 6px rgba(0,0,0,0.2);">
    </div>
</div>

</body>
</html>
