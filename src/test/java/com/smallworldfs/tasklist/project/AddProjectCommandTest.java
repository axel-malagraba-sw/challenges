package com.smallworldfs.tasklist.project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.smallworldfs.tasklist.cli.io.Arguments;
import com.smallworldfs.tasklist.cli.io.TestOutput;
import com.smallworldfs.tasklist.project.crud.AddProjectCommand;
import com.smallworldfs.tasklist.task.ProjectRegistryExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ProjectRegistryExtension.class)
public class AddProjectCommandTest {

    private final AddProjectCommand command = new AddProjectCommand();
    private final TestOutput output = new TestOutput();
    private final ProjectRegistry registry = ProjectRegistry.getInstance();

    @Test
    void should_create_project_in_registry_when_add_project_is_invoked() {
        command.run(new Arguments("project some_name", "add project some_name"), output);

        assertEquals("some_name", registry.getAll().get(0).getName());
    }
}
