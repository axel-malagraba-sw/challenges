package com.smallworldfs.tasklist.task.crud;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.smallworldfs.tasklist.AbstractCommandTest;
import com.smallworldfs.tasklist.project.Project;
import com.smallworldfs.tasklist.task.Task;
import com.smallworldfs.tasklist.task.TaskNotFoundException;
import org.junit.jupiter.api.Test;

class DeleteCommandTest extends AbstractCommandTest<DeleteCommand> {

    public DeleteCommandTest() {
        super(new DeleteCommand());
    }

    @Test
    void matches_delete_with_id() {
        assertMatches("delete 1");
    }

    @Test
    void matches_delete_without_arguments() {
        assertMatches("delete");
    }

    @Test
    void should_output_error_message_when_task_does_not_exist() {
        assertThrows(TaskNotFoundException.class, () -> run("delete 1"));
    }

    @Test
    void should_remove_task_from_project(Task task) {
        run("delete " + task.getId());

        Project project = registry.find("test");
        assertFalse(project.getTasks().contains(task));
    }

    @Test
    void throws_exception_when_no_arguments_are_provided() {
        assertThrowsInvalidCommandArgumentException("delete");
    }

    @Test
    void help_returns_example() {
        assertHelpIsEqualTo("delete <task ID>");
    }
}
