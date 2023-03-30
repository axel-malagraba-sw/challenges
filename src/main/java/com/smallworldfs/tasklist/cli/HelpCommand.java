package com.smallworldfs.tasklist.cli;

import com.smallworldfs.tasklist.cli.command.Command;
import com.smallworldfs.tasklist.cli.command.arguments.ArgumentParser;
import com.smallworldfs.tasklist.cli.command.arguments.DefaultArgumentsParser;
import com.smallworldfs.tasklist.cli.command.match.CommandMatcher;
import com.smallworldfs.tasklist.cli.command.match.StartsWithCommandMatcher;
import com.smallworldfs.tasklist.cli.io.Arguments;
import com.smallworldfs.tasklist.cli.io.Output;
import lombok.Getter;

public class HelpCommand implements Command<Arguments> {

    @Getter
    private final ArgumentParser<Arguments> argumentParser = new DefaultArgumentsParser();
    @Getter
    private final CommandMatcher matcher = new StartsWithCommandMatcher("help");

    @Override
    public void run(Arguments arguments, Output output) {
        output.writeln("Commands:");
        output.writeln("  show");
        output.writeln("  add project <project name>");
        output.writeln("  add task <project name> <task description>");
        output.writeln("  check <task ID>");
        output.writeln("  uncheck <task ID>");
        output.writeln("  deadline <task ID> <date yyyy-mm-dd>");
        output.writeln("  today");
        output.writeln("  rename <task ID> <new task ID>");
        output.writeln("  delete <task ID>");
        output.newLine();
    }
}
