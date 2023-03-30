package com.smallworldfs.tasklist.task.timeline;

import static com.smallworldfs.tasklist.cli.io.TestOutput.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.smallworldfs.tasklist.cli.io.Arguments;
import com.smallworldfs.tasklist.cli.io.TestOutput;
import com.smallworldfs.tasklist.task.ProjectRegistryExtension;
import com.smallworldfs.tasklist.task.Task;
import com.smallworldfs.tasklist.task.TaskNotFoundException;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ProjectRegistryExtension.class)
public class DeadlineCommandTest {

    private final DeadlineCommand command = new DeadlineCommand();
    private final TestOutput output = new TestOutput();

    @Test
    void should_throw_exception_when_referenced_task_does_not_exist() {
        assertThrows(TaskNotFoundException.class, () -> command.run(new Arguments("1 2025-01-18"), output));
    }

    @Test
    void should_set_specified_deadline_to_task(Task task) {
        command.run(new Arguments(task.getId() + " 2025-01-18"), output);

        assertThat(output).isEmpty();
        assertEquals(LocalDate.of(2025, 1, 18), task.getDeadline());
    }

    @Test
    void should_change_deadline_if_task_already_has_deadline(Task task) {
        task.setDeadline(LocalDate.of(2023, 5, 28));

        command.run(new Arguments(task.getId() + " 2025-11-13"), output);

        assertThat(output).isEmpty();
        assertEquals(LocalDate.of(2025, 11, 13), task.getDeadline());
    }
}
