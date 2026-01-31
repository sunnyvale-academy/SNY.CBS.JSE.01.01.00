# Lab 6-7: The Untrusted Provider (MUTABLE-7)

## Guideline Reference
**Guideline 6-7 / MUTABLE-7**: Treat output from untrusted object as input.

## Overview
Secure coding often focuses on validating inputs. However, Guideline 6-7 warns that **output from an untrusted object** must be treated with the same suspicion as input.

If you call a method on an untrusted object and it returns a mutable object (like a `List`, a `Date`, or a custom data class), the untrusted object might keep a reference to that object. This allows the untrusted source to modify your internal state "remotely" after it has been loaded into your class.

## Lab Materials
- `UserPrefs.java`: A mutable class for storing settings.
- `PrefsProvider.java`: Interface for providers (external plugins/modules).
- `PreferencesService.java`: A service that trusts returned preferences.
- `MaliciousProvider.java`: An exploit that retains a reference to modify state later.
- `PrefsLab.java`: PoC demonstrating the remote state modification.

## Instructions

1.  **Observe the Vulnerability**:
    ```bash
    javac *.java
    java PrefsLab
    ```
    Note how the `PreferencesService` has its "internal" theme changed to `MALICIOUS-DARK-WEB-MODE` by the provider *after* loading was complete.

2.  **Analyze the Failure**:
    In `PreferencesService.java`, the loading logic is:
    ```java
    this.currentPrefs = provider.getPreferences(); // LEAK!
    ```
    The service now shares a mutable object with an untrusted source.

3.  **Inspect the Fix**:
    Check `SecurePreferencesService.java`. It performs a defensive copy:
    ```java
    UserPrefs output = provider.getPreferences();
    this.currentPrefs = new UserPrefs(output.getTheme(), output.isNotificationsEnabled()); // SECURE
    ```

## Key Takeaway
**Treat anything returned from an untrusted collaborator as untrusted input.** If it's a mutable object, copy it before storing it in your internal state.
