package com.smallworldfs.tasklist.task;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
public final class Task {

    private final long id;
    private final String description;
    private boolean done;
    @Setter
    private LocalDate deadline;

    public Task(long id, String description) {
        this.id = id;
        this.description = description;
    }

    public boolean isDueToday() {
        return LocalDate.now().equals(deadline);
    }

    public void check() {
        done = true;
    }

    public void uncheck() {
        done = false;
    }

    public boolean hasDeadline() {
        return deadline != null;
    }
}
