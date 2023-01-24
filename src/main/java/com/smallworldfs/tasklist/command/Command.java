package com.smallworldfs.tasklist.command;

import com.smallworldfs.tasklist.io.Arguments;
import com.smallworldfs.tasklist.io.Output;

public interface Command {

    void run(Arguments arguments, Output output);

    String name();

    default boolean matches(String command) {
        return name().equals(command);
    }
}
