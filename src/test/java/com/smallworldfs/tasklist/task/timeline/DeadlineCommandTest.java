package com.smallworldfs.tasklist.task.timeline;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.smallworldfs.tasklist.AbstractCommandTest;
import com.smallworldfs.tasklist.task.Task;
import com.smallworldfs.tasklist.task.TaskNotFoundException;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class DeadlineCommandTest extends AbstractCommandTest<DeadlineCommand> {

    public DeadlineCommandTest() {
        super(new DeadlineCommand());
    }

    @Test
    void matches_command_without_arguments() {
        assertMatches("deadline");
    }

    @Test
    void matches_command_with_full_arguments() {
        assertMatches("deadline 1 2025-01-18");
    }

    @Test
    void matches_command_with_only_one_argument() {
        assertMatches("deadline 1");
    }

    @Test
    void throws_exception_when_date_is_not_valid() {
        assertThrowsInvalidCommandArgumentException("deadline 1 potato");
    }

    @Test
    void throws_exception_when_only_one_argument_is_provided() {
        assertThrowsInvalidCommandArgumentException("deadline 2");
    }

    @Test
    void throws_exception_when_no_arguments_are_provided() {
        assertThrowsInvalidCommandArgumentException("deadline");
    }

    @Test
    void throws_exception_when_referenced_task_does_not_exist() {
        assertThrows(TaskNotFoundException.class, () -> run("deadline 1 2025-01-18"));
    }

    @Test
    void sets_specified_deadline_to_task(Task task) {
        run("deadline " + task.getId() + " 2025-01-18");

        assertEquals(LocalDate.of(2025, 1, 18), task.getDeadline());
    }

    @Test
    void changes_deadline_if_task_already_has_deadline(Task task) {
        task.setDeadline(LocalDate.of(2023, 5, 28));

        run("deadline " + task.getId() + " 2025-11-13");

        assertEquals(LocalDate.of(2025, 11, 13), task.getDeadline());
    }

    @Test
    void help_returns_example() {
        assertHelpIsEqualTo("deadline <task ID> <date yyyy-mm-dd>");
    }
}
