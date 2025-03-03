// Находим форму красной кнопки по её идентификатору
const redButtonForm = document.getElementById('red-button-form');

// Находим аудиоэлемент
const redButtonAudio = document.getElementById('button-music');

// Обработчик отправки формы красной кнопки
redButtonForm.addEventListener('submit', function (event) {
    event.preventDefault(); // Останавливаем стандартное поведение формы (перезагрузку страницы)

    // Запускаем музыку
    redButtonAudio.play();

    // Отправляем форму через 2 секунды (чтобы музыка успела начать играть)
    setTimeout(() => {
        redButtonForm.submit();
    }, 500);
});