package com.smallworldfs.tasklist.cli.command;

import com.smallworldfs.tasklist.cli.io.Output;

public record ParsedCommand<T> (Command<T> command, T arguments) {

    public void run(Output output) {
        command.run(arguments, output);
    }
}
