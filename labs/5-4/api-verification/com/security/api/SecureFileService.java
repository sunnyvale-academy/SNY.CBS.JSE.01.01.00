package com.security.api;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Secure file service following Guideline 5-4.
 */
public class SecureFileService {
    private static final Path BASE_PATH = Paths.get("/app/data/storage/").toAbsolutePath();

    public void accessFile(String userInput) {
        System.out.println("[SECURE] Accessing file: " + userInput);

        // GUIDELINE 5-4 FIX:
        // 1. Create the object.
        // 2. Normalize it to resolve ".." and "."
        // 3. SECURELY CHECK THE RESULTING OBJECT, not the raw input.

        Path path = Paths.get(BASE_PATH.toString(), userInput);
        Path normalizedPath = path.normalize().toAbsolutePath();

        System.out.println("[SECURE] Normalized absolute path: " + normalizedPath);

        // Verify that the normalized path still starts with our sandbox directory.
        if (!normalizedPath.startsWith(BASE_PATH)) {
            System.out.println("[SECURITY ALERT] Unauthorized access attempt detected!");
            System.out.println("[SECURITY] Access denied for path outside sandbox.");
            return;
        }

        System.out.println("[SECURE] Access granted. (Simulation)");
    }
}
