package com.smallworldfs.tasklist.project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.smallworldfs.tasklist.AbstractCommandTest;
import com.smallworldfs.tasklist.project.crud.AddProjectCommand;
import org.junit.jupiter.api.Test;

public class AddProjectCommandTest extends AbstractCommandTest<AddProjectCommand> {

    public AddProjectCommandTest() {
        super(new AddProjectCommand());
    }

    @Test
    void matches_command_without_arguments() {
        assertMatches("add project");
    }

    @Test
    void matches_command_with_arguments() {
        assertMatches("add project potato");
    }

    @Test
    void creates_project_in_registry() {
        run("add project some_name");

        assertEquals("some_name", registry.getAll().get(0).getName());
    }

    @Test
    void throws_exception_when_argument_is_missing() {
        assertThrowsInvalidCommandArgumentException("add project");
    }

    @Test
    void help_returns_example() {
        assertHelpIsEqualTo("add project <project name>");
    }
}
