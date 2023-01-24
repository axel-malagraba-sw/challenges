package com.smallworldfs.tasklist;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProjectRegistry {

    private final Map<String, Project> projects = new LinkedHashMap<>();

    private static final ProjectRegistry INSTANCE = new ProjectRegistry();

    public static ProjectRegistry getInstance() {
        return INSTANCE;
    }

    public void clear() {
        projects.clear();
    }

    public List<Project> getAll() {
        return new ArrayList<>(projects.values());
    }

    public List<Project> getProjectsWithTasksDueToday() {
        return projects.values()
                .stream()
                .filter(Project::hasTasksDueToday)
                .collect(Collectors.toList());
    }

    public Optional<Project> find(String name) {
        return Optional.ofNullable(projects.get(name));
    }

    public Optional<Task> findTaskById(String id) {
        return projects.values()
                .stream()
                .map(Project::getTasks)
                .flatMap(Collection::stream)
                .filter(task -> id.equals(String.valueOf(task.getId())))
                .findFirst();
    }

    public void createProject(String name) {
        projects.put(name, new Project(name));
    }
}
