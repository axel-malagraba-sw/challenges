package com.smallworldfs.tasklist.cli.command;

import com.smallworldfs.tasklist.cli.io.Arguments;
import com.smallworldfs.tasklist.cli.io.Output;

public record ParsedCommand(Command command, Arguments arguments) {

    public void run(Output output) {
        command.run(arguments, output);
    }
}
