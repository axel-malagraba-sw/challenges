package com.smallworldfs.tasklist.task;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class ProjectRegistryExtension implements BeforeEachCallback, ParameterResolver {

    private final ProjectRegistry registry = ProjectRegistry.getInstance();

    @Override
    public void beforeEach(ExtensionContext extensionContext) {
        registry.clear();
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return parameterContext.getParameter().getType().isAssignableFrom(Task.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        Task task = new Task(1, "test task");

        registry.createProject("test").addTask(task);

        return task;
    }
}
