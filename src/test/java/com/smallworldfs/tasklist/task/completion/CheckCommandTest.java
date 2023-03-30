package com.smallworldfs.tasklist.task.completion;

import static com.smallworldfs.tasklist.cli.io.TestOutput.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.smallworldfs.tasklist.cli.io.Arguments;
import com.smallworldfs.tasklist.cli.io.TestOutput;
import com.smallworldfs.tasklist.task.ProjectRegistryExtension;
import com.smallworldfs.tasklist.task.Task;
import com.smallworldfs.tasklist.task.TaskNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ProjectRegistryExtension.class)
public class CheckCommandTest {

    private final CheckCommand command = new CheckCommand();
    private final TestOutput output = new TestOutput();


    @Test
    void should_return_no_task_found_message_when_referenced_task_does_not_exist() {
        assertThrows(TaskNotFoundException.class, () -> command.run(new Arguments("1"), output));
    }

    @Test
    void should_mark_task_as_done_when_it_exists(Task task) {
        task.uncheck();

        command.run(new Arguments(task.getId()), output);

        assertThat(output).isEmpty();
        assertTrue(task.isDone());
    }
}
