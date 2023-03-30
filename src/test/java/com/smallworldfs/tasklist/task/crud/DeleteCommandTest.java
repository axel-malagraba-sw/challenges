package com.smallworldfs.tasklist.task.crud;

import static com.smallworldfs.tasklist.cli.io.TestOutput.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.smallworldfs.tasklist.cli.io.Arguments;
import com.smallworldfs.tasklist.cli.io.TestOutput;
import com.smallworldfs.tasklist.project.Project;
import com.smallworldfs.tasklist.project.ProjectRegistry;
import com.smallworldfs.tasklist.task.ProjectRegistryExtension;
import com.smallworldfs.tasklist.task.Task;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ProjectRegistryExtension.class)
class DeleteCommandTest {

    private final DeleteCommand command = new DeleteCommand();
    private final TestOutput output = new TestOutput();
    private final ProjectRegistry registry = ProjectRegistry.getInstance();

    @Test
    void should_return_delete_as_name() {
        assertEquals("delete", command.name());
    }

    @Test
    void should_output_error_message_when_task_does_not_exist() {
        command.run(new Arguments("1"), output);

        assertThat(output).is("Could not find a task with an ID of 1.");
    }

    @Test
    void should_remove_task_from_project(Task task) {
        command.run(new Arguments(task.getId()), output);

        Optional<Project> project = registry.find("test");
        assertTrue(project.isPresent());
        assertFalse(project.get().getTasks().contains(task));
    }
}
