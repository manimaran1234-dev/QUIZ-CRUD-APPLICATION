const userId = localStorage.getItem("userId");
const username = localStorage.getItem("username");

if (!userId) {
    window.location.href = "/user/user-login.html";
}

document.getElementById("uname").innerText = username;

function goQuiz() {
    window.location.href = "/user/quiz.html";
}

function viewScore() {
    window.location.href = "/user/score.html";
}

function logout() {
    localStorage.clear();
    window.location.href = "/user/user-login.html";
}
