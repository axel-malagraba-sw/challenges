package com.smallworldfs.tasklist.cli.command;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StartsWithCommandMatcher implements CommandMatcher {

    private final String prefix;


    @Override
    public boolean matches(String commandLine) {
        return commandLine.startsWith(prefix);
    }
}
