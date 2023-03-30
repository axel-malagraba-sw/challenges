package com.smallworldfs.tasklist.cli.command;

import com.smallworldfs.tasklist.cli.io.Arguments;
import com.smallworldfs.tasklist.cli.io.Output;

public interface Command {

    void run(Arguments arguments, Output output);

    String name();

    default boolean matches(String command) {
        return command.startsWith(name());
    }
}
