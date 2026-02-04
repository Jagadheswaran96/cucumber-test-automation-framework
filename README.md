🚀 Selenium Cucumber Automation Framework (Java)

A scalable, parallel-ready, CI/CD-enabled automation framework built using Selenium, Cucumber (BDD), JUnit, Maven, Allure, Extent Reports, and Jenkins with support for cross-browser execution, failure screenshots, and video recording.

📌 Key Highlights

✔ BDD with Cucumber (Gherkin)
✔ Page Object Model (POM)
✔ Selenium WebDriver with Driver Factory
✔ Parallel Execution
✔ Retry for flaky tests
✔ Allure + Extent HTML Reporting
✔ Failure Screenshots
✔ Failure Video Recording
✔ Jenkins & GitHub Ready
✔ Maven based execution

🧱 Tech Stack
Layer	Technology
Language	Java 17+
UI Automation	Selenium WebDriver
BDD	Cucumber
Test Runner	JUnit
Build Tool	Maven
Reports	Allure, Extent
CI/CD	Jenkins
VCS	GitHub
📁 Framework Structure
project-root
│
├── pom.xml
├── README.md
├── src/test/java
│   ├── com.lambda.cucumber
│   │   ├── driver
│   │   │   └── DriverFactory.java
│   │   ├── hooks
│   │   │   └── Hooks.java
│   │   ├── pages
│   │   │   └── LoginPage.java
│   │   ├── steps
│   │   │   └── LoginSteps.java
│   │   ├── runners
│   │   │   └── TestRunner.java
│   │   └── utils
│   │       ├── ScreenshotUtils.java
│   │       ├── VideoRecorderUtil.java
│   │       ├── ExtentManager.java
│   │       └── ExtentTestManager.java
│
├── src/test/resources
│   ├── features
│   │   └── login.feature
│   └── allure.properties
│
└── target
    ├── allure-results
    ├── videos
    └── screenshots

🔹 Driver Management

DriverFactory ensures:

Thread-safe WebDriver

Parallel execution ready

Cross-browser support

Browser can be passed from Maven:

mvn test -Dbrowser=chrome
mvn test -Dbrowser=firefox

🔹 Hooks – Central Control Point

Hooks.java handles:

Driver setup & quit

Extent test creation

Allure attachments

Failure screenshots

Failure video recording

On Failure:

✔ Screenshot captured
✔ Video saved
✔ Attached to Allure
✔ Embedded in Extent

📸 Screenshot Handling

Captured automatically on failure using:

ScreenshotUtils.getScreenshot()


Stored under:

target/screenshots

🎥 Video Recording

Screen recording starts per scenario and stops on completion.

Videos are stored in:

target/videos


On failure, video is attached to:

Allure report

Jenkins artifacts

📊 Reporting
Allure

Generate report:

mvn test
mvn allure:report


Open:

target/site/allure-maven-plugin/index.html


Or live server:

mvn allure:serve

Extent Report

Automatically generated under:

target/extent-report/index.html


Contains:

Step logs

Failure screenshots

Scenario status

🔁 Retry for Flaky Tests

Failed scenarios are automatically retried using:

Cucumber rerun plugin

Retry runner

Ensures flaky failures don’t break the build.

⚡ Parallel Execution

JUnit parallel execution is enabled via:

junit.jupiter.execution.parallel.enabled=true


Each thread gets its own WebDriver instance.

🔄 Jenkins Integration

Jenkins pipeline:

mvn clean test
mvn allure:report


Jenkins can publish:

Allure Report

Videos

Extent Report

🌍 GitHub Ready

This project supports:

Clean Maven build

Jenkins CI

GitHub Actions (optional)

Easy cloning and execution

🧪 Run Tests
mvn clean test


Run with browser:

mvn test -Dbrowser=edge
