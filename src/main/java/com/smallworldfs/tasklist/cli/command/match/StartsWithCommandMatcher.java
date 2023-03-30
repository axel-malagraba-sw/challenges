package com.smallworldfs.tasklist.cli.command.match;

import com.smallworldfs.tasklist.cli.command.CommandLine;

public record StartsWithCommandMatcher(String prefix) implements CommandMatcher {

    @Override
    public boolean matches(CommandLine commandLine) {
        return commandLine.raw().startsWith(prefix);
    }
}
