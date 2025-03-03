<%--
  Created by IntelliJ IDEA.
  User: kaban
  Date: 26.02.2025
  Time: 23:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- Импортируем класс с константами -->
<%@ page import="static com.javarush.korotkov.gamequest.constants.WebConstants.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <%--Для корректного отображения вашей веб-страницы на мобильных устройствах и других экранах с разными размерами--%>
    <%--Что делает этот метатег:Управляет масштабированием и шириной страницы!
        width=device-width — указывает браузеру, что ширина страницы должна соответствовать ширине экрана устройства (например, 320px для iPhone SE, 414px для iPhone 11 и т.д.).
        initial-scale=1 — устанавливает начальный масштаб страницы в 100% (без увеличения или уменьшения).--%>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Matrix Game</title>
    <!-- Подключаем Bootstrap 5 CSS стили-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <%--ссылка на css стили с использованием JSTL--%>
    <link href="<c:url value='/resources/css/styles.css' />" rel="stylesheet">
    <link rel="icon" href="<c:url value='/resources/images/favicon.ico' />" type="image/x-icon">

</head>
<body class="${gameImage}"> <%--"startPage"--%>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8 text-center snippet-container">
            <h7><c:out value="${gameText}" escapeXml="false"/></h7>
            <%--escapeXml="false" - чтобы теги <br> интерпретировались как HTML.--%>
            <!-- Контейнер для кнопок -->
            <div class="d-flex justify-content-center mt-4"> <%--для размещения кнопок по центру контейнера; mt-4 добавляет отступ сверху (margin-top)--%>
                <!-- Красная кнопка: btn btn-danger делает кнопку красной; style="width: 100px;" задаёт фиксированную ширину кнопки-->
                <form action="playGame" method="POST" id="red-button-form">
                    <button type="submit" class="btn btn-danger me-3" style="width: 100px;">RED</button>
                </form>
                <!-- Синяя кнопка: btn btn-primary делает кнопку синей; style="width: 100px;" задаёт фиксированную ширину кнопки -->
                <form action="gameOver" method="POST" id="blue-button-form">
                    <button type="submit" class="btn btn-primary" style="width: 100px;">BLUE</button>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- Подключаем jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<%--Подключаем Bootstrap 5 JS: логика для работы интерактивных компонентов, таких как модальные окна, выпадающие меню, карусели --%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<audio id="button-music" src="<c:url value='/resources/sounds/redbutton.mp3' />"></audio>
<audio id="blueButton-music" src="<c:url value='/resources/sounds/bluebutton.mp3' />"></audio>
<script src="<c:url value='/resources/js/redButtonScript.js' />"></script>
<script src="<c:url value='/resources/js/blueButtonScript.js' />"></script>
</body>
</html>
