# IBM Bob: Java Modernization – Struts To Spring Boot Lab

This repository contains a hands-on lab for modernizing a legacy Java application using IBM Bob.

## Getting Started

To perform the lab, please refer to the comprehensive lab guide:

📖 **[Lab Guide](lab-guide.md)**

## What You'll Learn

In this lab, you will:
- Experience IBM Bob features and capabilities
- Explore Bob's comprehensive SDLC capabilities with a legacy Java application modernization use-case
- Experience the capabilities of the IBM Bob Premium package for Java modernization

## Lab Overview

Take a **Legacy Struts 1.3** netbanking application (Java 8, JSP, SQLite) with no documentation and modernize it to a **Spring Boot 3.x + React** application:

- **Reverse Engineer** the legacy code to generate documentation, architecture, ER and UML diagrams
- **Upgrade** Java from 1.8 to 17 using the *Java Modernization* workflow
- **Modernize** from Struts to a Spring Boot backend and a React (Carbon Design System) frontend using the *UI Modernization* workflow
- **Generate unit tests** with JaCoCo coverage using the *Java Unit Testing* workflow (optional)
- **Generate** OpenShift/Kubernetes deployment artifacts and scripts (optional)

## Lab Steps

1. Import the project into the Bob workspace
2. Reverse engineering – understand the legacy application
3. Java version upgrade
4. Full application modernization
5. Generate unit tests (optional)
6. Create Kubernetes/OpenShift deployment artifacts (optional)

## Key Modernization Achievements

- **Framework:** Struts 1.x → Spring Boot 3.x; JSP → React SPA
- **API:** Documented REST API with Swagger UI
- **Security:** Session-based authentication with Spring Security and CSRF protection
- **Quality:** Generated unit tests with JaCoCo code coverage
- **Cloud-Native:** Dockerfiles, OpenShift build configs, deployments, routes and deploy scripts
- **Functional Parity:** All legacy features preserved

## Prerequisites

Before starting the lab, ensure you have:
1. IBM Bob IDE installed and configured
2. Access to the IBM Premium Package for Java Modernization
3. Around 100 BobCoins
4. Java 17 (JDK), Maven, SDKMAN, and Node.js 20+ with npm
5. PlantUML Markdown Preview extension installed in Bob IDE

> **Note:** Bob's output is not deterministic, so your results may differ from the screenshots in the guide.

## Quick Start

1. Open this project in IBM Bob IDE
2. Follow the step-by-step instructions in [lab-guide.md](lab-guide.md)
3. Complete the modernization journey from legacy to cloud-native


---
For detailed instructions, troubleshooting, and best practices, see the [Lab Guide](lab-guide.md).
