# Scholarship Finder

A Java desktop application that helps students find scholarships matching their profile.
Built with **JavaFX** and **SQLite**, demonstrating core Object-Oriented Programming concepts.

## Features

- Enter a student profile (name, age, grade level, family income, disability status)
- Automatic eligibility checking against all scholarships in the database
- Recommendations sorted by soonest application deadline
- View scholarship details (description and benefits)
- Student records are saved to the database

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| UI | JavaFX 17 (FXML + CSS) |
| Database | SQLite (file-based, no server) |
| DB access | JDBC (`sqlite-jdbc`) |
| Build | Maven |

## OOP Concepts Used

| Concept | Where |
|---|---|
| Encapsulation | Private fields + getters/setters in all model classes |
| Inheritance | `Person -> Student`; `Scholarship -> GovernmentScholarship, PrivateScholarship, DisabilityScholarship` |
| Abstraction | `abstract class Scholarship` with `abstract boolean isEligible(Student)` and `abstract String getType()` |
| Polymorphism | Each subclass overrides `isEligible()`; one filter loop calls the overridden method |
| Collections | `List<Scholarship>` for search results |
| Exception handling | Custom `DatabaseException` and `InvalidInputException` |
| Singleton | `DatabaseConnection` |
| DAO pattern | `StudentDAO`, `ScholarshipDAO` |

---

## Prerequisites

Install these before running the project:

1. **JDK 17** (or newer) — https://adoptium.net/
2. **Maven 3.8+** — https://maven.apache.org/download.cgi
3. **NetBeans 17+** OR **VS Code** (your choice of IDE)

Verify your setup:

```bash
java -version
mvn -version
```

---

## 1. Database setup — none required

The app uses **SQLite**, a lightweight file-based database. There is no server to
install or configure. On first launch, the app automatically:

- Creates `scholarship.db` in the project folder
- Creates the `students` and `scholarships` tables
- Seeds the `scholarships` table with 10 sample scholarships

To start fresh, just delete `scholarship.db` and run the app again.

---

## 2. Run in NetBeans

1. Install **Apache NetBeans 17 or later**.
2. Open NetBeans, then **File → Open Project**.
3. Select the `scholarship-finder` folder (the one containing `pom.xml`). NetBeans detects it as a Maven project.
4. Wait for NetBeans to download the dependencies (first time takes a few minutes).
5. Run the app — use either method below:

   **Method A — Run Maven goal**
   - Right-click the project in the **Projects** pane → **Run Maven → Goals...**
   - Type `clean javafx:run` → click **Run**.

   **Method B — Set the default Run action (run once)**
   - Right-click the project → **Properties → Actions**.
   - In the **Run project** action, set the **Execute Goals** field to `javafx:run`.
   - Now the green **Run** button (F6) launches the app.

---

## 3. Run in VS Code

1. Install the **Extension Pack for Java** (Microsoft) from the Extensions panel.
   - This includes the Java language support and Maven integration.
2. Install JDK 17 and set `JAVA_HOME` (or let the extension prompt you to configure a JDK).
3. Open the `scholarship-finder` folder: **File → Open Folder**.
4. Wait for the Java extension to import the Maven project and download dependencies.
5. Run the app — use either method below:

   **Method A — Terminal**
   - Open the integrated terminal (`` Ctrl+` ``) and run:
     ```bash
     mvn clean javafx:run
     ```

   **Method B — Maven panel**
   - Click the **Maven** icon in the sidebar.
   - Expand **scholarship-finder → Plugins → javafx → javafx:run**.
   - Click the **Run** (play) button next to `javafx:run`.

---

## How to use the app

1. Click **Get Started**.
2. Fill in the student profile form.
3. Click **Find Scholarships**.
4. The results table shows every matching scholarship, sorted by deadline.
5. Click a row to see its description and benefits.

### Sample profiles to test

| Name | Grade | Income | Disability | Expected result |
|---|---|---|---|---|
| Maria | 11 | 250000 | No | Government + Private matches |
| Juan | 12 | 900000 | No | Private only (income too high for government) |
| Ana | 9 | 150000 | Yes | Disability + Government + Private |
| Carlos | 12 | 300000 | No | Government + Private |

---

## Project structure

```
scholarship-finder/
├── pom.xml
├── scholarship.db        (auto-created on first run)
└── src/main/
    ├── java/com/scholarship/
    │   ├── Main.java
    │   ├── model/
    │   │   ├── Person.java
    │   │   ├── Student.java
    │   │   ├── Scholarship.java
    │   │   ├── GovernmentScholarship.java
    │   │   ├── PrivateScholarship.java
    │   │   └── DisabilityScholarship.java
    │   ├── dao/
    │   │   ├── DatabaseConnection.java
    │   │   ├── StudentDAO.java
    │   │   └── ScholarshipDAO.java
    │   ├── service/
    │   │   └── EligibilityChecker.java
    │   ├── exception/
    │   │   ├── DatabaseException.java
    │   │   └── InvalidInputException.java
    │   └── controller/
    │       ├── MainController.java
    │       ├── ProfileController.java
    │       └── ResultsController.java
    └── resources/com/scholarship/
        ├── main.fxml
        ├── profile.fxml
        ├── results.fxml
        └── styles.css
```

## Troubleshooting

- **"Could not connect to the database"** — make sure the app can write to the project folder, then delete `scholarship.db` and run again.
- **"Table 'scholarships' doesn't exist"** — the app creates tables automatically; delete `scholarship.db` and restart to re-initialize.
- **JavaFX classes can't be found** — use `mvn clean javafx:run` (don't run `Main` directly, which skips the JavaFX module path).
- **Maven not found** — install Maven and ensure it's on your `PATH`.
