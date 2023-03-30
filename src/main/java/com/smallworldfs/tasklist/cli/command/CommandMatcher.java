package com.smallworldfs.tasklist.cli.command;

public interface CommandMatcher {

    boolean matches(String commandLine);
}
