package com.smallworldfs.tasklist.task.completion;

import static com.smallworldfs.tasklist.cli.io.TestOutput.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.smallworldfs.tasklist.AbstractCommandTest;
import com.smallworldfs.tasklist.task.Task;
import com.smallworldfs.tasklist.task.TaskNotFoundException;
import org.junit.jupiter.api.Test;

public class UncheckCommandTest extends AbstractCommandTest<UncheckCommand> {

    public UncheckCommandTest() {
        super(new UncheckCommand());
    }

    @Test
    void matches_command_without_arguments() {
        assertMatches("uncheck");
    }

    @Test
    void matches_command_with_argument() {
        assertMatches("uncheck 1");
    }

    @Test
    void throws_exception_when_referenced_task_does_not_exist() {
        assertThrows(TaskNotFoundException.class, () -> run("uncheck 1"));
    }

    @Test
    void should_mark_task_as_done_when_it_exists(Task task) {
        task.check();

        run("uncheck " + task.getId());

        assertThat(output).isEmpty();
        assertFalse(task.isDone());
    }

    @Test
    void throws_exception_when_no_arguments_are_provided() {
        assertThrowsInvalidCommandArgumentException("uncheck");
    }

    @Test
    void help_returns_example() {
        assertHelpIsEqualTo("uncheck <task ID>");
    }
}
