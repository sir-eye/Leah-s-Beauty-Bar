// Dropdown Menu
document.addEventListener("DOMContentLoaded", function() {
    const dropdown = document.querySelector(".dropdown");
    dropdown.addEventListener("mouseover", function() {
        dropdown.querySelector(".dropdown-content").style.display = "block";
    });

    dropdown.addEventListener("mouseleave", function() {
        dropdown.querySelector(".dropdown-content").style.display = "none";
    });
});

// Form Validation
document.getElementById("login-form").addEventListener("submit", function(event) {
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    if (!emailPattern.test(email)) {
        alert("Invalid email format.");
        event.preventDefault();
        return;
    }

    if (password.length < 6) {
        alert("Password must be at least 6 characters.");
        event.preventDefault();
    }
});
//Back end connection

// Registration Form Validation
document.addEventListener("DOMContentLoaded", function() {
    const registerForm = document.getElementById("register-form");

    if (registerForm) {
        registerForm.addEventListener("submit", function(event) {
            const username = document.getElementById("username").value;
            const email = document.getElementById("email").value;
            const password = document.getElementById("password").value;
            const confirmPassword = document.getElementById("confirm-password").value;

            const usernamePattern = /^[a-zA-Z0-9]{4,12}$/;
            const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

            if (!usernamePattern.test(username)) {
                alert("Username must be 4-12 characters (letters & numbers only).");
                event.preventDefault();
                return;
            }

            if (!emailPattern.test(email)) {
                alert("Invalid email format.");
                event.preventDefault();
                return;
            }

            if (password.length < 6) {
                alert("Password must be at least 6 characters.");
                event.preventDefault();
                return;
            }

            if (password !== confirmPassword) {
                alert("Passwords do not match.");
                event.preventDefault();
            }
        });
    }
});

// Apply fade-in effect after DOM loads
document.addEventListener("DOMContentLoaded", function () {
    const fadeElements = document.querySelectorAll(".fade-in");
    fadeElements.forEach((el, index) => {
        setTimeout(() => {
            el.style.opacity = "1";
        }, index * 200); // Delay each element slightly for a smooth effect
    });
});

// Dark Mode Toggle with Local Storage
document.addEventListener("DOMContentLoaded", function () {
    const toggleButton = document.getElementById("dark-mode-toggle");
    const body = document.body;

    // Check if Dark Mode is enabled in localStorage
    if (localStorage.getItem("darkMode") === "enabled") {
        body.classList.add("dark-mode");
        toggleButton.textContent = "☀️ Light Mode";
    } else {
        toggleButton.textContent = "🌙 Dark Mode";
    }

// Toggle Dark Mode
toggleButton.addEventListener("click", function () {
    if (body.classList.contains("dark-mode")) {
        body.classList.remove("dark-mode");
        localStorage.setItem("darkMode", "disabled");
        toggleButton.textContent = "🌙 Dark Mode";
    } else {
        body.classList.add("dark-mode");
        localStorage.setItem("darkMode", "enabled");
        toggleButton.textContent = "☀️ Light Mode";
    }
});
});
