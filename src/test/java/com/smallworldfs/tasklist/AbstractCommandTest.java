package com.smallworldfs.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.smallworldfs.tasklist.cli.command.Command;
import com.smallworldfs.tasklist.cli.command.CommandLine;
import com.smallworldfs.tasklist.cli.command.exception.InvalidCommandArgumentsException;
import com.smallworldfs.tasklist.cli.io.TestOutput;
import com.smallworldfs.tasklist.project.ProjectRegistry;
import com.smallworldfs.tasklist.task.ProjectRegistryExtension;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.extension.ExtendWith;

@RequiredArgsConstructor
@ExtendWith(ProjectRegistryExtension.class)
public abstract class AbstractCommandTest<T extends Command<?>> {

    protected final ProjectRegistry registry = ProjectRegistry.getInstance();
    protected final TestOutput output = new TestOutput();
    protected final T command;

    @SuppressWarnings("unchecked")
    protected <S> void run(String line) {
        Command<S> command = (Command<S>) this.command;

        command.run(command.getArgumentParser().parse(new CommandLine(line)), output);
    }

    protected void assertThrowsInvalidCommandArgumentException(String line) {
        assertThrows(InvalidCommandArgumentsException.class, () -> run(line));
    }

    protected void assertMatches(String line) {
        assertTrue(command.getMatcher().matches(new CommandLine(line)));
    }

    protected void assertHelpIsEqualTo(String help) {
        assertEquals(help, command.help());
    }
}
