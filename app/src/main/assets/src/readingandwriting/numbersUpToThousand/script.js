function goBack() {
    window.history.back();
}

document.addEventListener('DOMContentLoaded', function () {
    const numberGrid = document.getElementById('number-grid');
    const words = ["", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
                   "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"];
    const tens = ["", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"];

    for (let i = 1; i <= 1000; i++) {
        const gridItem = document.createElement('div');
        gridItem.classList.add('grid-item');
        gridItem.innerHTML = `<b>${i}</b>: ${numberToWords(i)}`;
        numberGrid.appendChild(gridItem);
    }

    function numberToWords(num) {
        if (num === 0) return "Zero";
        if (num < 20) return words[num];
        if (num < 100) return tens[Math.floor(num / 10)] + (num % 10 !== 0 ? "-" + words[num % 10] : "");
        if (num < 1000) return words[Math.floor(num / 100)] + " Hundred" + (num % 100 !== 0 ? " " + numberToWords(num % 100) : "");
        if (num === 1000) return "One Thousand";
    }

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
