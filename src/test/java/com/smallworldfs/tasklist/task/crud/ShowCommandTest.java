package com.smallworldfs.tasklist.task.crud;

import static com.smallworldfs.tasklist.cli.io.TestOutput.assertThat;

import com.smallworldfs.tasklist.cli.io.Arguments;
import com.smallworldfs.tasklist.cli.io.TestOutput;
import com.smallworldfs.tasklist.task.ProjectRegistryExtension;
import com.smallworldfs.tasklist.task.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ProjectRegistryExtension.class)
public class ShowCommandTest {

    private final ShowCommand command = new ShowCommand();
    private final TestOutput output = new TestOutput();

    @Test
    void should_not_output_anything_when_no_tasks() {
        command.run(Arguments.empty(), output);

        assertThat(output).isEmpty();
    }

    @Test
    void should_output_project_task_description_and_id(Task task) {
        command.run(Arguments.empty(), output);

        assertThat(output)
                .contains("test")
                .contains(task.getDescription())
                .contains(task.getId());
    }

    @Test
    void should_output_checked_checkbox_when_task_is_done(Task task) {
        task.check();

        command.run(Arguments.empty(), output);

        assertThat(output).contains("[x]");
    }

    @Test
    void should_output_unchecked_checkbox_when_task_is_not_done(Task task) {
        task.uncheck();

        command.run(Arguments.empty(), output);

        assertThat(output).contains("[ ]");
    }
}
