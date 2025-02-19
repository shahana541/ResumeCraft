document.getElementById("resumeForm").addEventListener("submit", function (event) {
    event.preventDefault(); // Prevent form from reloading the page

    const formData = new FormData(this);
    const jsonData = Object.fromEntries(formData.entries());

    fetch("http://localhost:8080/submit", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: new URLSearchParams(jsonData)
    })
    .then(response => response.text())
    .then(html => {
        document.open();
        document.write(html);
        document.close();
    })
    .catch(error => console.error("Error:", error));

});
