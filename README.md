# Java secure coding

To minimize the likelihood of security vulnerabilities caused by programmer error, Java developers should adhere to recommended coding rules available at https://www.oracle.com/java/technologies/javase/seccodeguide.html. 

This repo contains labs, PoCs, experiments and exploits, related to the topics listed in Oracle's Java coding guidelines. 


- [Lab 0-4](labs/0-4/README.md) / FUNDAMENTALS-4: Establish trust boundaries
- [Lab 1-1](labs/1-1/README.md) / DOS-1: Do not expose methods that use a large amount of resources
- [Lab 1-2](labs/1-2/README.md) / DOS-2: Release resources in all cases
- [Lab 2-1](labs/2-1/README.md) / CONFIDENTIAL-1: Purge sensitive information from exceptions
- [Lab 3-2](labs/3-2/README.md) / INJECT-2: Avoid dynamic SQL
- [Lab 4-1](labs/4-1/README.md) / ACCESS-1: Limit the accessibility of classes, interfaces, methods, and fields
- [Lab 5-1](labs/5-1/README.md) / INPUT-1: Validate inputs
- [Lab 6-2](labs/6-2/README.md) / MUTABLE-2: Create copies of mutable output values
- [Lab 7-3](labs/7-3/README.md) / OBJ-3: Defend against partially initialized instances
- [Lab 8-3](labs/8-3/README.md) / SERIAL-3: View deserialization the same as object construction
- [Lab 9-3](labs/9-3/README.md) / ACCESS-3: Safely invoke doPrivileged

### Exercises
- [Exercise 0-4 (Billion Laughs Attack)](exercises/0-4/README.md)


References:

- [Oracle's Secure Coding Guidelines for Java SE](https://www.oracle.com/java/technologies/javase/seccodeguide.html)