package com.smallworldfs.tasklist.command.factory;

import com.smallworldfs.tasklist.command.Command;
import com.smallworldfs.tasklist.io.Arguments;
import com.smallworldfs.tasklist.io.Output;

public record ParsedCommand(Command command, Arguments arguments) {

    public void run(Output output) {
        command.run(arguments, output);
    }
}
