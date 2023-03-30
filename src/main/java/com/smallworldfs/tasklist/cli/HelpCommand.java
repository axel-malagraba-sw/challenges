package com.smallworldfs.tasklist.cli;

import com.smallworldfs.tasklist.cli.command.Command;
import com.smallworldfs.tasklist.cli.command.NoArgumentsCommand;
import com.smallworldfs.tasklist.cli.command.match.CommandMatcher;
import com.smallworldfs.tasklist.cli.command.match.IsEqualToCommandMatcher;
import com.smallworldfs.tasklist.cli.io.Output;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HelpCommand extends NoArgumentsCommand {

    @Getter
    private final CommandMatcher matcher = new IsEqualToCommandMatcher("help");
    private final Supplier<Stream<Command<?>>> commands;

    @Override
    public void run(Output output) {
        output.writeln("Commands:");
        writeCommands(output);
        output.newLine();
    }

    private void writeCommands(Output output) {
        commands.get()
                .map(Command::help)
                .filter(Objects::nonNull)
                .map(help -> "  " + help)
                .forEach(output::writeln);
    }
}
