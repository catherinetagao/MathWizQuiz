document.addEventListener("DOMContentLoaded", function() {
    const additionQuestions = [
        { question: "What is 1 + 2?", options: ["2", "3", "4", "5"], answer: "3" },
        { question: "What is 2 + 3?", options: ["4", "5", "6", "7"], answer: "5" },
        { question: "What is 3 + 1?", options: ["3", "4", "5", "6"], answer: "4" },
        { question: "What is 4 + 2?", options: ["5", "6", "7", "8"], answer: "6" },
        { question: "What is 2 + 2?", options: ["4", "5", "6", "7"], answer: "4" },
        { question: "What is 3 + 3?", options: ["5", "6", "7", "8"], answer: "6" }
    ];

    const subtractionQuestions = [
        { question: "What is 5 - 1?", options: ["1", "2", "3", "4"], answer: "4" },
        { question: "What is 4 - 2?", options: ["1", "2", "3", "4"], answer: "2" },
        { question: "What is 6 - 3?", options: ["2", "3", "4", "5"], answer: "3" },
        { question: "What is 7 - 4?", options: ["2", "3", "4", "5"], answer: "3" },
        { question: "What is 3 - 1?", options: ["1", "2", "3", "4"], answer: "2" },
        { question: "What is 5 - 2?", options: ["2", "3", "4", "5"], answer: "3" }
    ];

    const multiplicationQuestions = [
        { question: "What is 2 × 2?", options: ["4", "5", "6", "7"], answer: "4" },
        { question: "What is 2 × 3?", options: ["5", "6", "7", "8"], answer: "6" },
        { question: "What is 3 × 2?", options: ["5", "6", "7", "8"], answer: "6" },
        { question: "What is 3 × 3?", options: ["7", "8", "9", "10"], answer: "9" },
        { question: "What is 2 × 4?", options: ["6", "7", "8", "9"], answer: "8" },
        { question: "What is 3 × 4?", options: ["9", "10", "11", "12"], answer: "12" }
    ];

    const divisionQuestions = [
        { question: "What is 4 ÷ 2?", options: ["1", "2", "3", "4"], answer: "2" },
        { question: "What is 6 ÷ 3?", options: ["1", "2", "3", "4"], answer: "2" },
        { question: "What is 8 ÷ 4?", options: ["1", "2", "3", "4"], answer: "2" },
        { question: "What is 10 ÷ 5?", options: ["1", "2", "3", "4"], answer: "2" },
        { question: "What is 12 ÷ 4?", options: ["2", "3", "4", "5"], answer: "3" },
        { question: "What is 14 ÷ 7?", options: ["1", "2", "3", "4"], answer: "2" }
    ];

    const allQuestions = [
        ...additionQuestions,
        ...subtractionQuestions,
        ...multiplicationQuestions,
        ...divisionQuestions
    ];

    function shuffle(array) {
        for (let i = array.length - 1; i > 0; i--) {
            const j = Math.floor(Math.random() * (i + 1));
            [array[i], array[j]] = [array[j], array[i]];
        }
        return array;
    }

    let shuffledQuestions = shuffle([...allQuestions]);
    let questions = shuffledQuestions.slice(0, 10);

    let currentQuestion = 0;
    let score = 0;
    let timeLimit = 60; // Time limit in seconds
    let timer;

    const questionElement = document.getElementById("question");
    const timerElement = document.getElementById("timer");
    const optionsElement = document.getElementById("options");
    const nextButton = document.getElementById("next-btn");
    const resultContainer = document.getElementById("result-container");
    const scoreElement = document.getElementById("score");
    const restartButton = document.getElementById("restart-btn");

    function displayQuestion() {
        const currentQ = questions[currentQuestion];
        questionElement.textContent = currentQ.question;

        optionsElement.innerHTML = "";
        currentQ.options.forEach((option) => {
            const button = document.createElement("button");
            button.textContent = option;
            button.onclick = () => checkAnswer(option);
            optionsElement.appendChild(button);
        });
    }

    function checkAnswer(option) {
        const currentQ = questions[currentQuestion];
        if (option === currentQ.answer) {
            score++;
        }
        currentQuestion++;
        if (currentQuestion < questions.length) {
            displayQuestion();
        } else {
            showResult();
        }
    }

    const rewards = {
        5: "Congratulations! You've earned a bronze medal!",
        8: "Well done! You've earned a silver medal!",
        10: "Amazing work! You've earned a gold medal!"
    };

    function checkReward(score) {
        const thresholds = Object.keys(rewards).map(Number).sort((a, b) => b - a);
        for (let threshold of thresholds) {
            if (score >= threshold) {
                return rewards[threshold];
            }
        }
        return null;
    }

    function saveScore(score) {
        fetch('http://localhost:3000/save-score', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ score: score })
        })
        .then(response => response.json())
        .then(data => console.log('Score saved:', data))
        .catch(error => console.error('Error saving score:', error));
    }

    function showResult() {
        clearInterval(timer); // Stop the timer
        document.getElementById("quiz-container").style.display = "none";
        resultContainer.style.display = "block";
        scoreElement.textContent = `You scored ${score}/${questions.length}`;

        const bronzeMedal = document.getElementById("bronze-medal");
        const silverMedal = document.getElementById("silver-medal");
        const goldMedal = document.getElementById("gold-medal");

        // Hide all medal images initially
        bronzeMedal.style.display = "none";
        silverMedal.style.display = "none";
        goldMedal.style.display = "none";

        const reward = checkReward(score);
        if (reward) {
            scoreElement.textContent += `\n${reward}`;
            // Display the appropriate medal image based on the reward
            if (reward.includes("bronze")) {
                bronzeMedal.style.display = "block";
            } else if (reward.includes("silver")) {
                silverMedal.style.display = "block";
            } else if (reward.includes("gold")) {
                goldMedal.style.display = "block";
            }
        } else {
            scoreElement.textContent += "\nKeep practicing!";
        }

        // Save the score to the database
        saveScore(score);
    }

    function updateTimer() {
        timerElement.textContent = `Time remaining: ${timeLimit}s`;
        if (timeLimit > 0) {
            timeLimit--;
        } else {
            clearInterval(timer);
            showTimeUp();
        }
    }

    function showTimeUp() {
        document.getElementById("quiz-container").style.display = "none";
        resultContainer.style.display = "block";
        scoreElement.textContent = `Time is up! You scored ${score}/${questions.length}`;
        saveScore(score); // Save the score even if time is up
    }

    function restartQuiz() {
        currentQuestion = 0;
        score = 0;
        timeLimit = 60;

        shuffledQuestions = shuffle([...allQuestions]);
        questions = shuffledQuestions.slice(0, 10);

        displayQuestion();
        document.getElementById("quiz-container").style.display = "flex";
        resultContainer.style.display = "none";
        timer = setInterval(updateTimer, 1000); // Start the timer
    }

    nextButton.addEventListener("click", () => {
        if (currentQuestion < questions.length) {
            displayQuestion();
        } else {
            showResult();
        }
    });

    restartButton.addEventListener("click", restartQuiz);

    displayQuestion();
    timer = setInterval(updateTimer, 1000); // Start the timer when the quiz begins
});
