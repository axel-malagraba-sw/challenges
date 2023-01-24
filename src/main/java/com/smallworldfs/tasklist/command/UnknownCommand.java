package com.smallworldfs.tasklist.command;

import com.smallworldfs.tasklist.io.Arguments;
import com.smallworldfs.tasklist.io.Output;

public class UnknownCommand implements Command {

    @Override
    public void run(Arguments arguments, Output output) {
        output.writeFormatted("I don't know what the command \"%s\" is.", arguments.getCommandLine());
    }

    @Override
    public String name() {
        return "";
    }
}
