# Exercise 3-5: The XML Heist

## Goal
Discover and neutralize an XML External Entity (XXE) vulnerability in a document management system, following [Guideline 3-5: Restrict XML inclusion](https://www.oracle.com/java/technologies/javase/seccodeguide.html#3-5).

## The Scenario
"SecureDocs Inc." provides an administrative portal where employees can upload system reports in XML format. The system parses these reports to extract status information.

## The Problem
The developer used a standard XML parser with default settings. While it works for valid reports, it hasn't been hardened against malicious XML features that allow including external resources.

## Tasks

### 1. Try to Hack (The Password Leak)
A sensitive file named `db_passwords.txt` exists in the server's local directory. Your goal is to "heist" the contents of this file using the administrative portal's XML upload feature.

- **The Goal**: Capture the database passwords from `db_passwords.txt`.
- **The Challenge**: Craft a malicious XML document that forces the parser to include the contents of `db_passwords.txt` in its output.
- **Rules**: No hints this time! You need to research how XML entities can be used to reference external files.

### 2. The Patch (The Fix)
Refactor the system into `SecureDocManager.java`.
- **Requirement**: Configure the `DocumentBuilderFactory` to explicitly disallow DTD declarations or restrict external entity resolution.

### 3. Verification
Run the `HeistApp` and confirm that your secure implementation no longer leaks the file content and instead handles the malicious XML safely (e.g., by throwing an exception).

## Running the Exercise
1.  **Compile**:
    ```bash
    javac *.java
    ```
2.  **Run**:
    ```bash
    java HeistApp
    ```
