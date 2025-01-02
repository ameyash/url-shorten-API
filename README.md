
# Spring Boot Application with PostgreSQL

This demonstrates how to run a Spring Boot application with PostgreSQL and how to import a `.sql` database file into PostgreSQL.

## Prerequisites

Before you begin, ensure that you have the following installed on your system:

- [Java 11+](https://adoptopenjdk.net/)
- [Maven](https://maven.apache.org/)
- [PostgreSQL](https://www.postgresql.org/)

## Step 1: Clone the Repository

First, clone the repository to your local machine:

```git clone
git clone https://github.com/ameyash/url-shorten-API.git
cd url-shorten-API
```

## Step 2: Set Up PostgreSQL

### 2.1 Create a PostgreSQL Database

Log into your PostgreSQL shell or use a GUI tool like pgAdmin to create a database for the Spring Boot application.

```sql
CREATE DATABASE url-shortener;
```

### 2.2 Import the Database Schema

Download the `.sql` file from DB folder, you can import it as follows:

```bash
psql -U postgres -d url-shortener -f path/to/your/file.sql
```

Make sure to replace `path/to/your/file.sql` with the path to your SQL file.

## Step 3: Run the Spring Boot Application

You can now run your Spring Boot application by importing it into Eclipse tool.

## Step 4: Verify the Application

After the application starts, you should see application starts at `localhost:8080` message in the console.

## Troubleshooting

- If you encounter connection issues to PostgreSQL, ensure your PostgreSQL server is running and the credentials are correct.
- Make sure the `url-shortener` database exists and is populated with the appropriate schema/data.

---

This `README.md` file provides the basic steps for setting up a Spring Boot project with a PostgreSQL database and importing a `.sql` file into PostgreSQL.
