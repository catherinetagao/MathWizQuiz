function checkAddRegrouping() {
    const answers = [13, 17, 23];
    const userAnswers = [
        parseInt(document.getElementById('addRegrouping1').value),
        parseInt(document.getElementById('addRegrouping2').value),
        parseInt(document.getElementById('addRegrouping3').value)
    ];

    let correctCount = 0;
    let resultHTML = "";
    for (let i = 0; i < answers.length; i++) {
        if (userAnswers[i] === answers[i]) {
            correctCount++;
            resultHTML += `Problem ${i + 1}: Correct! <br>`;
        } else {
            resultHTML += `Problem ${i + 1}: Incorrect. The correct answer is ${answers[i]}. <br>`;
        }
    }

    const resultElement = document.getElementById('addRegroupingResult');
    resultElement.innerHTML = `You got ${correctCount} out of ${answers.length} correct. <br> ${resultHTML}`;
}

function checkAddGrouping() {
    const answers = [42, 62, 85];
    const userAnswers = [
        parseInt(document.getElementById('addGrouping1').value),
        parseInt(document.getElementById('addGrouping2').value),
        parseInt(document.getElementById('addGrouping3').value)
    ];

    let correctCount = 0;
    let resultHTML = "";
    for (let i = 0; i < answers.length; i++) {
        if (userAnswers[i] === answers[i]) {
            correctCount++;
            resultHTML += `Problem ${i + 1}: Correct! <br>`;
        } else {
            resultHTML += `Problem ${i + 1}: Incorrect. The correct answer is ${answers[i]}. <br>`;
        }
    }

    const resultElement = document.getElementById('addGroupingResult');
    resultElement.innerHTML = `You got ${correctCount} out of ${answers.length} correct. <br> ${resultHTML}`;
}
