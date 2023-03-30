package com.smallworldfs.tasklist.task.timeline;

import com.smallworldfs.tasklist.cli.command.NoArgumentsCommand;
import com.smallworldfs.tasklist.cli.command.match.CommandMatcher;
import com.smallworldfs.tasklist.cli.command.match.IsEqualToCommandMatcher;
import com.smallworldfs.tasklist.cli.io.Output;
import com.smallworldfs.tasklist.project.Project;
import com.smallworldfs.tasklist.project.ProjectRegistry;
import com.smallworldfs.tasklist.task.TaskWriter;
import java.util.List;
import lombok.Getter;

public class TodayCommand extends NoArgumentsCommand {

    @Getter
    private final CommandMatcher matcher = new IsEqualToCommandMatcher("today");
    private final ProjectRegistry registry = ProjectRegistry.getInstance();

    @Override
    public void run(Output output) {
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
    public String help() {
        return "today";
    }
}
