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

        const resumeHtml = await response.text();

        // Fix spaces issue (replaces + with space)
        document.getElementById("resumePreview").innerHTML = decodeURIComponent(resumeHtml.replace(/\+/g, ' '));

        // Show Download Button
        document.getElementById("downloadPdf").style.display = "block";

    } catch (error) {
        console.error("Error submitting form:", error);
        alert("Failed to generate resume. Please check the server.");
    }
});

// PDF Download Function
document.getElementById("downloadPdf").addEventListener("click", function() {
    const { jsPDF } = window.jspdf;
    const doc = new jsPDF();

    doc.setFont("times", "normal");
    doc.text(document.getElementById("resumePreview").innerText, 10, 10);
    doc.save("Resume.pdf");
});
