package com.smallworldfs.tasklist;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TaskRegistry {

    @Getter
    private final Map<String, List<Task>> tasks = new LinkedHashMap<>();

    private static final TaskRegistry INSTANCE = new TaskRegistry();

    public static TaskRegistry getInstance() {
        return INSTANCE;
    }
}
