import java.io.Serializable;

public class Task implements Serializable {
    private static final long serialVersionUID = 1L;  // version control for serialization
    private String taskName;
    private  String dueDate;
    private int priority;
    private boolean isCompleted;


    public Task(String taskName, String dueDate, int priority){
        this.taskName = taskName;
        this.dueDate = dueDate;
        this.priority = priority;
        this.isCompleted = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void completeTask() {
        isCompleted = true;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskName='" + taskName + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", priority=" + priority +
                ", isCompleted=" + isCompleted +
                '}';
    }
}
