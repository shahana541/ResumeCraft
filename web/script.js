document.getElementById("resumeForm").addEventListener("submit", function (event) {
    event.preventDefault(); // Prevent page reload

    const formData = new FormData(this);

    fetch("http://localhost:8080/submit", {
        method: "POST",
        body: new URLSearchParams(formData), // Convert to x-www-form-urlencoded format
        headers: {
            "Content-Type": "application/x-www-form-urlencoded",
        },
    })
    .then(response => response.text()) // Get text response from server
    .then(data => {
        console.log("Server Response:", data);

        // Generate Resume Preview
        const previewSection = document.getElementById("resumePreview");
        previewSection.innerHTML = `
            <h2>Resume Preview</h2>
            <p><strong>Name:</strong> ${formData.get("name")}</p>
            <p><strong>Email:</strong> ${formData.get("email")}</p>
            <p><strong>Phone:</strong> ${formData.get("phone")}</p>
            <p><strong>Education:</strong> ${formData.get("education")}</p>
            <p><strong>Experience:</strong> ${formData.get("experience")}</p>
            <p><strong>Skills:</strong> ${formData.get("skills")}</p>
        `;
    })
    .catch(error => console.error("Error:", error));
});