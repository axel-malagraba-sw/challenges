package com.smallworldfs.tasklist.cli;

import static com.smallworldfs.tasklist.cli.io.TestOutput.assertThat;

import com.smallworldfs.tasklist.cli.io.Arguments;
import com.smallworldfs.tasklist.cli.io.TestOutput;
import org.junit.jupiter.api.Test;

class HelpCommandTest {

    private final HelpCommand command = new HelpCommand();
    private final TestOutput output = new TestOutput();

    @Test
    void should_start_with_commands_header() {
        command.run(Arguments.empty(), output);

        assertThat(output).startsWithLine("Commands:");
    }

    @Test
    void should_return_show_command() {
        command.run(Arguments.empty(), output);

        assertThat(output).contains("  show");
    }

    @Test
    public void should_return_add_project_command() {
        command.run(Arguments.empty(), output);

        assertThat(output).contains("  add project <project name>");
    }

    @Test
    public void should_return_add_task_command() {
        command.run(Arguments.empty(), output);

        assertThat(output).contains("  add task <project name> <task description>");
    }

    @Test
    public void should_return_check_command() {
        command.run(Arguments.empty(), output);

        assertThat(output).contains("  check <task ID>");
    }

    @Test
    public void should_return_uncheck_command() {
        command.run(Arguments.empty(), output);

        assertThat(output).contains("  uncheck <task ID>");
    }

    @Test
    public void should_return_deadline_command() {
        command.run(Arguments.empty(), output);

        assertThat(output).contains("  deadline <task ID> <date yyyy-mm-dd>");
    }

    @Test
    public void should_return_today_command() {
        command.run(Arguments.empty(), output);

        assertThat(output).contains("  today");
    }

    @Test
    public void should_return_rename_command() {
        command.run(Arguments.empty(), output);

        assertThat(output).contains("  rename <task ID> <new task ID>");
    }

    @Test
    public void should_return_delete_command() {
        command.run(Arguments.empty(), output);

        assertThat(output).contains("  delete <task ID>");
    }
}
