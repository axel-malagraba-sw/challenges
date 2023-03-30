package com.smallworldfs.tasklist.task.completion;

import static com.smallworldfs.tasklist.cli.io.TestOutput.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.smallworldfs.tasklist.AbstractCommandTest;
import com.smallworldfs.tasklist.task.Task;
import com.smallworldfs.tasklist.task.TaskNotFoundException;
import org.junit.jupiter.api.Test;

public class CheckCommandTest extends AbstractCommandTest<CheckCommand> {

    public CheckCommandTest() {
        super(new CheckCommand());
    }

    @Test
    void matches_command_without_arguments() {
        assertMatches("check");
    }

    @Test
    void matches_command_with_argument() {
        assertMatches("check 1");
    }

    @Test
    void throws_exception_when_referenced_task_does_not_exist() {
        assertThrows(TaskNotFoundException.class, () -> run("check 1"));
    }

    @Test
    void flags_task_as_done(Task task) {
        task.uncheck();

        run("check " + task.getId());

        assertThat(output).isEmpty();
        assertTrue(task.isDone());
    }

    @Test
    void throws_exception_when_no_arguments_are_provided() {
        assertThrowsInvalidCommandArgumentException("check");
    }

    @Test
    void help_returns_example() {
        assertHelpIsEqualTo("check <task ID>");
    }
}
