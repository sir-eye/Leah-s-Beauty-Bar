document.getElementById("register-form").addEventListener("submit", async function(event) {

    const username = document.getElementById("username").value.trim();
    const email = document.getElementById("email").value.trim();
    const confirmpassword = document.getElementById("confirm-password").value.trim();
    const password = document.getElementById("password").value.trim();

    // ✅ Debugging: Log values before sending to API
    console.log("Captured Values:", { username, email, password });

    // ✅ Check for missing inputs
    if (!username || !email || !confirmpassword || !password) {
        alert("⚠️ All fields are required!");
        return;
    }
    const response = await fetch("http://localhost:8080/users/register", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, email, password }) // ✅ Matches Java Backend
    });

    if (response.ok) {
        alert("Registration successful! Please login.");
        window.location.href = "/users/login"; // ✅ Redirect to Thymeleaf Login
    } else {
        alert("Registration failed!");
    }
});