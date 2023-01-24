package com.smallworldfs.tasklist;

import com.smallworldfs.tasklist.command.factory.CommandParser;
import com.smallworldfs.tasklist.io.PrintWriterOutput;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import lombok.SneakyThrows;

public final class TaskList implements Runnable {

    private static final String QUIT = "quit";

    private final CommandParser commandParser = new CommandParser();
    private final BufferedReader in;
    private final PrintWriter out;

    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        new TaskList(in, out).run();
    }

    public TaskList(BufferedReader reader, PrintWriter writer) {
        this.in = reader;
        this.out = writer;
    }

    @SneakyThrows
    public void run() {
        while (true) {
            String line = readInput();

            if (line.equals(QUIT)) {
                break;
            }
            runCommand(line);
        }
    }

    private void runCommand(String line) {
        commandParser.parse(line).run(new PrintWriterOutput(out));
    }

    private String readInput() throws IOException {
        out.print("> ");
        out.flush();

        return in.readLine();
    }
}
