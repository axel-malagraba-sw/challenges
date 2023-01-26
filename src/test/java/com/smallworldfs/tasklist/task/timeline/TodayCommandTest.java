package com.smallworldfs.tasklist.task.timeline;

import static com.smallworldfs.tasklist.cli.io.TestOutput.assertThat;

import com.smallworldfs.tasklist.cli.io.Arguments;
import com.smallworldfs.tasklist.cli.io.TestOutput;
import com.smallworldfs.tasklist.task.ProjectRegistryExtension;
import com.smallworldfs.tasklist.task.Task;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ProjectRegistryExtension.class)
public class TodayCommandTest {

    private final TodayCommand command = new TodayCommand();
    private final TestOutput output = new TestOutput();

    @Test
    void should_return_default_message_when_no_tasks_due_today() {
        command.run(Arguments.empty(), output);

        assertThat(output).is("There are no tasks due today.");
    }

    @Test
    void should_return_task_due_today(Task task) {
        task.setDeadline(LocalDate.now());

        command.run(Arguments.empty(), output);

        assertThat(output)
                .contains(task.getId())
                .contains(task.getDescription());
    }
}
