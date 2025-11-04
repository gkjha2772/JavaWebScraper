---

# KYC_Assignment

## 📌 Project Overview

This project is a **web scraping automation** written in **Java (Maven) with Selenium**.
It extracts structured **public representative data (Name, Title, Party, District, Contact Info, etc.)** from the following public website:

```
https://akleg.gov/senate.php
```

The extracted information is **transformed** and saved into a **JSON file** named:

```
output.json
```

This JSON format aligns with the required **KYC data structure** for identity verification / record management workflow.

---

## 🛠️ Technologies Used

| Component            | Technology                   |
| -------------------- | ---------------------------- |
| Programming Language | Java (JDK 20 or above)       |
| Build System         | Maven                        |
| Automation Framework | Selenium WebDriver           |
| JSON Serialization   | Gson                         |
| Browser Used         | Google Chrome / ChromeDriver |

---

## 📂 Project Structure

```
KYC_Assignment/
│
├── src/main/java/com/mycompany/kyc_assignment/
│   ├── KYC_Assignment.java        (main scraper code)
│
├── output.json                   (final extracted data)
├── pom.xml                       (Maven dependencies)
└── README.md                     (this file)
```

---

## ⚙️ How to Run

### **Step 1 — Ensure Java & Maven installed**

```bash
java -version
mvn -version
```

### **Step 2 — Install dependencies**

NetBeans does this automatically after the project opens.

### **Step 3 — Run the Scraper**

In NetBeans:

```
Right-click → MyScraper.java → Run File
```

Or via terminal:

```bash
mvn compile exec:java -Dexec.mainClass="com.mycompany.kyc_assignment.MyScraper"
```

### ✅ Output will be generated at project root:

```
output.json
```

---

## 🧾 Output Format (Example)

```json
[
  {
    "name": "John Doe",
    "title": "Senator",
    "party": "Republican",
    "profile": "",
    "dob": "",
    "type": "District X",
    "country": "USA",
    "url": "https://akleg.gov/Member/Detail/...",
    "otherinfo": "Email: john.doe@akleg.gov, Phone: 123-456-7890, Address: Example Address, Image: https://example.jpg"
  }
]
```

---

## 🕒 Time Taken

| Task                             | Time       |
| -------------------------------- | ---------- |
| Website Analysis                 | 20 minutes |
| Writing Automation Code          | 45 minutes |
| Testing + Debugging              | 15 minutes |
| Preparing Output & Documentation | 10 minutes |

**Total:** ~1.5 hours

---

## 👤 Author

**Name:** *Gautam Kumar Jha*
**Project:** KYC Data Scraping Assignment

---

If you want, I can now also:

✅ Create **run.bat** (double-click to run)
✅ Create **dist/main.jar** (executable file)
✅ Generate a **PDF report** for submission

Just reply:

```
YES generate run.bat and PDF
```
