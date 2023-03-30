package com.smallworldfs.tasklist.task.crud;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.smallworldfs.tasklist.cli.io.Arguments;
import com.smallworldfs.tasklist.cli.io.TestOutput;
import com.smallworldfs.tasklist.project.ProjectRegistry;
import com.smallworldfs.tasklist.task.ProjectRegistryExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ProjectRegistryExtension.class)
public class AddTaskCommandTest {

    private final AddTaskCommand command = new AddTaskCommand();
    private final TestOutput output = new TestOutput();
    private final ProjectRegistry registry = ProjectRegistry.getInstance();

    @Test
    void should_create_task_in_project_when_add_task_is_invoked() {
        registry.createProject("test");

        command.run(new Arguments(
                "task test some description",
                "add task test some description"), output);

        assertEquals("some description", registry.getAll().get(0).getTasks().get(0).getDescription());
    }
}
