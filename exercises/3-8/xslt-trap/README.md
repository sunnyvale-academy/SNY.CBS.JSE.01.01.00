# Exercise 3-8: The XSLT Trap

## Scenario
The "QuickReport" system allows users to generate custom reports. To give users maximum flexibility, the system allows them to upload their own XSLT (Extensible Stylesheet Language Transformations) files to define the report layout.

However, the default XSLT engine in Java (Xalan) often permits "extensions"—essentially allowing the XSLT to call into Java methods on the host system. A malicious user has uploaded an XSLT that tries to execute a system command to prove they can hack the server.

## Your Goal
1.  **Analyze**: Open `ReportGenerator.java`. It uses a standard `Transformer` to apply XSLT to XML data.
2.  **Exploit**: Run `ReportApp.java`. It simulates the processing of a malicious XSLT that calls `java.lang.Runtime.getRuntime().exec()`.
3.  **Confirm**: Observe the console output. Does the XSLT successfully execute the system command?
4.  **Fix**: Implement a secure version in `solutions/3-8/xslt-trap/SecureReportGenerator.java` that prevents any XSLT from calling host Java methods.

## The Malicious XSLT Snippet
The attacker uses a namespace declaration to map a prefix to a Java class:
```xml
<xsl:stylesheet version="1.0" 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:rt="http://xml.apache.org/xalan/java/java.lang.Runtime">
  <xsl:template match="/">
    <xsl:variable name="runtime" select="rt:getRuntime()"/>
    <xsl:variable name="proc" select="rt:exec($runtime, 'echo !!! RCE SUCCESSFUL !!!')"/>
  </xsl:template>
</xsl:stylesheet>
```

## Instructions
1.  Compile the exercise:
    ```bash
    javac *.java
    ```
2.  Run the application:
    ```bash
    java ReportApp
    ```

Observe if the "!!! RCE SUCCESSFUL !!!" message (or similar) appears in the console, indicating the XSLT engine executed the command.
