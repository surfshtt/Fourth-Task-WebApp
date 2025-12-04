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
            padding: 14px 28px;
            font-size: 20px;
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

        select, input[type="text"], input[type="email"], input[type="number"] {
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
    <div>МИНСКИЙ УНИВЕРСИТЕТ ИННОВАЙЗ</div>

    <form action="<%=request.getContextPath()%>/login" method="get">
        <input type="submit" value="Войти">
    </form>
</div>

<div class="container">

    <h1>Добро пожаловать!</h1>

    <h3>Выберите специальность для подачи документов</h3>

    <form action="apply.jsp" method="post">
        <select name="specialty">
            <option value="informatika">Информатика</option>
            <option value="ekonomika">Экономика</option>
            <option value="yurisprudenciya">Юриспруденция</option>
            <option value="stroitelstvo">Строительство</option>
        </select>

        <br>
        <input type="submit" value="Подать документы">
    </form>

    <hr>

    <!-- Анкета -->
    <h3>Анкета абитуриента</h3>

    <form action="saveForm.jsp" method="post">
        <input type="text" name="fio" placeholder="ФИО" required><br><br>
        <input type="email" name="email" placeholder="Email" required><br><br>
        <input type="text" name="phone" placeholder="Телефон"><br><br>
        <input type="number" name="age" placeholder="Возраст"><br><br>

        <input type="submit" value="Сохранить анкету">
    </form>

</div>

</body>
</html>
