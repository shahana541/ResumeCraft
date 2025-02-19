document.getElementById('resumeForm').addEventListener('submit', function(event) {
    // Prevent form submission (no page reload)
    event.preventDefault();

    // Generate the resume preview in a new window
    generateResume();
});

function generateResume() {
    // Get user input values
    let name = document.getElementById("name").value;
    let email = document.getElementById("email").value;
    let phone = document.getElementById("phone").value;
    let education = document.getElementById("education").value;
    let skills = document.getElementById("skills").value;
    let projects = document.getElementById("projects").value;
    let experience = document.getElementById("experience").value;

    // Create new window (tab) for resume preview
    let resumeWindow = window.open("", "_blank");
    resumeWindow.document.write(`
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Resume Preview</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    line-height: 1.6;
                    margin: 20px;
                }
                h2 {
                    text-align: center;
                    font-size: 28px;
                }
                .section {
                    margin-bottom: 20px;
                }
                .section strong {
                    font-weight: bold;
                }
                .name {
                    font-size: 26px;
                    font-weight: bold;
                    color: #2C3E50;
                }
                .contact-info p {
                    margin: 5px 0;
                }
                .skills, .projects {
                    list-style-type: none;
                    padding: 0;
                }
                .skills li, .projects li {
                    margin-bottom: 5px;
                }
            </style>
        </head>
        <body>
            <h2>Resume Preview</h2>
            <div class="section">
                <div class="name">${name}</div>
            </div>
            <div class="section contact-info">
                <strong>Email:</strong>
                <p>${email}</p>
                <strong>Phone:</strong>
                <p>${phone}</p>
            </div>
            <div class="section">
                <strong>Education:</strong>
                <p>${education}</p>
            </div>
            <div class="section">
                <strong>Skills:</strong>
                <ul class="skills">
                    ${skills.split(',').map(skill => `<li>${skill.trim()}</li>`).join('')}
                </ul>
            </div>
            <div class="section">
                <strong>Projects:</strong>
                <ul class="projects">
                    ${projects.split(',').map(project => `<li>${project.trim()}</li>`).join('')}
                </ul>
            </div>
            <div class="section experience">
                <strong>Experience:</strong>
                <p>${experience}</p>
            </div>
        </body>
        </html>
    `);

    // Focus on the new window
    resumeWindow.focus();
}
