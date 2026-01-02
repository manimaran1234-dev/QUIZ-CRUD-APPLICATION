const API = "http://localhost:9090";
const userId = localStorage.getItem("userId");
let questions = [];

fetch(API + "/quiz/questions")
.then(res => res.json())
.then(data => {
    questions = data;
    let html = "";

    data.forEach(q => {
        html += `
        <div style="margin:20px">
            <p><b>${q.question}</b></p>
            <input type="radio" name="${q.id}" value="${q.option1}"> ${q.option1}<br>
            <input type="radio" name="${q.id}" value="${q.option2}"> ${q.option2}<br>
            <input type="radio" name="${q.id}" value="${q.option3}"> ${q.option3}<br>
            <input type="radio" name="${q.id}" value="${q.option4}"> ${q.option4}<br>
        </div>`;
    });

    document.getElementById("quiz").innerHTML = html;
});

function submitQuiz() {
    let answers = [];

    questions.forEach(q => {
        let selected = document.querySelector(`input[name='${q.id}']:checked`);
        if (selected) {
            answers.push({
                questionId: q.id,
                selectedOption: selected.value
            });
        }
    });

    fetch(API + "/quiz/submit/" + userId, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(answers)
    })
    .then(() => {
        alert("Quiz submitted");
        window.location.href = "/user/score.html";
    });
}
