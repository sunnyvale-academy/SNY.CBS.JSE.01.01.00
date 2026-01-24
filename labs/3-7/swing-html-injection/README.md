# Lab 3-7: Swing HTML Injection (Disable HTML in Swing)

## Goal
Understand the security risks of automatic HTML rendering in Swing components and learn how to disable it for untrusted input, following [Guideline 3-7: Disable HTML display in Swing components](https://www.oracle.com/java/technologies/javase/seccodeguide.html#3-7).

## The Guideline (3-7 / INJECT-7)
Many Swing components (like `JLabel`, `JButton`, `JMenuItem`, etc.) automatically render their text as HTML if it starts with the `<html>` tag.

### Why is this a risk?
If an application displays untrusted input (e.g., a username or a message) in a Swing component, an attacker can:
1.  **Spoof the UI**: Change fonts, colors, or create fake buttons/forms to trick the user.
2.  **Trigger Network Requests**: Use `<img>` tags to trigger outbound requests (potentially used for tracking or CSRF).
3.  **Denial of Service**: Inject complex or deeply nested HTML that consumes excessive memory or CPU during rendering.

## Lab Files
- `VulnerableSwingApp.java`: Demonstrates the vulnerability where untrusted input is rendered as HTML.
- `SecureSwingApp.java`: Shows how to disable HTML rendering using the `html.disable` property.

## Instructions
1.  **Compile the apps**:
    ```bash
    javac *.java
    ```
2.  **Run the Vulnerable App**:
    ```bash
    java VulnerableSwingApp
    ```
    - Enter a malicious payload: `<html><font color='red' size='10'>SYSTEM HACKED</font></html>`.
    - Or an image payload: `<html><img src="https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png"></html>`.
    - Notice how the label renders the HTML instead of showing the literal tags. In the image case, it triggers a network request!
3.  **Run the Secure App**:
    ```bash
    java SecureSwingApp
    ```
    - Enter the same payload.
    - Notice that the labels now display the literal text, neutralizing the injection.

## Secure Coding Fix
To disable HTML rendering for a specific component, set the `html.disable` client property to `Boolean.TRUE`:
```java
label.putClientProperty("html.disable", Boolean.TRUE);
```
Alternatively, for components that don't support `putClientProperty` or to be more defensive, ensure the input is properly escaped or does not start with `<html>`.
