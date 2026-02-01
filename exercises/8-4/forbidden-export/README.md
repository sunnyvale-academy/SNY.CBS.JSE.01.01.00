# Exercise 8-4: The Forbidden Export (SERIAL-4)

## Objective
Secure the `SensitiveReport` class by duplicating the role-based access checks in the serialization methods.

## Scenario
The company uses a role-based access control (RBAC) system. The `SensitiveReport` class correctly checks that only users with the `ANALYST` role can create a new report. However, once a report exists, it can be "exported" (serialized) and "imported" (deserialized) by anyone, bypassing the role check.

## Vulnerability
Run the application to see the bypass:
```bash
javac *.java
java ReportApp
```
Notice that even when the role is set to `GUEST`, the application is able to deserialize and read the report data.

## Task
Modify `SensitiveReport.java` to:
1.  Implement `writeObject(ObjectOutputStream out)` and perform a role check before calling `out.defaultWriteObject()`.
2.  Implement `readObject(ObjectInputStream in)` and perform a role check after calling `in.defaultReadObject()`.

## Success Criteria
- The `ReportApp` should output: `RESULT: Action blocked as expected! Access Denied: Required role 'ANALYST', but current role is 'GUEST'.`
- Both exporting and importing should be protected. peace
