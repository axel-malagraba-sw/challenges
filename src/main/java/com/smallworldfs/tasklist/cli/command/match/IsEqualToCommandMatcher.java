package com.smallworldfs.tasklist.cli.command.match;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IsEqualToCommandMatcher implements CommandMatcher {

    private final String command;


    @Override
    public boolean matches(String commandLine) {
        return commandLine.trim().equals(command);
    }
}
