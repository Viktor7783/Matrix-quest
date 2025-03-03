// Находим форму красной кнопки по её идентификатору
const blueButtonForm = document.getElementById('blue-button-form');

// Находим аудиоэлемент
const blueButtonAudio = document.getElementById('blueButton-music');

// Обработчик отправки формы красной кнопки
blueButtonForm.addEventListener('submit', function (event) {
    event.preventDefault(); // Останавливаем стандартное поведение формы (перезагрузку страницы)

    // Запускаем музыку
    blueButtonAudio.play();

    // Отправляем форму через 2 секунды (чтобы музыка успела начать играть)
    setTimeout(() => {
        blueButtonForm.submit();
    }, 500);
});