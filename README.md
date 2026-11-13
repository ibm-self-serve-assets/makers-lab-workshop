# IBM Bob: Java Modernization – Struts To Spring Boot Lab


### Where It Began

ABC Bank's netbanking application has quietly processed customer transactions for over two decades. Built in the early 2000s on Apache Struts 1.x and Java 8, it was solid for its time. But the original developers are long gone, the documentation was never written, and the codebase has grown into something nobody fully understands anymore. Today's team maintains it the only way they can — by reading through JSP scriptlets and XML config files, hoping nothing breaks.

This is not an edge case. It is the reality for thousands of enterprise applications still running in production today.

### Why It Has to Change

The application has reached a tipping point where staying still costs more than moving forward.

**It's a security liability.** Struts 1.x has known, unpatched CVEs. Java 8 no longer receives free security updates. Every dependency the application carries is a potential breach waiting to happen. For a banking application handling real customer money, this is not a theoretical risk — it is an active exposure.

**It's a drain on the team.** There are no unit tests. No documentation. Every change requires weeks of reverse engineering just to understand what might break. Finding developers who still know Struts is increasingly difficult and expensive. The cost of maintaining this application grows every year while its value stays the same.

**It's blocking the future.** The bank is moving to the cloud. This application, packaged as a WAR file with a file-based SQLite database, cannot go there. It cannot be containerized, cannot auto-scale, and cannot plug into modern CI/CD pipelines. Every new feature the business wants is blocked before it starts.

### Why Not Just Rewrite It?

Traditional rewrites take years, cost millions, and routinely fail to deliver. Outsourcing modernization to a consultancy means months of knowledge transfer, opaque timelines, and a result the internal team doesn't understand or own. The risk of losing business logic during a manual migration is high — and in banking, losing business logic means losing money.

### Why IBM Bob Changes the Equation

IBM Bob approaches this differently. Instead of replacing human judgment, it augments it — working alongside developers at every step of the modernization journey.

**It understands the code before touching it.** Bob reverse-engineers the undocumented legacy application, generating architecture diagrams, ER diagrams, sequence diagrams, and written documentation — in minutes, not weeks. The team finally knows what they own.

**It modernizes with precision, not guesswork.** Bob uses OpenRewrite recipes to upgrade Java versions automatically, catching and fixing compilation issues along the way. It doesn't just migrate — it validates, builds, and confirms success before moving on.

**It rebuilds the entire stack, end to end.** From a Struts backend to a Spring Boot REST API with Swagger documentation. From JSP pages to a React frontend with IBM's Carbon Design System. Bob handles both sides of the transformation, with security best practices (Spring Security, CSRF protection) built in.

**It generates tests, not just code.** Bob adds JaCoCo coverage measurement and generates a full unit test suite for the new backend — so the modernized application ships with a safety net that the original never had.

**It prepares for deployment.** Bob generates Dockerfiles, OpenShift manifests, build configs, and deploy scripts — taking the application from a WAR file on a local server to a cloud-native workload ready for OpenShift or Kubernetes.

**All of this preserves functional parity.** Every feature the original application had — account balances, fund transfers, transaction history — works in the modernized version. Nothing customers rely on is lost.

| Before | After |
|---|---|
| Java 8, Struts 1.x (both EOL) | Java 17, Spring Boot 3.x |
| JSP with scriptlets | React SPA (Carbon Design System) |
| No documentation | Architecture, ER & sequence diagrams |
| No tests, no coverage | JaCoCo-measured unit test suite |
| WAR on app server | Docker + OpenShift / Kubernetes ready |
| Months to understand a change | Documented, testable, fast to iterate |

Other tools automate parts of this journey. IBM Bob automates the whole journey — with an AI agent that reasons about your specific codebase, asks the right questions, makes design decisions transparently, and hands you an application your team can own and evolve.

---

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
2. Run the legacy application
3. Reverse engineering – understand the legacy application
4. Java version upgrade
5. Full application modernization
6. Generate unit tests
7. Create Kubernetes/OpenShift deployment artifacts

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
