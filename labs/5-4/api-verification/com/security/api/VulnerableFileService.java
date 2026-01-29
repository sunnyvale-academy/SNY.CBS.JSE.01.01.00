package com.security.api;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Vulnerable file service that makes dangerous assumptions about API behavior.
 */
public class VulnerableFileService {
    private static final String BASE_DIR = "/app/data/storage/";

    public void accessFile(String userInput) {
        System.out.println("[VULNERABLE] Accessing file: " + userInput);

        // VULNERABILITY 1: Assumed Validation
        // The developer assumes Paths.get() will reject or sanitize ".." sequences.
        Path path = Paths.get(BASE_DIR, userInput);

        // VULNERABILITY 2: Using the wrong source of truth
        // The developer might dry-run a check on the string, but JNI/OS might interpret
        // the Path differently.
        // Here we just print the resulting path to show it points outside BASE_DIR.
        System.out.println("[VULNERABLE] Resolved path: " + path);

        if (path.toString().contains("..")) {
            // Even this check can be bypassed on some systems or if normalization happens
            // later.
            System.out.println("[VULNERABLE] Warning: Path contains '..'");
        }

        // In a real app, this would be: Files.readAllBytes(path);
        System.out.println("[VULNERABLE] Operation allowed (Simulation)");
    }
}
