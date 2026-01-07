# Java Secure Coding: Guideline 3-2 - Avoid Dynamic SQL

## The Guideline (3-2 / INJECT-2)

The Java SE Security Coding Guidelines state: **"Avoid dynamic SQL."**

Generating SQL statements dynamically by concatenating strings with user-provided data is one of the most common and dangerous security vulnerabilities. It allows an attacker to "inject" their own SQL commands into the query, potentially bypassing authentication, stealing data, or even deleting the entire database.

## The Vulnerability (VulnerableSearchService.java)

In `VulnerableSearchService.java`, the query is built using raw string concatenation:

```java
public void searchUser(String username) {
    // VULNERABILITY: Concatenating untrusted input directly into SQL
    String query = "SELECT * FROM users WHERE username = '" + username + "'";
    // ...
}
```

If a user provides a username like `bob`, the query is `SELECT * FROM users WHERE username = 'bob'`. However, if they provide something malicious, they can change the logic of the query.

## The Exploit (SQLInjectionExploit.java)

The `SQLInjectionExploit.java` demonstrates a classic "Always True" injection:

```java
String maliciousUser = "admin' OR '1'='1";
service.searchUser(maliciousUser);
```

The resulting query becomes:
`SELECT * FROM users WHERE username = 'admin' OR '1'='1'`

Because `'1'='1'` is always true, the `WHERE` clause matches every row in the `users` table, effectively returning all users instead of just one. An attacker could also use `;` to start a new command, like `; DROP TABLE users --`.

## The Secure Solution (SecureSearchService.java)

The only reliable way to prevent SQL Injection is to use **Parameterized Queries** (also known as Prepared Statements). This approach separates the SQL code from the data.

```java
public void searchUser(String username) {
    // SECURE: Use placeholders (?) for parameters
    String sql = "SELECT * FROM users WHERE username = ?";
    
    // In real JDBC code:
    // PreparedStatement pstmt = connection.prepareStatement(sql);
    // pstmt.setString(1, username);
}
```

When using `PreparedStatement`, the database driver ensures that the `username` is treated strictly as a data value, never as part of the SQL command itself. Any special characters like `'` or `;` are automatically escaped or handled safely.

## How to Run the Lab

### 1. Compile
```bash
javac *.java
```

### 2. Run the Exploit
```bash
java SQLInjectionExploit
```

Observe how the `Vulnerable Service` produces a compromised query string, while the `Secure Service` keeps the query template fixed and treats the input as a parameter.
