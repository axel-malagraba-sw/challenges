package com.smallworldfs.tasklist.task.crud;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.smallworldfs.tasklist.cli.io.Arguments;
import com.smallworldfs.tasklist.cli.io.TestOutput;
import com.smallworldfs.tasklist.project.Project;
import com.smallworldfs.tasklist.project.ProjectRegistry;
import com.smallworldfs.tasklist.task.ProjectRegistryExtension;
import com.smallworldfs.tasklist.task.Task;
import com.smallworldfs.tasklist.task.TaskNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ProjectRegistryExtension.class)
class DeleteCommandTest {

    private final DeleteCommand command = new DeleteCommand();
    private final TestOutput output = new TestOutput();
    private final ProjectRegistry registry = ProjectRegistry.getInstance();

    @Test
    void should_match_delete_command() {
        assertTrue(command.getMatcher().matches("delete 1"));
    }

    @Test
    void should_output_error_message_when_task_does_not_exist() {
        assertThrows(TaskNotFoundException.class, () -> command.run(new Arguments("1"), output));
    }

    @Test
    void should_remove_task_from_project(Task task) {
        command.run(new Arguments(task.getId()), output);

        Project project = registry.find("test");
        assertFalse(project.getTasks().contains(task));
    }
}
