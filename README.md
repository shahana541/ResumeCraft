# 📄 ResumeCraft – Simple Resume Builder  

ResumeCraft is a web-based application that allows users to create and download professional resumes in PDF format. It features an interactive form to input details, generates a formatted resume preview, and provides a download option—all without requiring a database.  

## 🚀 Features  
✔️ **User-friendly Interface** – Simple and clean UI for easy navigation.  
✔️ **Real-time Resume Preview** – Displays a structured preview of the resume.  
✔️ **Downloadable Resume** – Allows users to download the generated resume as a PDF.  
✔️ **No Database Required** – Uses Java for backend processing and fetches data via HTTP requests.  
✔️ **Completely Local** – Runs on a local server using Java’s built-in HttpServer.  

## 🛠️ Tech Stack  
- **Frontend:** HTML, CSS, JavaScript  
- **Backend:** Java (using HttpServer for handling requests)  

## 📌 Getting Started  

### 🔹 Prerequisites  
Ensure you have the following installed:  
- **Java JDK 8 or higher**  
- **Git** (for cloning the repository)  
- **A web browser** (Chrome, Firefox, Edge, etc.)  

### 🔹 Installation & Setup  

1️⃣ **Clone the repository**  
```sh
git clone https://github.com/shahana541/ResumeCraft.git
cd ResumeCraft
```
  
2️⃣ **Run the Java server**  
Compile and execute the backend Java server:  
```sh
javac ResumeServer.java
java ResumeServer
```
You should see: **✅ Server started on port 8080...**  

3️⃣ **Open the application**  
- Open `index.html` in a browser  
- Fill in your details and generate your resume  

4️⃣ **Download the resume**  
- After previewing the resume, click the **Download** button to save it as a PDF  

## 💡 How It Works  
1. The **frontend (HTML, CSS, JavaScript)** collects user input.  
2. The **backend (Java server)** processes the form data and returns the formatted resume.  
3. The resume is displayed in a preview section with an option to download it as a PDF.  

## 👨‍💻 Contribution  
Want to improve ResumeCraft? Feel free to fork the repo, create a new branch, and submit a pull request!  
