# Exercise 3-1: The Broken Badge

## Goal
Learn how to securely generate XML-based images (SVG) by ensuring all user input is correctly formatted and escaped, adhering to [Guideline 3-1: Generate valid formatting](https://www.oracle.com/java/technologies/javase/seccodeguide.html#3-1).

## The Scenario
Your company generates dynamic status badges for developers to display on their profile pages. These badges are generated as SVG (Scalable Vector Graphics) files, which are a form of XML.

## The Problem
The `VulnerableBadgeGenerator` uses simple string templates to build the SVG.

```java
return "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"200\" height=\"30\">\n" +
       "  <rect width=\"100%\" height=\"100%\" fill=\"#555\"/>\n" +
       "  <text x=\"10\" y=\"20\" fill=\"#fff\" font-family=\"Arial\">" + label + ": " + status + "</text>\n" +
       "</svg>";
```

An attacker can provide a `status` that contains structural characters (like `</text><rect fill="red" ... />`) to inject additional elements into the image, potentially defacing the badge or adding malicious content.

## Tasks
1.  **Exploit the Vulnerability**: Run `BadgeApp` to see how a malicious status can "break" the badge by injecting a large red rectangle that covers the entire image.
2.  **Refactor**: Create a `SecureBadgeGenerator.java` that:
    -   Uses a proper XML library (like DOM) to build the SVG structure.
    -   **OR** implements a robust `escapeXml` function to sanitize the `label` and `status` inputs before they are concatenated into the template.
3.  **Verify**: Run `BadgeApp` again (after updating it to use your secure generator) to confirm that the malicious status is now displayed as plain text and no longer injects structural elements.

## Running the Exercise
1.  **Compile**:
    ```bash
    javac *.java
    ```
2.  **Run**:
    ```bash
    java BadgeApp
    ```
