package com.smallworldfs.tasklist.cli.command;

import com.smallworldfs.tasklist.cli.command.match.CommandMatcher;
import com.smallworldfs.tasklist.cli.io.Arguments;
import com.smallworldfs.tasklist.cli.io.Output;

public interface Command {

    void run(Arguments arguments, Output output);

    CommandMatcher getMatcher();
}
