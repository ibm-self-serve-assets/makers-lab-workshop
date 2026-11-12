# IBM Bob: Java Modernization – Struts To Spring Boot Lab Guide

> **Note:** Your output may not exactly match the screenshots in this guide, since Bob's output is not deterministic. File names, the number of subtasks, to-do items, generated files, tests and coverage figures can differ from run to run.

## Contents
1. [About this Lab](#1-about-this-lab)
2. [Pre-requisites](#2-pre-requisites)
3. [Overview](#3-overview)
4. [Lab Steps](#4-lab-steps)
    - 4.1 [Import Project into Bob Workspace](#41-import-project-into-bob-workspace)
    - 4.2 [Reverse Engineering – Understanding the Legacy Application](#42-reverse-engineering--understanding-the-legacy-application)
    - 4.3 [Java Version Upgrade](#43-java-version-upgrade)
    - 4.4 [Full Application Modernization](#44-full-application-modernization)
    - 4.5 [Generate Unit Tests (Optional)](#45-generate-unit-tests-optional)
    - 4.6 [Create Kubernetes/OpenShift Deployment Artifacts (Optional)](#46-create-kubernetesopenshift-deployment-artifacts-optional)
5. [Key Modernization Achievements](#5-key-modernization-achievements)
6. [Troubleshooting](#6-troubleshooting)

---

## 1. About this Lab
### Objective of this lab:
* Experience IBM Bob features and capabilities.
* Explore Bob’s comprehensive SDLC capabilities with a legacy Java application modernization use-case.
* Experience the capabilities of the IBM Bob Premium package for Java modernization.

In this lab, we will take a **Legacy Struts 1.3** application whose documentation is not available and will perform the following activities:
* **Reverse Engineer** the legacy code to generate comprehensive documentation, architecture diagrams, ER diagrams, and UML diagrams.
* Use the **‘Java Modernization’** workflow to upgrade the Java version from 1.8 to 17.
* Use the **‘UI Modernization’** workflow to modernize the legacy Struts application into a Spring Boot backend and a React frontend.
* Use the **‘Java Unit Testing’** workflow to add JaCoCo and generate unit tests for the new backend (optional).
* **Generate** required OpenShift artifacts and scripts for deployment.

---

## 2. Pre-requisites
### 2.1 IBM Bob IDE
Install and configure IBM Bob. Follow instructions from [https://bob.ibm.com/docs/ide/getting-started/install](https://bob.ibm.com/docs/ide/getting-started/install).

### 2.2 Access to IBM Premium package for Java Modernization

### 2.3 BobCoins
Around 100 BobCoins are required.

### 2.4 Verify the Java Modernization Premium Package
1. Click on the Bob Settings icon.

![Bob Settings](images/image-1.png)

2. Select the account which has the Java Modernization Premium Package. You should see the premium package name listed under Add-ons.

![Java Modernization Premium Package](images/image-2.png)

### 2.5 Required Software
The following tools are used to build and run the legacy, upgraded and modernized applications in this lab. Install them before you start.

| Tool | Used for |
| :--- | :--- |
| Java 17 (JDK) | Building and running the upgraded legacy app and the Spring Boot backend |
| Maven | Building the Java projects and running the backend (`mvn spring-boot:run`) |
| SDKMAN | Switching Java versions during the Java upgrade |
| Node.js and npm | Building and running the React frontend (`npm run dev`) |

After each install, open a new terminal and run the verification command shown to confirm the tool is available.

1. Install the `openjdk-17-jdk` package
```sh
# MacOS
brew install openjdk@17
# Ubuntu/Debian
sudo apt update
sudo apt install openjdk-17-jdk
# RHEL
sudo dnf install java-17-openjdk-devel
# Windows : Refer https://www.oracle.com/jp/java/technologies/downloads/#java17-windows to install
```
Verify (the output should report version 17):
```sh
java -version
```
2. Install the `maven` package
```sh
# MacOS
brew install maven
# Ubuntu/Debian
sudo apt update
sudo apt install maven
# RHEL
sudo dnf install maven
# Windows
choco install maven
# Refer https://chocolatey.org/install#individual to install chocolatey
```
Verify (the output should show the Maven version and the Java version it uses):
```sh
mvn -version
```
3. Install `sdkman`
```sh
# MacOS
curl -s "https://get.sdkman.io" | bash
# Ubuntu/Debian
curl -sL https://get.sdkman.io | sudo bash
# RHEL
curl -sL https://get.sdkman.io | sudo bash
```
Verify (load SDKMAN into the current shell first, or open a new terminal):
```sh
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk version
```
If SDKMAN is not installed, see [6.1 SDKMAN not installed](#61-sdkman-not-installed).

4. Install `node` and `npm` (npm is installed together with Node.js). Use Node.js 20 or later (LTS recommended).
```sh
# MacOS
brew install node
# Ubuntu/Debian
curl -fsSL https://deb.nodesource.com/setup_lts.x | sudo -E bash -
sudo apt install -y nodejs
# RHEL
sudo dnf module install nodejs:20
# Windows
choco install nodejs-lts
# Or download the installer from https://nodejs.org/en/download
```
Verify (both commands should print a version number):
```sh
node -v
npm -v
```

### 2.6 Install PlantUML Plugin
Install the **“PlantUML Markdown Preview”** extension on IBM Bob IDE.
1. Click on the ‘Extensions’ icon in the left activity bar.

![Extensions Icon](images/image-3.png)

2. Search for “PlantUML”.
3. Install the extension shown below:

![Extension: PlantUML Markdown Preview](images/image-4.png)

### 2.7 Demo Application
We are using a legacy-style netbanking application built with:
* **Java 8** (no newer language features)
* **Apache Struts 1.x** (Action, ActionForm, struts-config.xml)
* **JSPs** with scriptlets (`<% %>`)
* **Plain Servlets** (InitServlet, LogoutServlet)
* **JDBC** (no ORM/JPA)
* **SQLite** (file-based database)
* **XML Configuration** (web.xml, struts-config.xml)
* **WAR packaging**

---

## 3. Overview
This lab showcases the complete journey from legacy Struts 1.x + SQLite to modern **Spring Boot 3.x + React**, including generated unit tests and deployment to OpenShift/Kubernetes.

---

## 4. Lab Steps

### 4.1 Import Project into Bob Workspace
1. Download ‘Java-Modernization-Bob-2.0’ from [https://github.com/ibm-self-serve-assets/Java-Modernization-Bob-2.0](https://github.com/ibm-self-serve-assets/Java-Modernization-Bob-2.0).
Alternatively, you can use Bob to clone the repository to your local machine.

Run ```git clone https://github.com/ibm-self-serve-assets/Java-Modernization-Bob-2.0``` to clone the repo to a local folder.

2. Go to IBM Bob and open the folder `Java-Modernization-Bob-2.0`.

The folder structure should appear as follows:

![Folder Structure](images/image-5.png)

### 4.2 Reverse Engineering – Understanding the Legacy Application
**Objective:** Analyze the undocumented early-2000s code.

1. **Switch to Ask Mode:** Click on the **+** icon in the Bob chat box, and select **‘Ask’** from the Modes list.

![Switch to Ask Mode](images/image-6.png)

2. **Enter Prompt and Approve Tasks:** Enter the prompt below and hit enter to send. Bob will explore the application files. Keep reviewing and approving the tasks as Bob works.
   > *"Help me understand this legacy-netbanking application. Save the generated documentation and diagrams in legacy-netbanking-documentation directory for it. Make sure to generate required plantUML diagrams like sequence diagram along with mermaid architecture and ER diagram etc of current implementation"*

![Enter Prompt](images/image-7.png)

![Processing Tasks](images/image-8.png)

3. **Review the Analysis and Switch to Agent Mode:** Ask mode cannot save files. Once the analysis is complete, Bob summarizes its findings and proposes an output layout for the documentation and diagrams. Click on the **+** icon again and select **‘Agent’** so that Bob can create the documentation files.

![Proposed Output Layout](images/image-9.png)

![Switch to Agent Mode](images/image-10.png)

4. **Confirm the Structure and Generate Files:** Reply to Bob with *"The structure looks good"*. Bob will start writing the files. Approve the Write File requests, or click **‘Approve edit tools for task’** to approve them all at once. When Bob finishes, it lists the files it generated in the `legacy-netbanking-documentation` directory.

![Approve Write File](images/image-11.png)

![Documentation Generated](images/image-12.png)

5. **Review Artifacts:**
   - Right-click the generated `.md` file and select **‘Open Preview’**.

   - Right-click a `.puml` file under `legacy-netbanking-documentation/diagrams` and select **‘Preview PlantUML File’**.

![PlantUML Preview Menu](images/image-13.png)

![Activity Diagram Preview](images/image-14.png)

### 4.3 Java Version Upgrade
**Objective:** Upgrade to Java 17.

1. **Start the Workflow:** Click on the workflow icon (play button) in the Bob toolbar. You will see a list of workflows, including Java Modernization, Java Unit Testing and others (for example, Explain code). Click **Start** next to **Java Modernization**.

![Start Workflow](images/image-15.png)
![Select Workflow](images/image-16.png)

2. **Verify the Project and Approve Checks:** Verify that the project path (`legacy-netbanking`) is correct and click **Continue**. Bob will run some commands to check that the required tools are available (for example, detecting dependencies, querying vulnerabilities and running an initial build). Approve those tasks.

![Continue](images/image-17.png)

![Query Vulnerabilities](images/image-18.png)

![Build Java Project](images/image-19.png)

3. **Select Java Upgrade:** After the tasks are completed you should see the available modernization options. Select **Java Upgrade**, make sure **Enable Git Flow** is turned off, and click **Continue**. Approve the tasks to check prerequisites.

![Modernization Options](images/image-20.png)

4. **Configure Upgrade:** Bob confirms that SDKMAN is installed and shows the Java Upgrade Configuration form. Set:
   - Select **Java Distribution: Oracle JDK**.
   - Select **Java Version: 17**.
   - Select **Jakarta EE Version: Do not Upgrade**.

![Java Upgrade Config](images/image-21.png)

5. **Run the Upgrade:** Click **Continue** and approve the tool calls. Bob runs the OpenRewrite recipe `UpgradeToJava17` against the project.

![Run Rewrite Recipe](images/image-22.png)

6. **Skip Vulnerability Fixes:** Bob will ask whether to fix vulnerabilities. For this lab, select **“No”** since we are modernizing the application.

![Fix Vulnerabilities](images/image-23.png)

7. **Build and Review the Summary:** Approve the To-Do list tasks for the final step. Bob runs the build, resolves any compilation issues and verifies that the full application build succeeds. Once completed, a summary flowchart will be displayed. It confirms that the Java version was upgraded from 1.8 to 17 and that the build completed successfully.

![To-Do List](images/image-24.png)

![Upgrade Success Flowchart](images/image-25.png)

### 4.4 Full Application Modernization
**Objective:** Transform to Spring Boot + React using the UI Modernization workflow.

1. **Start UI Modernization:** Go to the workflows tab again and click **Start** next to **Java Modernization**. Expand the entry to see the available options (Java Upgrade, Liberty Modernization and UI Modernization). Verify the project path and click **Continue**. Approve the tasks, and when the Modernization Type screen appears, select **“UI Modernization”** this time, keep Git Flow turned off, and click **Continue**.

![Select Workflow](images/image-26.png)

![Analyze Project](images/image-27.png)

![UI Modernization](images/image-28.png)

2. **Analyze the Application:** Bob starts the **Analyze Application Architecture** step. Click **Continue**, then approve the subtask. Bob reads the key build, configuration and database files and runs commands to read the DAO and util classes. Approve the commands. Bob then writes the architecture documents in the `legacy-netbanking` folder. Approve the Write File requests, or click **‘Approve edit tools for task’** to approve them all at once.

![Analyze Application Architecture](images/image-29.png)

![Approve Subtask](images/image-30.png)

![Analysis Commands](images/image-31.png)

![Write architecture document](images/image-32.png)

3. **Set Up the Project:** Once the analysis is completed, the **Setup UI Modernization Project** form is displayed. Keep both **“Frontend Modernization”** and **“Backend Modernization”** switched on. The framework and path fields are still empty at this point.

   The frontend and backend folders must exist before you submit the form. Open a terminal one level above `legacy-netbanking` (the root of the `Java-Modernization-Bob-2.0` folder) and run the following commands:
```sh
mkdir frontend
mkdir backend
```

   Then configure the modernization options:
   - Select **Frontend Framework: React**.
   - Select **Frontend Design System: Carbon Design System**.
   - Frontend Project Path: Click **Select Directory** and choose the `frontend` directory we just created.
   - Select **Backend Framework: Spring Boot**.
   - Backend Project Path: Click **Select Directory** and choose the `backend` directory we just created.
   - Click **Setup Project**.

![Setup UI Modernization Project](images/image-33.png)

![Create frontend and backend folders](images/image-34.png)

![Configure Modernization](images/image-35.png)

4. **Backend Migration:** Bob starts the **Backend Migration** subtask. Approve the subtask and the to-do list (from analyzing the legacy source to compiling with Maven). Bob asks a design question about how the Spring Boot REST API should handle authentication and authorization. Select reccomended option.

![Backend Migration To-Do List](images/image-36.png)

![Authentication Design Decision](images/image-37.png)

5. **Test the Backend:** Once the Backend Migration is done, Bob asks you to start the backend yourself. Copy the command from the Bob chat window (`cd <path>/backend && mvn spring-boot:run`) and run it in a terminal. It needs Java 17 or later. Then open `http://localhost:8080/swagger-ui/index.html` in a browser. You should see the **ABC Bank - Netbanking REST API** Swagger UI. Back in the Bob chat window, select **“Yes, the application started successfully with no errors.”** to finish the Backend Migration.

![Start Backend Command](images/image-38.png)

![Backend Running in Terminal](images/image-39.png)

![Swagger UI](images/image-40.png)


6. **Frontend Setup:** Approve the tool to start the Frontend Migration. The Frontend Migration runs as several subtasks (for example, setup, components and home page, pages and backend integration, and validation). In the setup subtask, Bob reads the architecture document, checks the Node and npm versions, and proposes its key architectural decisions (for example, the build tool, React, TypeScript, Carbon, routing, state management, API client and a dev proxy to the backend). Select **“Approved - proceed with all recommended defaults.”**

![Start Frontend Migration](images/image-41.png)


7. **Run the Frontend:** When Bob asks you to confirm the app starts, run the frontend command shown in the chat window (`cd <path>/frontend && npm run dev`). Vite serves the app on `http://localhost:5173`. Select **“Yes, the application started successfully. The landing page loads and theming works properly.”**

![Frontend Running in Terminal](images/image-42.png)

![Landing Page](images/image-43.png)

8. **Frontend Components & Home Page:** Bob reads the architecture document and explores the `frontend` and `backend` folders. Approve the commands. When the build (`npm run build`) and lint pass, approve the required tasks.

![Frontend Components Subtask](images/image-44.png)


9. **Check the Application:** In the browser, log in with Username: `admin` and Password: `admin123`. The home page shows the account summary and quick links. Perform a fund transfer to Account: `ACC1001`. 

![Modernized UI](images/image-45.png)



10. **Validation:** Bob provides launch instructions for the full stack. Press **F5** (or **Run → Start Debugging**), select **“Launch Full Stack Application”** and wait for both services to start. Open `http://localhost:5173` in your browser, then select **“Yes, both applications are working correctly…”** in the chat.

![Validation Launch Instructions](images/image-48.png)

11. The modernization is now complete. Bob shows an **All tasks completed!** summary with a visualization of the modernization steps and the cost of each subtask.
![Complete](images/image-49.png)

### 4.5 Generate Unit Tests
**Objective:** Add JaCoCo and generate unit tests for the modernized Spring Boot backend using the Java Unit Testing workflow.

1. **Start the Workflow:** Open the `backend` directory in a Bob window. Click on the workflow icon (play button) and click **Start** next to **Java Unit Testing**. Bob checks the project and finds no code coverage tool, so it starts the **Add JaCoCo library** subtask. Approve the subtask.

![Execute Subtask: Add JaCoCo Library](images/image-50.png)

2. **Install JaCoCo:** Approve the to-do list (analyze the build files, install JaCoCo, verify the installation). Approve the change to the backend build file (you can click **Open Diff** to review it first). Bob then verifies the installation by running `mvn test` with coverage enabled. Approve the tool.

![Add JaCoCo To-Do List](images/image-51.png)

![Apply Diff to build file](images/image-52.png)

![Run Unit Tests](images/image-54.png)

4. **Select Tasks:** Bob generates a unit test strategy document and opens the **Select Tasks** form. Keep **Generate Unit Tests** and **Run Code Coverage** switched on, keep **Candidate Selection Strategy** as **All classes**, keep **Enable Git Flow** switched off, and click **Continue**. You can right-click the strategy document and select **‘Open Preview’** to review it.

![Unit Test Strategy and Select Tasks](images/image-55.png)

5. **Generate the Tests:** Bob gathers the test candidates and asks you to confirm the number of classes and batches. Click **Proceed with test generation**. Bob generates the tests package by package in parallel batches. Approve the tasks as they appear. When all batches are done, Bob runs all the unit tests of the project.

![Confirm Test Generation](images/image-56.png)

![Test Generation Completed](images/image-57.png)

6. **Review the Results:** Review the **Unit Test Results** and **Code Coverage** report. Bob reports the number of tests that pass and the instruction coverage achieved. Bob also shows a summary of all subtasks and the cost of each one.

![Unit Test Results and Code Coverage](images/image-59.png)

![Unit Testing Summary](images/image-58.png)

### 4.6 Create Kubernetes/OpenShift Deployment Artifacts 
1. Switch to previous bob window and check if you are in Agent Mode.
2. Start a new task and enter: *"I want to deploy this application to Openshift. The frontend code is at frontend/ and backend code is at backend/. Create required artifacts and scripts"*.

![OpenShift Prompt](images/image-60.png)

3. Approve the tasks as Bob works. When it finishes, Bob summarizes what it created. Review the generated artifacts and scripts. Typically these include:
   - Dockerfiles for the backend and frontend.
   - A web server configuration for the frontend.
   - OpenShift manifests: image streams, build configs, deployments, services, volume claim and route.
   - Deploy and undeploy scripts.
   - Documentation for the above.

   Images are built inside the cluster from your local source, so you don't need Docker or Podman locally.

![OpenShift Summary](images/image-61.png)

![OpenShift Files Explorer](images/image-62.png)


---

## 5. Key Modernization Achievements
* **Framework:** Struts 1.x → Spring Boot 3.x; JSP → React SPA (Carbon Design System).
* **API:** Documented REST API with Swagger UI.
* **Security:** Session-based authentication with Spring Security role enforcement and CSRF protection.
* **Quality:** Generated unit tests with code coverage measured by JaCoCo.
* **Cloud-Native:** Dockerfiles, OpenShift build configs, deployments, routes and deploy scripts.
* **Functional Parity:** All legacy features (transfers, history) preserved.

---
