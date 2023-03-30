package com.smallworldfs.tasklist.task.timeline;

import com.smallworldfs.tasklist.cli.command.Command;
import com.smallworldfs.tasklist.cli.io.Arguments;
import com.smallworldfs.tasklist.cli.io.Output;
import com.smallworldfs.tasklist.project.Project;
import com.smallworldfs.tasklist.project.ProjectRegistry;
import com.smallworldfs.tasklist.task.TaskWriter;
import java.util.List;

public class TodayCommand implements Command {

    protected ProjectRegistry registry = ProjectRegistry.getInstance();

    @Override
    public void run(Arguments arguments, Output output) {
        List<Project> projects = registry.getProjectsWithTasksDueToday();

        if (projects.isEmpty()) {
            output.writeln("There are no tasks due today.");
            return;
        }
        writeTasks(projects, output);
    }

    private void writeTasks(List<Project> projects, Output output) {
        new TaskWriter(output, projects).write(Project::getTasksDueToday);
    }

    @Override
    public String name() {
        return "today";
    }
}
