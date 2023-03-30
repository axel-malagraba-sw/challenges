package com.smallworldfs.tasklist.cli.command.match;

import com.smallworldfs.tasklist.cli.command.CommandLine;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StartsWithCommandMatcher implements CommandMatcher {

    private final String prefix;


    @Override
    public boolean matches(CommandLine commandLine) {
        return commandLine.raw().startsWith(prefix);
    }
}
