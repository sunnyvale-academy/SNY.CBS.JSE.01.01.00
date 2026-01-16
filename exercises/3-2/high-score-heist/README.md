# Exercise 3-2: The High Score Heist

## Goal
Discover how dynamic SQL leads to injection vulnerabilities in a gamified scenario and implement a robust fix using `PreparedStatement`, adhering to [Guideline 3-2: Avoid dynamic SQL](https://www.oracle.com/java/technologies/javase/seccodeguide.html#3-2).

## The Scenario
Welcome to the "Retro Arcade"! You've been tasked with auditing the `High Score Leaderboard` system. Rumor has it that some "cheaters" have been manipulating their tags to climb the ranks without even playing.

## The Problem
The `VulnerableLeaderboard.java` uses simple string concatenation to save new scores.

```java
String query = "UPDATE scores SET score = " + score + " WHERE tag = '" + playerTag + "'";
```

If a player provides a tag like `PRO_PLAYER' OR '1'='1`, they might unintentionally (or intentionally!) update every single record in the database.

## Tasks

### 1. The Heist (Discovery & Exploit)
Run `GameApp` and try to "steal" the top spot. 
- **Hint**: Look at how the `playerTag` is used in the `saveScore` method.
- **Goal**: Inject a SQL payload into the `Player Tag` prompt that changes the structure of the query to update more than just your own score.

### 2. The Patch (The Fix)
Refactor the code into `SecureLeaderboard.java`.
- **Requirement**: Use `java.sql.PreparedStatement` to separate the SQL command from the user data.
- **Requirement**: Ensure that user input can **never** change the logic of the SQL statement.

### 3. Verification
Update `GameApp` to use your `SecureLeaderboard` and confirm that the heist payload no longer works; it should simply be treated as a literal (albeit strange) player tag.

## Running the Exercise
1.  **Compile**:
    ```bash
    javac *.java
    ```
2.  **Run**:
    ```bash
    java GameApp
    ```
