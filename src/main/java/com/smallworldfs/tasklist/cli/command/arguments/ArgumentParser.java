package com.smallworldfs.tasklist.cli.command.arguments;

public interface ArgumentParser<T> {

    T parse(String commandLine);
}
