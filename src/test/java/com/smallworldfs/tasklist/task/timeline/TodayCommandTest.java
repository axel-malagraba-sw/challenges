package com.smallworldfs.tasklist.task.timeline;

import static com.smallworldfs.tasklist.cli.io.TestOutput.assertThat;

import com.smallworldfs.tasklist.AbstractCommandTest;
import com.smallworldfs.tasklist.task.Task;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class TodayCommandTest extends AbstractCommandTest<TodayCommand> {

    public TodayCommandTest() {
        super(new TodayCommand());
    }

    @Test
    void matches_command() {
        assertMatches("today");
    }

    @Test
    void should_return_default_message_when_no_tasks_due_today() {
        command.run(output);

        assertThat(output).is("There are no tasks due today.");
    }

    @Test
    void should_return_task_due_today(Task task) {
        task.setDeadline(LocalDate.now());

        command.run(output);

        assertThat(output)
                .contains(task.getId())
                .contains(task.getDescription());
    }

    @Test
    void help_returns_example() {
        assertHelpIsEqualTo("today");
    }
}
