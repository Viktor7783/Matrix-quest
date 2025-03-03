// Находим форму красной кнопки по её идентификатору
const restartButtonForm = document.getElementById('restart-button-form');

// Находим аудиоэлемент
const restartButtonAudio = document.getElementById('restartButton-music');

// Обработчик отправки формы красной кнопки
restartButtonForm.addEventListener('submit', function (event) {
    event.preventDefault(); // Останавливаем стандартное поведение формы (перезагрузку страницы)

    // Запускаем музыку
    restartButtonAudio.play();

    // Отправляем форму через 2 секунды (чтобы музыка успела начать играть)
    setTimeout(() => {
        restartButtonForm.submit();
    }, 700);
});