package com.smallworldfs.tasklist.task.crud;

import static com.smallworldfs.tasklist.cli.io.TestOutput.assertThat;

import com.smallworldfs.tasklist.AbstractCommandTest;
import com.smallworldfs.tasklist.task.Task;
import org.junit.jupiter.api.Test;

public class ShowCommandTest extends AbstractCommandTest<ShowCommand> {

    public ShowCommandTest() {
        super(new ShowCommand());
    }

    @Test
    void matches_command() {
        assertMatches("show");
    }

    @Test
    void does_nothing_when_there_are_no_tasks() {
        command.run(output);

        assertThat(output).isEmpty();
    }

    @Test
    void should_output_project_task_description_and_id(Task task) {
        command.run(output);

        assertThat(output)
                .contains("test")
                .contains(task.getDescription())
                .contains(task.getId());
    }

    @Test
    void should_output_checked_checkbox_when_task_is_done(Task task) {
        task.check();

        command.run(output);

        assertThat(output).contains("[x]");
    }

    @Test
    void should_output_unchecked_checkbox_when_task_is_not_done(Task task) {
        task.uncheck();

        command.run(output);

        assertThat(output).contains("[ ]");
    }

    @Test
    void help_returns_example() {
        assertHelpIsEqualTo("show");
    }
}
