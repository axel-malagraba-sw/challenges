package com.smallworldfs.tasklist.task.crud;

import com.smallworldfs.tasklist.cli.command.Command;
import com.smallworldfs.tasklist.cli.io.Arguments;
import com.smallworldfs.tasklist.cli.io.Output;
import com.smallworldfs.tasklist.project.Project;
import com.smallworldfs.tasklist.project.ProjectRegistry;
import com.smallworldfs.tasklist.task.Task;
import java.util.Optional;

public class AddTaskCommand implements Command {

    private long lastId = 0;

    private final ProjectRegistry registry = ProjectRegistry.getInstance();

    @Override
    public void run(Arguments arguments, Output output) {
        String[] subcommandRest = arguments.getCommandLine().split(" ", 4);
        addTask(subcommandRest[2], subcommandRest[3], output);
    }

    private void addTask(String projectName, String description, Output output) {
        Optional<Project> storedProject = registry.find(projectName);

        if (storedProject.isEmpty()) {
            output.writeFormatted("Could not find a project with the name \"%s\".", projectName);
            return;
        }
        storedProject.get().addTask(new Task(nextId(), description));
    }

    private long nextId() {
        return ++lastId;
    }

    @Override
    public String name() {
        return "add task";
    }
}