
# WolfParking Management System

This project is a robust SQL-based Relational Database Management System (RDBMS) for managing university parking operations. Designed with flexibility, efficiency, and scalability in mind, this system can handle a range of user types and vehicle categories. The RDBMS provides functionality for automated permit allocation, citation tracking, and enforcement.

## 🚀 Features

- **User & Vehicle Categories**: Adapts to multiple user roles (students, faculty, staff), driver types (Handicapped), and various vehicle types (EV, Fuel) for flexible permit allocation.
- **Automated Permit Allocation**: Efficiently assigns parking permits based on predefined user and vehicle rules, streamlining the management of parking permissions.
- **Citation Tracking & Enforcement**: Tracks violations and manages citations, with built-in enforcement protocols to ensure compliance.
- **Optimized Query Performance**: Efficient E/R and relational models for complex joins and queries.
- **ACID-Compliant Transactions**: Ensures data consistency, even under high-traffic conditions, by adhering to atomic, consistent, isolated, and durable (ACID) principles.
- **Boyce-Codd Normalization**: The database schema is normalized up to Boyce-Codd standards, reducing redundancy and improving operational efficiency.

## 🛠️ Technologies Used

- **SQL**: Core language for querying and managing data.
- **E/R Modeling**: Efficient entity-relationship model design for optimal relational mapping.
- **Boyce-Codd Normalization**: Database schema normalization up to BCNF.
- **ACID Transactions**: Provides strong consistency for all database operations.

## ⚙️ System Overview

1. **Permit Allocation**: Dynamically assigns permits based on user and vehicle criteria.
2. **Citation Management**: Logs and tracks citations for violations, with escalation policies.
3. **Data Models**:
   - **User**: Stores information on individuals eligible for permits.
   - **Vehicle**: Classifies vehicle details and associates them with users.
   - **Permit**: Manages issued permits with start and end dates.
   - **Citation**: Tracks violations and enforces university parking policies.

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request with your improvements.
