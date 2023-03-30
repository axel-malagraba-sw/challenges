package com.smallworldfs.tasklist.cli.command.match;

import com.smallworldfs.tasklist.cli.command.CommandLine;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IsEqualToCommandMatcher implements CommandMatcher {

    private final String command;


    @Override
    public boolean matches(CommandLine commandLine) {
        return commandLine.raw().equals(command);
    }
}
