package com.plugin;

public class PluginManager {
    private final ClassLoader loader;

    public PluginManager() {
        this.loader = this.getClass().getClassLoader();
    }

    // VULNERABILITY: Exposing the ClassLoader instance
    public ClassLoader getLoader() {
        return loader;
    }

    public void runPluginTask(String taskName) {
        System.out.println("Executing plugin task: " + taskName);
    }
}
