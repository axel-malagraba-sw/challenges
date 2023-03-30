package com.smallworldfs.tasklist.task.crud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.smallworldfs.tasklist.AbstractCommandTest;
import com.smallworldfs.tasklist.cli.command.exception.InvalidCommandArgumentsException;
import com.smallworldfs.tasklist.task.Task;
import com.smallworldfs.tasklist.task.TaskNotFoundException;
import org.junit.jupiter.api.Test;

public class RenameCommandTest extends AbstractCommandTest<RenameCommand> {

    public RenameCommandTest() {
        super(new RenameCommand());
    }

    @Test
    void matches_command_without_arguments() {
        assertMatches("rename");
    }

    @Test
    void matches_command_with_full_arguments() {
        assertMatches("rename 1 potato");
    }

    @Test
    void matches_command_with__only_one_arguments() {
        assertMatches("rename 1");
    }

    @Test
    void throws_exception_when_referenced_task_does_not_exist() {
        assertThrows(TaskNotFoundException.class, () -> run("rename 1 potato"));
    }

    @Test
    void throws_exception_when_no_new_id_is_provided() {
        assertThrowsInvalidCommandArgumentException("rename 1");
    }

    @Test
    void throws_exception_when_no_arguments_are_provided() {
        assertThrowsInvalidCommandArgumentException("rename");
    }

    @Test
    void throws_exception_when_new_id_contains_space() {
        InvalidCommandArgumentsException exception = assertThrows(InvalidCommandArgumentsException.class,
                () -> run("rename 1 potato 232"));

        assertEquals(
                "New id 'potato 232' is not valid. It must be an alphanumeric string containing at least one character.",
                exception.getMessage());
    }

    @Test
    void throws_exception_when_new_id_contains_special_characters() {
        InvalidCommandArgumentsException exception = assertThrows(InvalidCommandArgumentsException.class,
                () -> run("rename 1 potato$%^"));

        assertEquals(
                "New id 'potato$%^' is not valid. It must be an alphanumeric string containing at least one character.",
                exception.getMessage());
    }

    @Test
    void throws_exception_when_new_id_is_numeric() {
        InvalidCommandArgumentsException exception = assertThrows(InvalidCommandArgumentsException.class,
                () -> run("rename 1 55"));

        assertEquals(
                "New id '55' is not valid. It must be an alphanumeric string containing at least one character.",
                exception.getMessage());
    }

    @Test
    void should_change_id_of_specified_task_when_new_id_is_valid(Task task) {
        run("rename " + task.getId() + " Potato23");

        assertEquals("Potato23", task.getId());
    }

    @Test
    void should_change_id_of_specified_task_twice_if_requested(Task task) {
        run("rename " + task.getId() + " Lasagne5");

        assertEquals("Lasagne5", task.getId());

        run("rename Lasagne5 AwesomeLeak");

        assertEquals("AwesomeLeak", task.getId());
    }

    @Test
    void help_returns_example() {
        assertHelpIsEqualTo("rename <task ID> <new task ID>");
    }
}
