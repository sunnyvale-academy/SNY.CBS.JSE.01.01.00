# Lab 3-1: Valid Formatting (XML Injection)

## Goal
Understand the risks of ad-hoc string concatenation for generating structured output and learn how to use dedicated libraries to ensure valid and secure formatting, adhering to [Guideline 3-1: Generate valid formatting](https://www.oracle.com/java/technologies/javase/seccodeguide.html#3-1).

## The Guideline (3-1 / INJECT-1)
Applications often generate structured data like XML, HTML, or JSON. If this data is built using simple string concatenation with untrusted input, an attacker can "inject" structural characters (like `<`, `>`, or `&`) to change the meaning of the document.

This is known as **Injection**. Guideline 3-1 recommends using well-tested libraries (or well tested custom code) that handle escaping and formatting automatically instead of building strings manually.

## The Vulnerability (VulnerableXmlGenerator.java)
The `VulnerableXmlGenerator` creates a user XML record by joining strings.

```java
public String createUserRecord(String username, String role) {
    // VULNERABILITY: Ad-hoc string concatenation
    return "<user>\n" +
           "  <username>" + username + "</username>\n" +
           "  <role>" + role + "</role>\n" +
           "</user>";
}
```

If a user provides a "username" like `admin</username><role>superadmin</role><username>attacker`, the resulting XML structure is corrupted and the attacker gains elevated privileges in the eyes of any system parsing this XML.

## The Secure Solution (SecureXmlGenerator.java)
The secure implementation uses standard Java XML APIs (`javax.xml.parsers` and `javax.xml.transform`) to build the document as an object model. These libraries automatically escape special characters, ensuring that the input is always treated as data, never as code/structure.

## How to Run the Lab

### 1. Compile
```bash
javac *.java
```

### 2. Run the Lab
```bash
java XmlApp
```

Observe how the **Vulnerable** generator allows the attacker to inject a new `<role>` tag, while the **Secure** generator safely escapes the malicious input.
