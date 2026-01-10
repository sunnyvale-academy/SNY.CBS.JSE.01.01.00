# Java secure coding

To minimize the likelihood of security vulnerabilities caused by programmer error, Java developers should adhere to recommended coding rules available at https://www.oracle.com/java/technologies/javase/seccodeguide.html. 

This repo contains labs, PoCs, experiments and exploits, related to the topics listed in Oracle's Java coding guidelines. 


- [Lab 0-1](labs/0-1/api-design/README.md) / FUNDAMENTALS-1: Design APIs to avoid security concerns
- [Lab 0-4](labs/0-4/trust-boundary/README.md) / FUNDAMENTALS-4: Establish trust boundaries
- [Lab 1-1](labs/1-1/zip-bomb/README.md) / DOS-1: Beware of activities that may use disproportionate resources
- [Lab 1-2](labs/1-2/resource-leak/README.md) / DOS-2: Release resources in all cases
- [Lab 2-1](labs/2-1/exception-leak/README.md) / CONFIDENTIAL-1: Purge sensitive information from exceptions
- [Lab 3-2](labs/3-2/sql-injection/README.md) / INJECT-2: Avoid dynamic SQL
- [Lab 4-1](labs/4-1/accessibility/README.md) / ACCESS-1: Limit the accessibility of classes, interfaces, methods, and fields
- [Lab 5-1](labs/5-1/input-validation/README.md) / INPUT-1: Validate inputs
- [Lab 6-2](labs/6-2/defensive-copy/README.md) / MUTABLE-2: Create copies of mutable output values
- [Lab 7-3](labs/7-3/finalizer-attack/README.md) / OBJ-3: Defend against partially initialized instances
- [Lab 8-3](labs/8-3/deserialization/README.md) / SERIAL-3: View deserialization the same as object construction
- [Lab 9-3](labs/9-3/privileged-action/README.md) / ACCESS-3: Safely invoke doPrivileged

### Exercises
- [Exercise 0-1 (The boolean trap)](exercises/0-1/boolean-trap/README.md)
- [Exercise 0-4 (Secure Document Access)](exercises/0-4/secure-document-access/README.md)
- [Exercise 1-1 (Denial of Service - Billion Laughs)](exercises/1-1/billion-laughs/README.md)


References:

- [Oracle's Secure Coding Guidelines for Java SE](https://www.oracle.com/java/technologies/javase/seccodeguide.html)