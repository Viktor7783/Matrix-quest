// Получаем элементы
const form = document.querySelector('form'); // Форма
const audio = document.getElementById('backgroundMusic'); // Аудиоэлемент

// Обработчик отправки формы
form.addEventListener('submit', function (event) {
    event.preventDefault(); // Останавливаем стандартное поведение формы (перезагрузку страницы)

    // Запускаем музыку
    audio.play();

    // Отправляем форму через 1 секунду (чтобы музыка успела начать играть)
    setTimeout(() => {
        form.submit();
    }, 2000);
});