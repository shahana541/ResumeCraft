document.getElementById("resumeForm").addEventListener("submit", async function(event) {
    event.preventDefault(); // Prevent default form submission

    const formData = new FormData(this); // Get form data

    try {
        const response = await fetch("http://localhost:8080/submit", {
            method: "POST",
            body: new URLSearchParams(formData) // Convert form data to URL encoded format
        });

        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const resumeHtml = await response.text(); // Get response HTML
        document.getElementById("resumePreview").innerHTML = resumeHtml; // Display resume
    } catch (error) {
        console.error("Error submitting form:", error);
        alert("Failed to generate resume. Please check the server.");
    }
});
