<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ошибка</title>
    <style>
      body {
        font-family: Arial, sans-serif;
        text-align: center;
        margin: 100px;
      }

      .error-message {
        color: red;
        font-size: 24px;
      }

      a {
        text-decoration: none;
        color: blue;
        font-size: 18px;
      }

      a:hover {
        text-decoration: underline;
      }
    </style>
  </head>
    <body>
      <h1 class="error-message">Упс! Произошла ошибка.</h1>
      <p>К сожалению, что-то пошло не так.</p>
      <p>Вернуться на <a href="<%=request.getContextPath()%>/">главную</a> страницу</p>
    </body>
</html>