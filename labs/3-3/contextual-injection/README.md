# Lab 3-3: The Contextual Leak

## Goal
Understand how the context in which data is placed (e.g., HTML text content vs. HTML attribute) affects the required escaping strategy, as per [Guideline 3-3: XML and HTML generation requires care](https://www.oracle.com/java/technologies/javase/seccodeguide.html#3-3).

## The Guideline (3-3 / INJECT-3)
Simply escaping characters like `<` and `>` is often sufficient for data placed between HTML tags (text context). However, if the data is placed inside an HTML attribute (e.g., `value='...'`), an attacker can use quotes (`'`, `"`) to "break out" of the attribute and inject new attributes or event handlers (like `onmouseover`).

## The Vulnerability (VulnerableHtmlGenerator.java)
The `VulnerableHtmlGenerator` performs basic escaping of `<` and `>`, but it fails to escape double quotes (`"`).

```java
// Vulnerable if input is placed in a double-quoted attribute
return "<input type='text' value=\"" + escapeTextOnly(input) + "\">";
```

An attacker can provide an input like `" onmouseover="alert('Hacked')"` to inject a malicious event handler.

## The Solution (SecureHtmlGenerator.java)
The `SecureHtmlGenerator` performs **Contextual Escaping**. Since the data is being placed in a double-quoted attribute, it must escape double quotes (at a minimum) to prevent structural changes to the tag.

## Running the Lab
1.  **Compile**:
    ```bash
    javac *.java
    ```
2.  **Run**:
    ```bash
    java HtmlApp
    ```
    Observe how the vulnerable generator allows an attribute breakout, while the secure generator correctly escapes the quotes, keeping the input captured within the `value` attribute.
