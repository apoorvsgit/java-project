import java.time.LocalDate;

public interface Task {
    String getDescription();
    void setDescription(String description);
    boolean isComplete();
    void setComplete(boolean complete);
    Priority getPriority();
    void setPriority(Priority priority);
    LocalDate getDueDate();
    void setDueDate(LocalDate dueDate);
}
