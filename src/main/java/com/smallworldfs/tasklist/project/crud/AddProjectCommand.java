package com.smallworldfs.tasklist.project.crud;

import com.smallworldfs.tasklist.cli.command.Command;
import com.smallworldfs.tasklist.cli.command.arguments.ArgumentParser;
import com.smallworldfs.tasklist.cli.command.arguments.DefaultArgumentsParser;
import com.smallworldfs.tasklist.cli.command.match.CommandMatcher;
import com.smallworldfs.tasklist.cli.command.match.StartsWithCommandMatcher;
import com.smallworldfs.tasklist.cli.io.Arguments;
import com.smallworldfs.tasklist.cli.io.Output;
import com.smallworldfs.tasklist.project.ProjectRegistry;
import lombok.Getter;

public class AddProjectCommand implements Command<Arguments> {

    @Getter
    private final ArgumentParser<Arguments> argumentParser = new DefaultArgumentsParser();
    @Getter
    private final CommandMatcher matcher = new StartsWithCommandMatcher("add project");
    private final ProjectRegistry registry = ProjectRegistry.getInstance();


    @Override
    public void run(Arguments arguments, Output output) {
        addProject(arguments.getCommandLine().split(" ", 3)[2]);
    }

    private void addProject(String name) {
        registry.createProject(name);
    }

    @Override
    public String help() {
        return "add project <project name>";
    }
}
