package com.smallworldfs.tasklist.project.crud;

import com.smallworldfs.tasklist.cli.command.Command;
import com.smallworldfs.tasklist.cli.command.arguments.ArgumentParser;
import com.smallworldfs.tasklist.cli.command.arguments.SingleStringArgumentParser;
import com.smallworldfs.tasklist.cli.command.match.CommandMatcher;
import com.smallworldfs.tasklist.cli.command.match.StartsWithCommandMatcher;
import com.smallworldfs.tasklist.cli.io.Output;
import com.smallworldfs.tasklist.project.ProjectRegistry;
import lombok.Getter;

public class AddProjectCommand implements Command<String> {

    @Getter
    private final ArgumentParser<String> argumentParser = new SingleStringArgumentParser(2, this::help);
    @Getter
    private final CommandMatcher matcher = new StartsWithCommandMatcher("add project");
    private final ProjectRegistry registry = ProjectRegistry.getInstance();

    @Override
    public void run(String projectName, Output output) {
        registry.createProject(projectName);
    }

    @Override
    public String help() {
        return "add project <project name>";
    }
}
