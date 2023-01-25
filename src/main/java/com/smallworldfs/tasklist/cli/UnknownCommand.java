package com.smallworldfs.tasklist.cli;

import com.smallworldfs.tasklist.cli.command.Command;
import com.smallworldfs.tasklist.cli.io.Arguments;
import com.smallworldfs.tasklist.cli.io.Output;

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
