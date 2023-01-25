package com.smallworldfs.tasklist.task.crud;

import com.smallworldfs.tasklist.cli.command.Command;
import com.smallworldfs.tasklist.cli.io.Arguments;
import com.smallworldfs.tasklist.cli.io.Output;
import com.smallworldfs.tasklist.task.Project;
import com.smallworldfs.tasklist.task.ProjectRegistry;
import com.smallworldfs.tasklist.task.Task;
import java.util.Optional;

public class AddCommand implements Command {

    private long lastId = 0;

    private final ProjectRegistry registry = ProjectRegistry.getInstance();

    @Override
    public void run(Arguments arguments, Output output) {
        String[] subcommandRest = arguments.getArgumentString().split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            addProject(subcommandRest[1]);
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split(" ", 2);
            addTask(projectTask[0], projectTask[1], output);
        }
    }

    private void addProject(String name) {
        registry.createProject(name);
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
        return "add";
    }
}
