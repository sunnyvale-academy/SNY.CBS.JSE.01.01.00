# Exercise 0-4: Secure Document Access (Trust Boundaries)

## Guideline 0-4: Establish trust boundaries

Any data originating from outside an application's trust boundary must be sanitized and validated before it is used. Conversely, any data containing sensitive information must be sanitized before it is passed outside a trust boundary.

### The Vulnerability: Insecure Direct Object Reference (IDOR)

In this exercise, we explore a trust boundary violation at the **identity and authority level**. 

A common mistake is to trust that any identifier (like a `documentId` or `accountNumber`) provided by a user is one they are authorized to access. When an application fetches a resource based on a user-provided ID without verifying if the user has the right to access that specific resource, it is a Trust Boundary violation. The application is "trusting" the client's choice of ID.

### Instructions

1.  Review the code in `VulnerableDocumentService.java`.
2.  Observe how the `getDocument` method takes a `userId` and a `documentId`, but uses the `documentId` directly to fetch the document without checking if it belongs to the `userId`.
3.  Implement a secure version of this service that:
    *   Defines a clear trust boundary.
    *   Validates that the requested document belongs to the requesting user *before* returning any data.
    *   Throws a `SecurityException` if the boundary is violated.

### Files
- [VulnerableDocumentService.java](VulnerableDocumentService.java)
