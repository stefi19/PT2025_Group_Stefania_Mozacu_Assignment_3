<p align="center">
  <h1 align="center">Orders Management Application</h1>
</p>

<p align="center">
  <a href="https://github.com/stefi19/PT2025_Group_Stefania_Mozacu_Assignment_3/actions">
    <img src="https://img.shields.io/badge/build-passing-FFB6C1?style=for-the-badge&logo=github" alt="Build Status" />
  </a>
  <img src="https://img.shields.io/badge/Java-17-9370DB?style=for-the-badge" alt="Java 17" />
  <img src="https://img.shields.io/badge/Platform-Cross--Platform-FFB6C1?style=for-the-badge" alt="Cross Platform" />
</p>

---

## 🔥 Table of Contents
1. [✨ Features](#-features)  
2. [🚀 Getting Started](#-getting-started)  
3. [💡 Usage](#-usage)  
4. [📐 Architecture & Design](#-architecture--design)  
5. [🗂 Project Structure](#-project-structure)  
6. [🤝 Contributing](#-contributing)  
7. [📬 Contact](#-contact)

---

## ✨ Features
- 🗄️ **Relational Persistence** – MySQL tables for Client, Product, Order (+ Log for Bills)  
- 📂 **Layered Architecture** – packages: `dataAccessLayer`, `businessLayer`, `model`, `presentation`  
- 🧾 **Immutable Bill** – generated as a Java record, stored read‐only in Log table  
- 🔍 **Reflection Utilities**  
  - Dynamic JTable headers & rows from any list of model objects  
  - Generic CRUD operations (except Log) via reflected SQL  
- 🧑‍💻 **GUI** – Swing windows for Clients, Products, Order creation with under-stock warnings  
- 🧵 **Streams & Lambdas** – used for collection processing & filtering  

---

## 🚀 Getting Started

### Prerequisites
- **Java** JDK 17+  
- **Maven** (or your preferred build tool)  
- **MySQL** server running locally (or update your JDBC URL)

### Installation & Running
```bash
# 1. Clone the GitHub mirror
git clone https://github.com/stefi19/PT2025_Group_Stefania_Mozacu_Assignment_3.git
cd PT2025_Group_Stefania_Mozacu_Assignment_3

# 2. Initialize database
#    - import SQL dump in `db/schema.sql`
#    - update `dataAccessLayer/DBConfig.java` if needed

# 3. Build and run
mvn clean package
java -jar target/OrdersManagementApp.jar
