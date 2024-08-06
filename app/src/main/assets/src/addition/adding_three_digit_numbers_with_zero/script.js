function goBack() {
    window.history.back();
}

document.addEventListener('DOMContentLoaded', function () {
    const draggables = document.querySelectorAll('.draggable-item');
    const dropzones = document.querySelectorAll('.dropzone');

    draggables.forEach(draggable => {
        draggable.addEventListener('dragstart', dragStart);
    });

    dropzones.forEach(dropzone => {
        dropzone.addEventListener('dragover', dragOver);
        dropzone.addEventListener('drop', drop);
    });

    function dragStart(event) {
        event.dataTransfer.setData('text/plain', event.target.id);
        setTimeout(() => {
            event.target.classList.add('hide');
        }, 0);
    }

    function dragOver(event) {
        event.preventDefault();
    }

    function drop(event) {
        event.preventDefault();
        const id = event.dataTransfer.getData('text/plain');
        const draggable = document.getElementById(id);
        if (!event.target.classList.contains('dropzone')) {
            return;
        }
        event.target.appendChild(draggable);
        draggable.classList.remove('hide');
    }
});

function checkAnswers() {
    const dropzones = document.querySelectorAll('.dropzone');
    let allCorrect = true;
    let resultHTML = '';

    dropzones.forEach(dropzone => {
        const correctAnswer = dropzone.getAttribute('data-answer');
        const droppedItem = dropzone.querySelector('.draggable-item');

        if (droppedItem && droppedItem.id === correctAnswer) {
            resultHTML += `<p>Correct answer for: ${dropzone.textContent.trim()}</p>`;
        } else {
            resultHTML += `<p>Incorrect answer for: ${dropzone.textContent.trim()}</p>`;
            allCorrect = false;
        }
    });

    const resultElement = document.getElementById('result');
    resultElement.innerHTML = resultHTML;

    if (allCorrect) {
        resultElement.innerHTML += "<p class='correct-feedback'>All answers are correct! Good job!</p>";
    } else {
        resultElement.innerHTML += "<p class='incorrect-feedback'>Some answers are incorrect. Please try again.</p>";
    }
}
