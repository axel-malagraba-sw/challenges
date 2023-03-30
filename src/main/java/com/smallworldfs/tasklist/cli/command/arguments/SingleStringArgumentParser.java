package com.smallworldfs.tasklist.cli.command.arguments;

import com.smallworldfs.tasklist.cli.command.CommandLine;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SingleStringArgumentParser implements ArgumentParser<String> {

    private final int index;
    private final Supplier<String> example;

    public SingleStringArgumentParser(Supplier<String> example) {
        this(1, example);
    }

    @Override
    public String parse(CommandLine commandLine) {
        return commandLine.getTrailingStringAtIndexForCommand(index, example.get());
    }
}
