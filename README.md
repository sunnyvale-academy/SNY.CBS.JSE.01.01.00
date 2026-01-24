# Java secure coding

To minimize the likelihood of security vulnerabilities caused by programmer error, Java developers should adhere to recommended coding rules available at https://www.oracle.com/java/technologies/javase/seccodeguide.html. 

This repo contains labs, PoCs, experiments and exploits, related to the topics listed in Oracle's Java coding guidelines. 


- [Lab 0-1](labs/0-1/api-design/README.md) / FUNDAMENTALS-1: Design APIs to avoid security concerns
- [Lab 0-3](labs/0-3/restrict-privileges/README.md) / FUNDAMENTALS-3: Restrict privileges
- [Lab 0-4](labs/0-4/trust-boundary/README.md) / FUNDAMENTALS-4: Establish trust boundaries
- [Lab 1-1](labs/1-1/zip-bomb/README.md) / DOS-1: Beware of activities that may use disproportionate resources
- [Lab 1-2](labs/1-2/resource-leak/README.md) / DOS-2: Release resources in all cases
- [Lab 1-3](labs/1-3/integer-overflow/README.md) / DOS-3: Resource limit checks should not suffer from integer overflow
- [Lab 1-4 (Robust Handling)](labs/1-4/robust-exception-handling/README.md) / DOS-4: Implement Robust Error/Exceptions handling for services
- [Lab 1-4 (Uncaught Handler)](labs/1-4/uncaught-exception-handler/README.md) / DOS-4: Use Thread.UncaughtExceptionHandler for global safety nets
- [Lab 1-5](labs/1-5/hash-collision/README.md) / DOS-5: Avoid using user input as hash keys (Hash Collision DoS)
- [Lab 2-1](labs/2-1/exception-leak/README.md) / CONFIDENTIAL-1: Purge sensitive information from exceptions
- [Lab 2-2](labs/2-2/shadow-logs/README.md) / CONFIDENTIAL-2: Do not log sensitive information
- [Lab 2-3](labs/2-3/memory-zeroing/README.md) / CONFIDENTIAL-3: Purge sensitive information from memory after use
- [Lab 3-1](labs/3-1/valid-formatting/README.md) / INJECT-1: Generate valid formatting
- [Lab 3-2](labs/3-2/sql-injection/README.md) / INJECT-2: Avoid dynamic SQL
- [Lab 3-3](labs/3-3/contextual-injection/README.md) / INJECT-3: XML and HTML generation requires care
- [Lab 3-4](labs/3-4/command-injection/README.md) / INJECT-4: Avoid untrusted data on the command line
- [Lab 3-5](labs/3-5/xml-inclusion/README.md) / INJECT-5: Restrict XML inclusion
- [Lab 3-6](labs/3-6/bmp-icc-injection/README.md) / INJECT-6: Care with BMP files
- [Lab 3-7](labs/3-7/swing-html-injection/README.md) / INJECT-7: Disable HTML display in Swing components
- [Lab 3-8](labs/3-8/untrusted-code/README.md) / INJECT-8: Take care interpreting untrusted code
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
- [Exercise 1-2 (Lock Release)](exercises/1-2/lock-release/README.md)
- [Exercise 1-3 (Vulnerable File Manager)](exercises/1-3/vulnerable-file-manager/README.md)
- [Exercise 1-4 (Robust Payment Service)](exercises/1-4/payment-service/README.md)
- [Exercise 1-5 (Metadata Store Protection)](exercises/1-5/metadata-store/README.md)
- [Exercise 2-1 (Ghost Paths)](exercises/2-1/ghost-paths/README.md)
- [Exercise 2-2 (Masked Transactions)](exercises/2-2/masked-transactions/README.md)
- [Exercise 2-3 (The Vanishing Key)](exercises/2-3/vanishing-key/README.md)
- [Exercise 3-1 (The Broken Badge)](exercises/3-1/broken-badge/README.md)
- [Exercise 3-2 (The High Score Heist)](exercises/3-2/high-score-heist/README.md)
- [Exercise 3-3 (The Profile Prank)](exercises/3-3/profile-prank/README.md)
- [Exercise 3-4 (The Silent Command)](exercises/3-4/silent-command/README.md)
- [Exercise 3-5: The XML Heist](file:///Users/denismaggiorotto/My%20Drive/Documents/Sunnyvale/Academy/Java_secure_coding/repos/exercises/3-5/xml-heist/) ([Solution](file:///Users/denismaggiorotto/My%20Drive/Documents/Sunnyvale/Academy/Java_secure_coding/repos/solutions/3-5/xml-heist/))
- [Exercise 3-6: Hack the Gallery](file:///Users/denismaggiorotto/My%20Drive/Documents/Sunnyvale/Academy/Java_secure_coding/repos/exercises/3-6/hack-the-gallery/) ([Solution](file:///Users/denismaggiorotto/My%20Drive/Documents/Sunnyvale/Academy/Java_secure_coding/repos/solutions/3-6/hack-the-gallery/))
- [Exercise 3-7: The Invisible Form](file:///Users/denismaggiorotto/My%20Drive/Documents/Sunnyvale/Academy/Java_secure_coding/repos/exercises/3-7/invisible-form/) ([Solution](file:///Users/denismaggiorotto/My%20Drive/Documents/Sunnyvale/Academy/Java_secure_coding/repos/solutions/3-7/invisible-form/))
- [Exercise 3-8: The XSLT Trap](file:///Users/denismaggiorotto/My%20Drive/Documents/Sunnyvale/Academy/Java_secure_coding/repos/exercises/3-8/xslt-trap/) ([Solution](file:///Users/denismaggiorotto/My%20Drive/Documents/Sunnyvale/Academy/Java_secure_coding/repos/solutions/3-8/xslt-trap/))


References:

- [Oracle's Secure Coding Guidelines for Java SE](https://www.oracle.com/java/technologies/javase/seccodeguide.html)