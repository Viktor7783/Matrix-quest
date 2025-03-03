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
    <title>Вступление!</title>
    <!-- Подключаем Bootstrap 5 CSS стили-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <%--ссылка на css стили с использованием JSTL--%>
    <link href="<c:url value='/resources/css/styles.css' />" rel="stylesheet">
    <link rel="icon" href="<c:url value='/resources/images/favicon.ico' />" type="image/x-icon">

</head>
<body class="startPage">
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8 text-center snippet-container">
            <%=GREETINGS%>
            <form action="start-servlet" method="POST" class="mt-4">
                <%--<form>: Тег, который создаёт форму для сбора данных от пользователя. action="hello-servlet": Указывает, куда отправлять данные формы. class="mt-4": Добавляет класс Bootstrap, который задаёт внешний отступ сверху (margin-top: 1.5rem;--%>
                <div class="form-group mb-3"> <%--Класс Bootstrap, который добавляет стили для группы элементов формы (например, отступы и выравнивание).--%>
                    <label for="playerName" class="text-success mb-2" style="font-size: 1.1em; display: block;"> <%-- label cоздаёт текстовую метку для поля ввода.
                    for="username": Связывает метку с полем ввода, у которого id="username". При клике на метку фокус перемещается на это поле; class="text-success mb-2": Добавляет стили Bootstrap: text-success — зелёный цвет текста. mb-2 — внешний отступ снизу (margin-bottom: 0.5rem;).
                    style="font-size: 1.1em; display: block;": Встроенные стили: font-size: 1.1em; — увеличивает размер текста на 10%; display: block; — делает метку блочным элементом, чтобы она занимала всю ширину.--%>
                        Введи сюда своё настоящее имя и навсегда его забудь!
                    </label>
                    <input type="text" <%--<input>: Создаёт поле для ввода текста--%>
                           class="form-control transparent-input" <%--Добавляет стили: form-control — класс Bootstrap, который задаёт стандартные стили для полей ввода (например, отступы, границы).transparent-input — ваш пользовательский класс, который делает поле прозрачным и добавляет зелёный текст.--%>
                           id="playerName" <%-- Уникальный идентификатор поля, который связывает его с меткой (<label for="username">).--%>
                           name="playerName" <%--Имя поля, которое будет использоваться при отправке данных на сервер (например, username=ВведённоеИмя).--%>
                           placeholder="Введите имя..." <%--Текст-подсказка, которая отображается в поле, пока пользователь ничего не ввёл.--%>
                           required> <%--Указывает, что поле обязательно для заполнения. Если пользователь не введёт данные, форма не отправится.--%>
                </div>
                <button type="submit" class="btn btn-outline-success">Войти в Матрицу</button>
                <%--<button>: Создаёт кнопку; type="submit": Указывает, что это кнопка для отправки формы. class="btn btn-outline-success": Добавляет стили Bootstrap: btn — базовый класс для кнопок. btn-outline-success — делает кнопку с зелёной outline-обводкой.--%>
            </form>
        </div>
    </div>
</div>
<!-- Подключаем jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<%--Подключаем Bootstrap 5 JS: логика для работы интерактивных компонентов, таких как модальные окна, выпадающие меню, карусели --%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<%--Аудиоэлемент--%>
<audio id="backgroundMusic">
    <source src="<c:url value='/resources/sounds/matrix.mp3'/>" type="audio/mpeg">
</audio>
<!-- Подключаем внешний JavaScript-файл для воспроизведения музыки-->
<script src="<c:url value='/resources/js/soundScript.js' />"></script>
</body>
</html>