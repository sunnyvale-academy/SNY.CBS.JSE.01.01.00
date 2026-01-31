# Lab 6-3: Safe Copies of Input Values (MUTABLE-3)

## Guideline Reference
**Guideline 6-3 / MUTABLE-3**: Create safe copies of mutable and subclassable input values.

## Overview
When a class receives mutable objects (like arrays, Collections, or Dates) as constructor or method arguments, it must create copies of these objects before storing them in its internal state. 

However, how you create those copies matters!

### The Vulnerabilities
1.  **Direct Assignment**: Storing a reference to an input array directly means the caller still has access to the internal data of your object.
2.  **The `clone()` Trap**: For classes that can be subclassed (like `java.util.Date`), calling `.clone()` is dangerous. A malicious subclass can override `.clone()` to return a reference to itself, making your "defensive copy" useless.

## Lab Materials
- `MutableMetadata.java`: Demonstrates the vulnerable implementation.
- `MaliciousDate.java`: A "trojan horse" subclass that sabotages the `clone()` method.
- `MetadataLab.java`: A program that exploits both vulnerabilities.
- `SecureMetadata.java`: The fixed, secure implementation.

## Instructions

1.  **Compile and Run the Lab**:
    ```bash
    javac *.java
    java MetadataLab
    ```

2.  **Analyze the Output**:
    - Observe how the `tags` array in `MutableMetadata` changes when the original array is modified.
    - Observe how the `timestamp` changes even though `MutableMetadata` called `.clone()`.

3.  **Inspect the Fixes**:
    Read `SecureMetadata.java`. Note how:
    - The array is copied using `baseArray.clone()` (or `Arrays.copyOf`).
    - The `Date` is copied by creating a `new Date(untrustedDate.getTime())`. This "unboxes" the data into a trusted type, bypassing any malicious `clone()` implementation.

## Key Takeaway
For **defensive copying** from untrusted sources:
- **Always copy** mutable inputs.
- **Do not use `clone()`** on types that can be subclassed. Use a copy constructor or a factory method instead.
