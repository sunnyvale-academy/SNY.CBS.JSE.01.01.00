# Exercise 6-3: The Schedule Hijack (MUTABLE-3)

## The Scenario
"SecureCorp" uses a software to manage executive meetings. However, some board members have noticed "ghost attendees" in their private meetings and meetings being rescheduled to the 70s without warning.

An audit reveals that the `Meeting` class is not correctly protecting its internal state from the inputs it receives.

## Your Task
Secure the `Meeting.java` class by following **Guideline 6-3 / MUTABLE-3: Create safe copies of mutable and subclassable input values**.

1.  **Analyze the vulnerability**: 
    - Identify the two places in the `Meeting` constructor where internal state is exposed to the caller.
    - Understand why `startTime.clone()` is failing to protect the object when a `MaliciousDate` is provided.

2.  **Apply the Fixes**:
    - Perform a defensive copy of the `participants` list (e.g., wrap it in a new `ArrayList`).
    - Perform a safe copy of the `startTime` by using a trusted constructor (`new Date(...)`) instead of `clone()`.

## Instructions

1.  **Run the exploit**:
    ```bash
    javac *.java
    java ScheduleHijack
    ```
    Observe how "The Hijacked Meeting State" contains the spy and the wrong date.

2.  **Fix Meeting.java**:
    Refactor the constructor to perform safe copies of both `startTime` and `participants`.

3.  **Verify**:
    Compile and run `ScheduleHijack` again. The "Hijacked Meeting State" should now be identical to the "Original Scheduled Meeting".
