<%@ page import="by.innowise.task.model.ApplicationModel" %>
<%@ page import="by.innowise.task.servlet.Constant" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Личный кабинет</title>

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

        .container {
            width: 60%;
            margin: 30px auto;
            background: white;
            padding: 25px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        h1 {
            text-align: center;
        }

        .field {
            margin: 12px 0;
            font-size: 18px;
        }

        .label {
            font-weight: bold;
            color: #2c3e50;
        }

        .status {
            padding: 8px 15px;
            border-radius: 5px;
            display: inline-block;
            font-weight: bold;
        }

        .SUBMITTED { background: #f1c40f; color: black; }
        .ACCEPTED  { background: #2ecc71; color: white; }
        .REJECTED  { background: #e74c3c; color: white; }
    </style>
</head>

<body>

<div class="top-bar">
    <div style="font-size: 25px"><a href="<%=request.getContextPath() + Constant.MAIN_PAGE_REDIRECT%>" style="color: white">МИНСКИЙ УНИВЕРСИТЕТ ИННОВАЙЗ</a></div>
</div>

<div class="container">

    <h1>Личный кабинет абитуриента</h1>

    <%
        ApplicationModel appl =
                (ApplicationModel) request.getAttribute("application");

        if (appl == null) {
    %>
    <p style="text-align:center;">Заявка не найдена</p>
    <%
    } else {
    %>

    <div class="field">
        <span class="label">ФИО:</span>
        <%= appl.getFio() %>
    </div>

    <div class="field">
        <span class="label">Факультет:</span>
        <%= appl.getFacultyName() %>
    </div>

    <div class="field">
        <span class="label">Средний балл диплома:</span>
        <%= appl.getDiplomaScore() %>
    </div>

    <div class="field">
        <span class="label">Телефон:</span>
        <%= appl.getMobilePhone() %>
    </div>

    <div class="field">
        <span class="label">Доп. информация</span>
        <%= appl.getDescription() %>
    </div>

    <div class="field">
        <span class="label">Статус заявки:</span>
        <span class="status <%= appl.getStatus() %>">
            <%= appl.getStatus() %>
        </span>
    </div>

    <%
        }
    %>

</div>

</body>
</html>
