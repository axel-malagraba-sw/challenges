package com.smallworldfs.tasklist.cli.command.match;

import com.smallworldfs.tasklist.cli.command.CommandLine;

public record IsEqualToCommandMatcher(String command) implements CommandMatcher {

    @Override
    public boolean matches(CommandLine commandLine) {
        return commandLine.raw().equals(command);
    }
}
