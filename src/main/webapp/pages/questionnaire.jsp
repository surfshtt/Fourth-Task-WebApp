<%@ page import="by.innowise.task.servlet.ServletConstants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Анкета абитуриента</title>
</head>
<body>
    <div class="top-bar">
        <div style="font-size: 25px">МИНСКИЙ УНИВЕРСИТЕТ ИННОВАЙЗ</div>

        <form action="TODO" method="get">
            <input type="submit" value="Профиль">
        </form>
    </div>

    <div class="container">

        <h1>Приветствуем <%=request.getSession().getAttribute(ServletConstants.USER_NAME_ATTRIBUTE)%>!</h1>

        <h3>Анкета абитуриента для выбранной специальности:</h3>

        <form action="questionnaire" method="post">
            <input type="text" name="fio" placeholder="ФИО" required><br><br>
            <input type="number" name="diplomaScore" placeholder="Средний балл" required><br><br>
            <input type="text" name="description" placeholder="О себе" required><br><br>
            <input type="text" name="mobilePhone" placeholder="Телефон"><br><br>

            <input type="submit" value="Подать анкету анкету">
        </form>
    </div>
</body>

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

</html>
