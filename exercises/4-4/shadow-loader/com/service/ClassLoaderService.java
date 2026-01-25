package com.service;

/**
 * A service that provides utility methods for the system.
 * VULNERABILITY: This class exposes its internal ClassLoader.
 */
public class ClassLoaderService {
    private final ClassLoader loader;

    public ClassLoaderService() {
        // Obtains the class loader that loaded this class
        this.loader = this.getClass().getClassLoader();
    }

    /**
     * @return the internal ClassLoader
     * @deprecated This method is dangerous and exposes internal state.
     */
    public ClassLoader getLoader() {
        return loader;
    }

    public void logActivity(String message) {
        System.out.println("[SERVICE LOG] " + message);
    }
}
