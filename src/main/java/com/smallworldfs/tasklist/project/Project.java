package com.smallworldfs.tasklist.project;

import com.smallworldfs.tasklist.task.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class Project {

    private final String name;
    private final List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public List<Task> getTasksDueToday() {
        return tasks.stream().filter(Task::isDueToday).collect(Collectors.toList());
    }

    public boolean hasTasksDueToday() {
        return tasks.stream().anyMatch(Task::isDueToday);
    }

    public boolean removeTask(String taskId) {
        return tasks.removeIf(task -> Objects.equals(taskId, task.getId()));
    }
}
