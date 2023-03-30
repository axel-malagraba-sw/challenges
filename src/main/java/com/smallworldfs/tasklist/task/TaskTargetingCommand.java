package com.smallworldfs.tasklist.task;

import com.smallworldfs.tasklist.cli.command.Command;
import com.smallworldfs.tasklist.project.Project;
import com.smallworldfs.tasklist.project.ProjectRegistry;
import java.util.Collection;
import java.util.function.Consumer;

public abstract class TaskTargetingCommand<T> implements Command<T> {

    protected ProjectRegistry registry = ProjectRegistry.getInstance();

    protected void runOnTask(Consumer<Task> consumer, String id) {
        consumer.accept(findTaskById(id));
    }

    protected Task findTaskById(String id) {
        return registry.getAll()
                .stream()
                .map(Project::getTasks)
                .flatMap(Collection::stream)
                .filter(task -> id.equals(task.getId()))
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException(id));
    }
}
