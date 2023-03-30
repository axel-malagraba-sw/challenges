package com.smallworldfs.tasklist.cli.command.match;

import com.smallworldfs.tasklist.cli.command.CommandLine;

public interface CommandMatcher {

    boolean matches(CommandLine commandLine);
}
