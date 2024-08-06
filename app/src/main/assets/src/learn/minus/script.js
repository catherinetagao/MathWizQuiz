function checkSubRegrouping() {
    const answers = [8, 14, 15];
    const userAnswers = [
        parseInt(document.getElementById('subRegrouping1').value),
        parseInt(document.getElementById('subRegrouping2').value),
        parseInt(document.getElementById('subRegrouping3').value)
    ];
    let result = "Great job!";
    for (let i = 0; i < answers.length; i++) {
        if (userAnswers[i] !== answers[i]) {
            result = "Try again!";
            break;
        }
    }
    document.getElementById('subRegroupingResult').textContent = result;
}

function checkSimpleSubtraction() {
    const answers = [6, 11, 13];
    const userAnswers = [
        parseInt(document.getElementById('simpleSubtraction1').value),
        parseInt(document.getElementById('simpleSubtraction2').value),
        parseInt(document.getElementById('simpleSubtraction3').value)
    ];
    let result = "Great job!";
    for (let i = 0; i < answers.length; i++) {
        if (userAnswers[i] !== answers[i]) {
            result = "Try again!";
            break;
        }
    }
    document.getElementById('simpleSubtractionResult').textContent = result;
}
