package com.smallworldfs.tasklist.task.completion;

import static com.smallworldfs.tasklist.cli.io.TestOutput.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.smallworldfs.tasklist.cli.io.Arguments;
import com.smallworldfs.tasklist.cli.io.TestOutput;
import com.smallworldfs.tasklist.task.ProjectRegistryExtension;
import com.smallworldfs.tasklist.task.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ProjectRegistryExtension.class)
public class UncheckCommandTest {

    private final UncheckCommand command = new UncheckCommand();
    private final TestOutput output = new TestOutput();


    @Test
    void should_return_no_task_found_message_when_referenced_task_does_not_exist() {
        command.run(new Arguments("1"), output);

        assertThat(output).is("Could not find a task with an ID of 1.");
    }

    @Test
    void should_mark_task_as_not_done_when_it_exists(Task task) {
        task.check();

        command.run(new Arguments(task.getId()), output);

        assertThat(output).isEmpty();
        assertFalse(task.isDone());
    }
}
