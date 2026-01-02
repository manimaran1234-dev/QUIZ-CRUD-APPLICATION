const API = "http://localhost:9090";

const table = document.getElementById("table");
const thead = document.getElementById("thead");
const tbody = document.getElementById("tbody");

/* SHOW TABLE */
function showTable(headers) {
    table.classList.remove("hidden");
    thead.innerHTML = "<tr>" + headers.map(h => `<th>${h}</th>`).join("") + "</tr>";
    tbody.innerHTML = "";
}

/* ADD QUESTION (FROM admin-question.html) */
function addQuestion() {
    const q = {
        question: question.value,
        option1: o1.value,
        option2: o2.value,
        option3: o3.value,
        option4: o4.value,
        correctAnswer: answer.value
    };

    fetch(API + "/admin/question", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(q)
    })
    .then(res => res.json())
    .then(() => {
        alert("Question Added Successfully");
        loadQuestions();
    });
}

/* MANAGE QUIZ (VIEW + DELETE) */
function loadQuestions() {
    showTable(["ID", "Question", "Answer", "Action"]);

    fetch(API + "/admin/questions")
        .then(res => res.json())
        .then(data => {
            data.forEach(q => {
                tbody.innerHTML += `
                    <tr>
                        <td>${q.id}</td>
                        <td>${q.question}</td>
                        <td>${q.correctAnswer}</td>
                        <td>
                            <button onclick="deleteQuestion(${q.id})">Delete</button>
                        </td>
                    </tr>
                `;
            });
        });
}

function deleteQuestion(id) {
    if (!confirm("Delete this question?")) return;

    fetch(API + "/admin/question/" + id, { method: "DELETE" })
        .then(() => loadQuestions());
}

/* VIEW USERS */
function loadUsers() {
    showTable(["User ID", "Username", "Role"]);

    fetch(API + "/admin/users")
        .then(res => res.json())
        .then(data => {
            data.forEach(u => {
                tbody.innerHTML += `
                    <tr>
                        <td>${u.id}</td>
                        <td>${u.username}</td>
                        <td>${u.role}</td>
                    </tr>
                `;
            });
        });
}

/* VIEW SCORES */
function loadScores() {
    showTable(["User ID", "Score"]);

    fetch(API + "/admin/scores")
        .then(res => res.json())
        .then(data => {
            data.forEach(s => {
                tbody.innerHTML += `
                    <tr>
                        <td>${s.userId}</td>
                        <td>${s.score}</td>
                    </tr>
                `;
            });
        });
}

/* CARD CLICK */
function openAddQuestion() {
    window.location.href = "admin-question.html";
}
