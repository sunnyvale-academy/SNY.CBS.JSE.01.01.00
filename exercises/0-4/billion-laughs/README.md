# Exercise 0-4: Billion Laughs Attack (Trust Boundary)

## The Guideline (0-4 / FUNDAMENTALS-4)

The Java SE Security Coding Guidelines state: **"Establish trust boundaries."**

A trust boundary is any point where data transitions from an untrusted source (like an external user or a network service) into a trusted part of your application. When receiving structured data like XML, the parser itself is a critical trust boundary that must be configured to handle malicious input.

## The Vulnerability: Billion Laughs Attack

The "Billion Laughs" attack (also known as an XML Entity Expansion attack) is a type of Denial of Service (DoS). It leverages the ability of XML DTDs to define internal entities that can expand exponentially.

A small XML file can define a series of nested entities:
- `lol1` contains 10 `lol`s.
- `lol2` contains 10 `lol1`s (100 `lol`s).
- ...
- `lol9` contains 10 `lol8`s (1,000,000,000 `lol`s).

> [!NOTE]
> **Modern JDK Protection**: Most modern JDKs include a default limit (e.g., `jdk.xml.entityExpansionLimit = 2500`) to prevent this attack out-of-the-box. To "prove" the exploit in this lab, the `VulnerableXmlParser` explicitly relaxes these limits to simulate a vulnerable or misconfigured environment.

When a vulnerable parser tries to expand these entities, it consumes massive amounts of CPU and memory, often leading to a JVM crash or a system hang.

## The Exercise

In this folder, you will find:
1.  `VulnerableXmlParser.java`: A parser that uses default settings, allowing entity expansion.
2.  `BillionLaughsExploit.java`: A PoC that sends a small but exponentially expanding XML to the parser.

What you have to do:
1.  Implement the corresponding `SecureXmlParser.java` and configure it to parse XML rejecting DTDs to prevent the attack.
2.  Uncomment the "// Step 3: Test Secure Parser" block in `BillionLaughsExploit.java` to test your `SecureXmlParser.java` implementation.

### Observe the Results

When you run the exploit, you will see:
1.  **VulnerableXmlParser**: It will either "finish" (if the payload is moderate) or hit a JVM safety limit (e.g., `JAXP00010007` at 100,000 expansions). This shows that the vulnerable service **trusts the input enough to begin a costly expansion**, only being stopped by a generic system-level safety net.
2.  **SecureXmlParser**: It will fail immediately with an error like `DOCTYPE is disallowed`. This shows that the secure service **establishes a trust boundary**, rejecting the dangerous structure before it can consume any significant resources.

> [!TIP]
> **Relaxing Limits**: If you want to see a true "Billion Laughs" hang, you can try passing these JVM flags to the `java` command:
> `-Djdk.xml.entityExpansionLimit=0 -Djdk.xml.totalEntitySizeLimit=0`

## How to Run the Exercise

### 1. Compile
```bash
javac *.java ../../../solutions/1-1/billion-laughs/*.java
```

### 2. Run the Exploit
```bash
java BillionLaughsExploit
```

## Secure Solution

The most effective way to prevent Billion Laughs and XXE (XML External Entity) attacks is to disable DTD processing entirely:

```java
DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
```

If DTDs are required, you should enable secure processing and limit the uncompressed size and entity expansion counts.
