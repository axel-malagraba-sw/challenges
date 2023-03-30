package com.smallworldfs.tasklist.task.crud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.smallworldfs.tasklist.AbstractCommandTest;
import com.smallworldfs.tasklist.cli.command.exception.InvalidCommandArgumentsException;
import com.smallworldfs.tasklist.project.ProjectNotFoundException;
import com.smallworldfs.tasklist.task.ProjectRegistryExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ProjectRegistryExtension.class)
public class AddTaskCommandTest extends AbstractCommandTest<AddTaskCommand> {

    public AddTaskCommandTest() {
        super(new AddTaskCommand());
    }

    @Test
    void matches_add_task_command_with_all_arguments() {
        assertMatches("add task test some description");
    }

    @Test
    void matches_add_task_command_without_arguments() {
        assertMatches("add task");
    }

    @Test
    void adds_task_to_project() {
        registry.createProject("test");

        run("add task test some description");

        assertEquals("some description", registry.getAll().get(0).getTasks().get(0).getDescription());
    }

    @Test
    void throws_exception_when_referenced_project_does_not_exist() {
        assertThrows(ProjectNotFoundException.class, () -> run("add task asd something"));
    }

    @Test
    void throws_exception_when_no_arguments_are_provided() {
        assertThrows(InvalidCommandArgumentsException.class, () -> run("add task"));
    }

    @Test
    void throws_exception_when_only_one_argument_is_provided() {
        assertThrows(InvalidCommandArgumentsException.class, () -> run("add task test"));
    }
}
