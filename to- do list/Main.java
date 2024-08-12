import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        GenericTaskManager<SimpleTask> taskManager = new GenericTaskManager<>();

        // Load tasks from file
        taskManager.loadTasksFromFile("tasks.dat");

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Add a task");
            System.out.println("2. Edit a task");
            System.out.println("3. Mark a task as complete");
            System.out.println("4. Mark a task as incomplete");
            System.out.println("5. Sort tasks by priority");
            System.out.println("6. Show tasks");
            System.out.println("7. Edit task priority");
            System.out.println("8. Delete a task");
            System.out.println("9. Save and exit");
            System.out.print("Choose an option: ");

            int option = getIntInput();
            if (option == -1) continue;

            switch (option) {
                case 1:
                    addTaskManually(taskManager);
                    break;
                case 2:
                    showTasksSorted(taskManager);
                    editTask(taskManager);
                    break;
                case 3:
                    showTasksSorted(taskManager);
                    markTaskStatus(taskManager, true);
                    break;
                case 4:
                    showTasksSorted(taskManager);
                    markTaskStatus(taskManager, false);
                    break;
                case 5:
                    taskManager.sortTasksByPriority();
                    showTasks(taskManager);
                    break;
                case 6:
                    showTasks(taskManager);
                    break;
                case 7:
                    showTasksSorted(taskManager);
                    editTaskPriority(taskManager);
                    break;
                case 8:
                    showTasksSorted(taskManager);
                    deleteTask(taskManager);
                    break;
                case 9:
                    taskManager.saveTasksToFile("tasks.dat");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addTaskManually(GenericTaskManager<SimpleTask> taskManager) {
        System.out.println("Enter description for the task:");
        String description = scanner.nextLine();

        Priority priority = null;
        while (priority == null) {
            System.out.println("Enter priority (LOW, MEDIUM, HIGH) for the task:");
            String priorityInput = scanner.nextLine().trim().toUpperCase();
            try {
                priority = Priority.valueOf(priorityInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid priority. Please enter LOW, MEDIUM, or HIGH.");
            }
        }

        System.out.println("Enter due date (YYYY-MM-DD) for the task:");
        LocalDate dueDate = null;
        while (dueDate == null) {
            String dueDateInput = scanner.nextLine().trim();
            try {
                dueDate = LocalDate.parse(dueDateInput);
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter date in YYYY-MM-DD format.");
            }
        }

        Boolean isComplete = null;
        while (isComplete == null) {
            System.out.println("Is the task complete? (true/false):");
            String isCompleteInput = scanner.nextLine().trim().toLowerCase();
            if (isCompleteInput.equals("true") || isCompleteInput.equals("false")) {
                isComplete = Boolean.parseBoolean(isCompleteInput);
            } else {
                System.out.println("Invalid input. Please enter true or false.");
            }
        }

        SimpleTask task = new SimpleTask(description, priority, dueDate);
        task.setComplete(isComplete);
        taskManager.addTask(task);
    }

    private static void editTask(GenericTaskManager<SimpleTask> taskManager) {
        System.out.println("Enter the number of the task you want to edit:");
        int taskIndex = getIntInput();
        if (taskIndex == -1 || taskIndex < 1 || taskIndex > taskManager.getTasks().size()) {
            System.out.println("Invalid task number.");
            return;
        }

        SimpleTask task = taskManager.getTasks().get(taskIndex - 1);

        System.out.println("Enter new description for the task (leave blank to keep current):");
        String description = scanner.nextLine();
        if (!description.isBlank()) {
            task.setDescription(description);
        }

        Priority priority = null;
        while (priority == null) {
            System.out.println("Enter new priority (LOW, MEDIUM, HIGH) for the task (leave blank to keep current):");
            String priorityInput = scanner.nextLine().trim().toUpperCase();
            if (priorityInput.isBlank()) break;
            try {
                priority = Priority.valueOf(priorityInput);
                task.setPriority(priority);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid priority. Please enter LOW, MEDIUM, or HIGH.");
            }
        }

        System.out.println("Enter new due date (YYYY-MM-DD) for the task (leave blank to keep current):");
        LocalDate dueDate = null;
        while (dueDate == null) {
            String dueDateInput = scanner.nextLine().trim();
            if (dueDateInput.isBlank()) break;
            try {
                dueDate = LocalDate.parse(dueDateInput);
                task.setDueDate(dueDate);
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter date in YYYY-MM-DD format.");
            }
        }

        Boolean isComplete = null;
        while (isComplete == null) {
            System.out.println("Is the task complete? (true/false, leave blank to keep current):");
            String isCompleteInput = scanner.nextLine().trim().toLowerCase();
            if (isCompleteInput.isBlank()) break;
            if (isCompleteInput.equals("true") || isCompleteInput.equals("false")) {
                isComplete = Boolean.parseBoolean(isCompleteInput);
                task.setComplete(isComplete);
            } else {
                System.out.println("Invalid input. Please enter true or false.");
            }
        }
    }

    private static void markTaskStatus(GenericTaskManager<SimpleTask> taskManager, boolean status) {
        System.out.println("Enter the number of the task you want to mark as " + (status ? "complete" : "incomplete") + ":");
        int taskIndex = getIntInput();
        if (taskIndex == -1 || taskIndex < 1 || taskIndex > taskManager.getTasks().size()) {
            System.out.println("Invalid task number.");
            return;
        }
        taskManager.getTasks().get(taskIndex - 1).setComplete(status);
    }

    private static void showTasks(GenericTaskManager<SimpleTask> taskManager) {
        System.out.println("\nAll Tasks:");
        int index = 1;
        for (SimpleTask task : taskManager.getTasks()) {
            String status = task.isComplete() ? "Complete" : "Incomplete";
            System.out.println(index + ". " + task.getDescription() + " (" + status + ")");
            index++;
        }
    }

    private static void showTasksSorted(GenericTaskManager<SimpleTask> taskManager) {
        taskManager.sortTasksByPriority();
        showTasks(taskManager);
    }

    private static void editTaskPriority(GenericTaskManager<SimpleTask> taskManager) {
        System.out.println("Enter the number of the task you want to edit the priority for:");
        int taskIndex = getIntInput();
        if (taskIndex == -1 || taskIndex < 1 || taskIndex > taskManager.getTasks().size()) {
            System.out.println("Invalid task number.");
            return;
        }

        SimpleTask task = taskManager.getTasks().get(taskIndex - 1);

        Priority priority = null;
        while (priority == null) {
            System.out.println("Enter new priority (LOW, MEDIUM, HIGH) for the task:");
            String priorityInput = scanner.nextLine().trim().toUpperCase();
            try {
                priority = Priority.valueOf(priorityInput);
                task.setPriority(priority);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid priority. Please enter LOW, MEDIUM, or HIGH.");
            }
        }
    }

    private static void deleteTask(GenericTaskManager<SimpleTask> taskManager) {
        System.out.println("Enter the number of the task you want to delete:");
        int taskIndex = getIntInput();
        if (taskIndex == -1 || taskIndex < 1 || taskIndex > taskManager.getTasks().size()) {
            System.out.println("Invalid task number.");
            return;
        }
        taskManager.removeTask(taskManager.getTasks().get(taskIndex - 1));
    }

    private static int getIntInput() {
        try {
            int input = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over
            return input;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Consume the invalid input
            return -1;
        }
    }
}
