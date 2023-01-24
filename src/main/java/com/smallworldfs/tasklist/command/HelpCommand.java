package com.smallworldfs.tasklist.command;

import com.smallworldfs.tasklist.io.Arguments;
import com.smallworldfs.tasklist.io.Output;

public class HelpCommand implements Command {

    @Override
    public void run(Arguments arguments, Output output) {
        output.writeln("Commands:");
        output.writeln("  show");
        output.writeln("  add project <project name>");
        output.writeln("  add task <project name> <task description>");
        output.writeln("  check <task ID>");
        output.writeln("  uncheck <task ID>");
        output.newLine();
    }

    @Override
    public String name() {
        return "help";
    }
}
