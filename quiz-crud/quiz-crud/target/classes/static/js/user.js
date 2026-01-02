const API = "http://localhost:9090";

function login() {

    const user = {
        username: document.getElementById("username").value,
        password: document.getElementById("password").value
    };

    fetch(API + "/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(user)
    })
    .then(res => {
        if (!res.ok) {
            throw new Error("Login failed");
        }
        return res.json();
    })
    .then(data => {
        if (data && data.id) {

            // ✅ SAVE SESSION DATA
            localStorage.setItem("userId", data.id);
            localStorage.setItem("username", data.username);
            localStorage.setItem("role", data.role);

            // ✅ REDIRECT TO DASHBOARD
            window.location.href = "/user/user-dashboard.html";

        } else {
            alert("Invalid username or password");
        }
    })
    .catch(err => {
        alert("Login failed");
        console.error(err);
    });
}
