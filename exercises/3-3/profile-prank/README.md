# Exercise 3-3: The Profile Prank

## Goal
Master context-aware escaping by exploiting and then fixing a contextual injection vulnerability in a CSS attribute, adhering to [Guideline 3-3: XML and HTML generation requires care](https://www.oracle.com/java/technologies/javase/seccodeguide.html#3-3).

## The Scenario
"SocialConnect" is a new social media platform. One of its features allows users to customize their profile header by choosing a "Favorite Color". This color is then applied to the profile page using a CSS `style` attribute.

## The Problem
The `VulnerableProfileSystem` performs basic HTML escaping for text (like `<` and `>`), but it fails to account for the context of a CSS attribute.

```java
return "<div style=\"background-color: " + userInput + ";\"> ... </div>";
```

An attacker can provide an input that closes the `background-color` property and injects new properties or even breaks out of the `style` attribute entirely.

## Tasks

### 1. Try to Hack (The Prank)
Run `ProfileApp` and try to "prank" the profile. 
- **The Goal**: Inject a "favorite color" that does more than just changing the background.
- **Payload Inspiration**: Think about how to close the `background-color` style and add a new one, or how to close the `style` attribute and add an `onmouseover` event handler.
- **Example Payload**: `blue; height: 500px; background-image: url('https://example.com/malicious.png')` or even richer XSS payloads.

### 2. The Patch (The Fix)
Refactor the system into `SecureProfileSystem.java`.
- **Requirement**: Implement strict **Input Validation** (allow-listing). Only allow a specific set of safe color names (e.g., "blue", "red", "green", "hex codes").
- **Requirement**: Alternatively, implement **Contextual Escaping** that handles all characters capable of breaking out of a CSS attribute context.

### 3. Verification
Update `ProfileApp` to use your secure system and confirm that the malicious "color" is now rejected or safely neutralized.

## Running the Exercise
1.  **Compile**:
    ```bash
    javac *.java
    ```
2.  **Run**:
    ```bash
    java ProfileApp
    ```
