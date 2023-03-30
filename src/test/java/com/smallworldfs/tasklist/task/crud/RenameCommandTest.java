package com.smallworldfs.tasklist.task.crud;

import static com.smallworldfs.tasklist.cli.io.TestOutput.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.smallworldfs.tasklist.cli.io.Arguments;
import com.smallworldfs.tasklist.cli.io.TestOutput;
import com.smallworldfs.tasklist.task.ProjectRegistryExtension;
import com.smallworldfs.tasklist.task.Task;
import com.smallworldfs.tasklist.task.TaskNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ProjectRegistryExtension.class)
public class RenameCommandTest {

    private final RenameCommand command = new RenameCommand();
    private final TestOutput output = new TestOutput();

    @Test
    void should_return_no_task_found_message_when_referenced_task_does_not_exist() {
        assertThrows(TaskNotFoundException.class, () -> command.run(new Arguments("1 potato"), output));
    }

    @Test
    void should_return_invalid_id_message_when_new_id_contains_spaces() {
        command.run(new Arguments("1 potato 232"), output);

        assertThat(output)
                .is("New id 'potato 232' is not valid. It must be an alphanumeric string containing at least one character.");
    }

    @Test
    void should_return_invalid_id_message_when_new_id_contains_special_characters() {
        command.run(new Arguments("1 potato$%^"), output);

        assertThat(output)
                .is("New id 'potato$%^' is not valid. It must be an alphanumeric string containing at least one character.");
    }

    @Test
    void should_return_invalid_id_message_when_new_id_is_numeric() {
        command.run(new Arguments("1 55"), output);

        assertThat(output)
                .is("New id '55' is not valid. It must be an alphanumeric string containing at least one character.");
    }

    @Test
    void should_change_id_of_specified_task_when_new_id_is_valid(Task task) {
        command.run(new Arguments(task.getId() + " Potato23"), output);

        assertEquals("Potato23", task.getId());
    }

    @Test
    void should_change_id_of_specified_task_twice_if_requested(Task task) {
        command.run(new Arguments(task.getId() + " Lasagne5"), output);

        assertEquals("Lasagne5", task.getId());

        command.run(new Arguments("Lasagne5 AwesomeLeak"), output);

        assertEquals("AwesomeLeak", task.getId());
    }
}
