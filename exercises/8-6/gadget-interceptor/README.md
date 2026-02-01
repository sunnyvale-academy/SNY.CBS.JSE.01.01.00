# Exercise 8-6: The Gadget Interceptor (SERIAL-6)

## Objective
Protect the `TaskProcessor` from malicious deserialization attacks by implementing a strict class whitelist using `ObjectInputFilter`.

## Scenario
The `TaskProcessor` is responsible for receiving and processing background tasks. These tasks are transferred over the network as serialized Java objects. 

An attacker has discovered that they can send any serializable class that is currently in the processor's classpath. By sending a `ShutdownGadget` object, they can trigger a simulated system shutdown whenever the processor attempts to read the object.

## Vulnerability
Run the application to see the exploit in action:
```bash
javac *.java
java TaskProcessor
```
Notice that even if the processor doesn't explicitly look for a `ShutdownGadget` type, the code inside the gadget's `readObject` is executed during the `ois.readObject()` call.

## Task
Modify `TaskProcessor.java` to secure the `process(byte[] data)` method:
1.  Configure an `ObjectInputFilter` that only allows `CommonTask` and necessary core Java classes (like `String`, `Long`, etc.).
2.  Explicitly reject all other classes using the `!*` pattern.
3.  Set the filter on the `ObjectInputStream` before calling `readObject()`.

## Success Criteria
- The `TaskProcessor` should successfully process the `CommonTask`.
- The `TaskProcessor` should block the `ShutdownGadget` with an `InvalidClassException` (or similar filter-related exception).
- The `ShutdownGadget` payload message should **not** appear in the output when the filter is active.
