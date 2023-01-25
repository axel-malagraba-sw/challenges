package com.smallworldfs.tasklist.task;

import com.smallworldfs.tasklist.cli.io.Output;
import java.util.List;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TaskWriter {

    private static final String TASK_TEMPLATE = "    [%c] %d: %s%s";

    private final Output output;
    private final List<Project> projects;

    public void write() {
        write(Project::getTasks);
    }

    public void write(Function<Project, List<Task>> taskProvider) {
        for (Project project : projects) {
            output.writeln(project.getName());
            writeTasks(project, taskProvider);
            output.newLine();
        }
    }

    private void writeTasks(Project project, Function<Project, List<Task>> taskProvider) {
        for (Task task : taskProvider.apply(project)) {
            output.writeFormatted(TASK_TEMPLATE,
                    checkbox(task),
                    task.getId(),
                    task.getDescription(),
                    formatDeadline(task));
        }
    }

    private String formatDeadline(Task task) {
        if (task.hasDeadline()) {
            return " (Due " + task.getDeadline() + ")";
        }
        return "";
    }

    private char checkbox(Task task) {
        return task.isDone() ? 'x' : ' ';
    }
}
