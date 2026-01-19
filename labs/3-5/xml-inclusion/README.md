# Lab 3-5: The XML Gateway

## Goal
Understand the risks of XML External Entity (XXE) vulnerabilities and learn how to securely configure XML parsers, following [Guideline 3-5: Restrict XML inclusion](https://www.oracle.com/java/technologies/javase/seccodeguide.html#3-5).

## The Guideline (3-5 / INJECT-5)
XML documents can contain "entities" that reference external resources (e.g., local files or network URLs). If a parser is configured to resolve these entities automatically, an attacker can provide a crafted XML that "includes" sensitive local files (like `/etc/passwd`) or triggers internal network requests.

## The Vulnerability (VulnerableXmlParser.java)
The `VulnerableXmlParser` uses a `DocumentBuilderFactory` with default settings. Many older Java XML parsers have DTD (Document Type Definition) processing enabled by default, which is the root cause of XXE.

```java
DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
DocumentBuilder builder = factory.newDocumentBuilder();
Document doc = builder.parse(new InputSource(new StringReader(xmlContent)));
```

An attacker can provide an XML like:
```xml
<?xml version="1.0"?>
<!DOCTYPE data [
  <!ENTITY xxe SYSTEM "file:///path/to/secret.txt">
]>
<user>
  <name>&xxe;</name>
</user>
```

## The Solution (SecureXmlParser.java)
The `SecureXmlParser` explicitly disables DTDs and external entities in the factory configuration.

```java
DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
// Disallow DTDs entirely
factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
// Or disable external entities specifically
factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
```

## Running the Lab
1.  **Compile**:
    ```bash
    javac *.java
    ```
2.  **Run**:
    ```bash
    java XmlInclusionApp
    ```
    Observe how the vulnerable version reads the "secret" file, while the secure version rejects the XML or ignores the entity.
