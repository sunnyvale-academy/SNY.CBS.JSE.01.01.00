# Java Secure Coding

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
- [Lab 3-9](labs/3-9/exceptional-floats/README.md) / INJECT-9: Prevent injection of exceptional floating point values
- [Lab 4-1](labs/4-1/limit-accessibility/README.md) / ACCESS-1: Limit the accessibility of classes, interfaces, methods, and fields
- [Lab 4-2](labs/4-2/modules-internal/README.md) / EXTEND-2: Use modules to hide internal packages
- [Lab 4-4](labs/4-4/classloader-exposure/README.md) / EXTEND-4: Limit exposure of ClassLoader instances
- [Lab 4-5](labs/4-5/limit-extensibility/README.md) / EXTEND-5: Limit the extensibility of classes and methods
- [Lab 4-6](labs/4-6/superclass-behavior/README.md) / EXTEND-6: Understand how a superclass can affect subclass behavior
- [Lab 5-1](labs/5-1/input-validation/README.md) / INPUT-1: Validate inputs
- [Lab 5-2](labs/5-2/untrusted-objects/README.md) / INPUT-2: Validate output from untrusted objects as input
- [Lab 5-3](labs/5-3/native-wrappers/README.md) / INPUT-3: Define wrappers around native methods
- [Lab 5-4](labs/5-4/api-verification/README.md) / INPUT-4: Verify API behavior related to input validation
- [Lab 6-1](labs/6-1/mutable-value-types/README.md) / MUTABLE-1: Prefer immutability for value types
- [Lab 6-2](labs/6-2/defensive-copy/README.md) / MUTABLE-2: Create copies of mutable output values
- [Lab 6-3](labs/6-3/safe-copying/README.md) / MUTABLE-3: Create safe copies of mutable and subclassable input values
- [Lab 6-4](labs/6-4/copy-support/README.md) / MUTABLE-4: Support copy functionality for a mutable class
- [Lab 6-5](labs/6-5/identity-trap/README.md) / MUTABLE-5: Do not trust identity equality when overridable on input reference objects
- [Lab 6-6](labs/6-6/leaky-callback/README.md) / MUTABLE-6: Treat passing input to untrusted object as output
- [Lab 6-7](labs/6-7/untrusted-provider/README.md) / MUTABLE-7: Treat output from untrusted object as input
- [Lab 6-8](labs/6-8/restricted-inventory/README.md) / MUTABLE-8: Define wrapper methods around modifiable internal state
- [Lab 6-9](labs/6-9/global-hijack/README.md) / MUTABLE-9: Make public static fields final
- [Lab 6-10](labs/6-10/mutable-constant/README.md) / MUTABLE-10: Ensure public static final field values are constants
- [Lab 6-11](labs/6-11/hijacked-counter/README.md) / MUTABLE-11: Do not expose mutable statics
- [Lab 6-12](labs/6-12/poisoned-list/README.md) / MUTABLE-12: Do not expose modifiable collections
- [Lab 7-1](labs/7-1/hidden-constructor/README.md) / OBJECT-1: Avoid exposing constructors of sensitive classes
- [Lab 7-3](labs/7-3/finalizer-attack/README.md) / OBJ-3: Defend against partially initialized instances
- [Lab 7-4](labs/7-4/constructor-trap/README.md) / OBJ-4: Prevent constructors from calling methods that can be overridden
- [Lab 7-5](labs/7-5/clone-bypass/README.md) / OBJ-5: Defend against cloning of non-final classes
- [Lab 8-1](labs/8-1/avoid-serialization/README.md) / SERIAL-1: Avoid serialization for security-sensitive classes
- [Lab 8-2](labs/8-2/guard-serialization/README.md) / SERIAL-2: Guard sensitive data during serialization
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
- [Exercise 3-5 (The XML Heist)](exercises/3-5/xml-heist/)
- [Exercise 3-6 (Hack the Gallery)](exercises/3-6/hack-the-gallery/)
- [Exercise 3-7 (The Invisible Form)](exercises/3-7/invisible-form/)
- [Exercise 3-8 (The XSLT Trap)](exercises/3-8/xslt-trap/)
- [Exercise 3-9 (The Infinite Bidding Wars)](exercises/3-9/infinite-bidding/)
- [Exercise 4-1 (The Hidden Discount Trick)](exercises/4-1/discount-trick/)
- [Exercise 4-2 (Module Encapsulation)](exercises/4-2/module-encapsulation/)
- [Exercise 4-4 (Shadow Loader)](exercises/4-4/shadow-loader/README.md)
- [Exercise 4-5 (Sealed Payment Gateway)](exercises/4-5/sealed-payment-gateway/README.md)
- [Exercise 4-6 (Redacting Logger)](exercises/4-6/redacting-logger/README.md)
- [Exercise 5-1 (The Magic Withdrawal)](exercises/5-1/bank-transfer/README.md)
- [Exercise 5-2 (The Discount Deceit)](exercises/5-2/discount-service/README.md)
- [Exercise 5-3 (The Buffer Boundary)](exercises/5-3/native-buffer/README.md)
- [Exercise 6-1 (The GPS Tamperer)](exercises/6-1/gps-tracker/README.md)
- [Exercise 6-2 (The Payroll Sabotage)](exercises/6-2/payroll-system/README.md)
- [Exercise 6-3 (The Schedule Hijack)](exercises/6-3/schedule-hijack/README.md)
- [Exercise 6-4 (The Config Experiment)](exercises/6-4/config-manager/README.md)
- [Exercise 6-5 (The Forged Ticket)](exercises/6-5/ticket-manager/README.md)
- [Exercise 6-6 (The Sabotaged Auditor)](exercises/6-6/audit-system/README.md)
- [Exercise 6-7 (The Hijacked Sensor)](exercises/6-7/hijacked-sensor/README.md)
- [Exercise 6-8 (The Bypassed RBAC)](exercises/6-8/role-manager/README.md)
- [Exercise 6-9 (The Master Key Injection)](exercises/6-9/master-key/README.md)
- [Exercise 6-10 (The Sabotaged Limit)](exercises/6-10/resource-limit/README.md)
- [Exercise 6-11 (The Maintenance Bypass)](exercises/6-11/maintenance-bypass/README.md)
- [Exercise 6-12 (The Bypassed Blacklist)](exercises/6-12/bypassed-blacklist/README.md)
- [Exercise 7-1 (The Unauthorized Vault Key)](exercises/7-1/vault-key-bypass/README.md)
- [Exercise 7-3 (The Ghost Transfer)](exercises/7-3/ghost-transfer/README.md)
- [Exercise 7-4 (The Broken Logger)](exercises/7-4/broken-logger/README.md)
- [Exercise 7-5 (The Identity Duplicator)](exercises/7-5/identity-duplicator/README.md)
- [Exercise 8-1 (The Leaking Token)](exercises/8-1/leaking-token/README.md)
- [Exercise 8-2 (The Exposed Credentials)](exercises/8-2/exposed-credentials/README.md)
- [Exercise 8-3 (The Impossible Range)](exercises/8-3/impossible-range/README.md)
- [Final Challenge (The Legacy Bank Heist)](exercises/final-challenge/legacy-bank/README.md)


References:

- [Oracle's Secure Coding Guidelines for Java SE](https://www.oracle.com/java/technologies/javase/seccodeguide.html)