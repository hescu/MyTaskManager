package task_manager_app.model;

import java.time.LocalDate;
import java.util.Date;

public class Task {
    enum Status {
        PENDING,
        IN_PROGRESS,
        COMPLETED
    }
    private int taskId;
    private String title;
    private String description;
    private String creationDate;
    private LocalDate dueDate;
    private int priority;
    private Status status;

    public Task() {
        this.status = Status.PENDING;
    }

    public Task(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = Status.PENDING;
    }

    public Task(String title, String description, String creationDate, String dueDate, int priority, String status) {
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.dueDate = LocalDate.parse(dueDate);
        this.priority = priority;
        this.status = Status.valueOf(status);
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = mapStatus(status);
    }

    @Override
    public String toString() {
        return  taskId + ") " +
                "Title: '" + title + '\'' +
                ", Description: '" + description + '\'' +
                ", CreationDate: '" + creationDate + '\'' +
                ", Due on: '" + dueDate + '\'' +
                ", Priority: " + priority +
                ", Status: " + status;
    }

    public static Status mapStatus(String statusString) {
        switch (statusString.toUpperCase()) {
            case "PENDING":
                return Status.PENDING;
            case "IN_PROGRESS":
                return Status.IN_PROGRESS;
            case "COMPLETED":
                return Status.COMPLETED;
            default:
                throw new IllegalArgumentException("Ungültiger Status-String: " + statusString);
        }
    }

    public String getStatusAsString() {
        return this.status.toString();
    }
}
