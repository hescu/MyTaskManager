package task_manager_app.model;

public class SubTask extends Task{
    private int sequence;

    public SubTask(int sequence) {
        this.sequence = sequence;
    }

    public SubTask(String title, String description, int priority, int sequence) {
        super(title, description, priority);
        this.sequence = sequence;
    }

    public SubTask(int userId, String title, String description, String creationDate, String dueDate, int priority, String status, int sequence) {
        super(userId, title, description, creationDate, dueDate, priority, status);
        this.sequence = sequence;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
}
