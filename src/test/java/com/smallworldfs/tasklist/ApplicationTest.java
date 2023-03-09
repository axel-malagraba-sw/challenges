package com.smallworldfs.tasklist;

import static java.lang.System.lineSeparator;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.smallworldfs.tasklist.task.ProjectRegistryExtension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ProjectRegistryExtension.class)
public final class ApplicationTest {

    public static final String PROMPT = "> ";
    private final PipedOutputStream inStream = new PipedOutputStream();
    private final PrintWriter inWriter = new PrintWriter(inStream, true);

    private final PipedInputStream outStream = new PipedInputStream();
    private final BufferedReader outReader = new BufferedReader(new InputStreamReader(outStream));

    private Thread applicationThread;

    public ApplicationTest() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new PipedInputStream(inStream)));
        PrintWriter out = new PrintWriter(new PipedOutputStream(outStream), true);
        TaskList taskList = new TaskList(in, out);
        applicationThread = new Thread(taskList);
    }

    @BeforeEach
    public void start_the_application() {
        applicationThread.start();
    }

    @AfterEach
    public void kill_the_application() throws IOException, InterruptedException {
        execute("quit");

        if (!stillRunning()) {
            return;
        }

        Thread.sleep(1000);
        if (!stillRunning()) {
            return;
        }

        applicationThread.interrupt();
        throw new IllegalStateException("The application is still running.");
    }

    @Test
    @Timeout(value = 1)
    public void it_works() throws IOException {
        execute("show");

        execute("add project secrets");
        execute("add task secrets Eat more donuts.");
        execute("add task secrets Destroy all humans.");

        execute("show");
        readLines(
                "secrets",
                "    [ ] 1: Eat more donuts.",
                "    [ ] 2: Destroy all humans.",
                "");

        execute("add project training");
        execute("add task training Four Elements of Simple Design");
        execute("add task training SOLID");
        execute("add task training Coupling and Cohesion");
        execute("add task training Primitive Obsession");
        execute("add task training Outside-In TDD");
        execute("add task training Interaction-Driven Design");

        execute("check 1");
        execute("check 3");
        execute("check 5");
        execute("check 6");

        execute("show");
        readLines(
                "secrets",
                "    [x] 1: Eat more donuts.",
                "    [ ] 2: Destroy all humans.",
                "",
                "training",
                "    [x] 3: Four Elements of Simple Design",
                "    [ ] 4: SOLID",
                "    [x] 5: Coupling and Cohesion",
                "    [x] 6: Primitive Obsession",
                "    [ ] 7: Outside-In TDD",
                "    [ ] 8: Interaction-Driven Design",
                "");
    }

    @Test
    @Timeout(value = 1)
    public void should_return_default_message_for_unknown_command() throws IOException {
        execute("dance for me");
        readLines("I don't know what the command \"dance for me\" is.");
    }

    @Test
    @Timeout(value = 1)
    public void today_command_returns_default_message_when_no_tasks_with_today_deadline() throws IOException {
        execute("today");
        readLines("There are no tasks due today.");
    }

    @Test
    public void when_deadline_is_added_to_task_with_today_date_it_is_returned_in_today_command() throws IOException {
        execute("add project training");
        execute("add task training Task 1");
        execute("add task training Task 2");
        execute("deadline 1 " + LocalDate.now());

        execute("today");
        readLines("training",
                "    [ ] 1: Task 1 (Due " + LocalDate.now() + ")",
                "");
    }

    @Test
    public void when_task_is_renamed_should_change_task_id() throws IOException {
        execute("add project training");
        execute("add task training Fantasy league task");
        execute("rename 1 FantasyLeague");
        execute("check FantasyLeague");

        execute("show");
        readLines("training",
                "    [x] FantasyLeague: Fantasy league task",
                "");
    }

    @Test
    public void when_task_is_deleted_it_is_not_shown() throws IOException {
        execute("add project training");
        execute("add task training Task 1");
        execute("add task training Task 2");
        execute("show");
        readLines("training",
                "    [ ] 1: Task 1",
                "    [ ] 2: Task 2",
                "");

        execute("delete 1");

        execute("show");
        readLines("training",
                "    [ ] 2: Task 2",
                "");
    }

    @Test
    public void help_prints_available_commands() throws IOException {
        execute("help");

        readLines("Commands:",
                "  show",
                "  add project <project name>",
                "  add task <project name> <task description>",
                "  check <task ID>",
                "  uncheck <task ID>",
                "  deadline <task ID> <date yyyy-mm-dd>",
                "  today",
                "  rename <task ID> <new task ID>",
                "  delete <task ID>",
                "");
    }

    private void execute(String command) throws IOException {
        read(PROMPT);
        write(command);
    }

    private void read(String expectedOutput) throws IOException {
        int length = expectedOutput.length();
        char[] buffer = new char[length];
        outReader.read(buffer, 0, length);
        String actualValue = String.valueOf(buffer);

        assertEquals(expectedOutput, actualValue);
    }

    private void readLines(String... expectedOutput) throws IOException {
        for (String line : expectedOutput) {
            read(line + lineSeparator());
        }
    }

    private void write(String input) {
        inWriter.println(input);
    }

    private boolean stillRunning() {
        return applicationThread != null && applicationThread.isAlive();
    }
}
