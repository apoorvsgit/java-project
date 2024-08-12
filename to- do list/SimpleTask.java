// import java.io.Serializable;
// import java.time.LocalDate;

// public class SimpleTask implements Task, Serializable {
//     private String description;
//     private boolean complete;
//     private Priority priority;
//     private LocalDate dueDate;

//     public SimpleTask(String description, Priority priority, LocalDate dueDate) {
//         this.description = description;
//         this.complete = false;
//         this.priority = priority;
//         this.dueDate = dueDate;
//     }

//     @Override
//     public String getDescription() {
//         return description;
//     }

//     @Override
//     public void setDescription(String description) {
//         this.description = description;
//     }

//     @Override
//     public boolean isComplete() {
//         return complete;
//     }

//     @Override
//     public void setComplete(boolean complete) {
//         this.complete = complete;
//     }

//     @Override
//     public Priority getPriority() {
//         return priority;
//     }

//     @Override
//     public void setPriority(Priority priority) {
//         this.priority = priority;
//     }

//     @Override
//     public LocalDate getDueDate() {
//         return dueDate;
//     }

//     @Override
//     public void setDueDate(LocalDate dueDate) {
//         this.dueDate = dueDate;
//     }

//     @Override
//     public String toString() {
//         return description;
//     }
// }
import java.io.Serializable;
import java.time.LocalDate;

public class SimpleTask implements Task, Serializable {
    private String description;
    private boolean complete;
    private Priority priority;
    private LocalDate dueDate;

    public SimpleTask(String description, Priority priority, LocalDate dueDate) {
        this.description = description;
        this.complete = false;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean isComplete() {
        return complete;
    }

    @Override
    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    @Override
    public Priority getPriority() {
        return priority;
    }

    @Override
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public LocalDate getDueDate() {
        return dueDate;
    }

    @Override
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        String status = complete ? "Complete" : "Incomplete";
        return description + " (" + status + ")";
    }
}
