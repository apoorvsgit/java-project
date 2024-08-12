import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GenericTaskManager<T extends Task> {
    private List<T> tasks;

    public GenericTaskManager() {
        tasks = new ArrayList<>();
    }

    public void addTask(T task) {
        tasks.add(task);
    }

    public void removeTask(T task) {
        tasks.remove(task);
    }

    public List<T> getTasks() {
        return tasks;
    }

    public void markTaskComplete(T task) {
        task.setComplete(true);
    }

    public void markTaskIncomplete(T task) {
        task.setComplete(false);
    }

    public void sortTasksByPriority() {
        tasks.sort(Comparator.comparing(T::getPriority));
    }

    public void printTasks() {
        for (T task : tasks) {
            System.out.println(task);
        }
    }

    public void saveTasksToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadTasksFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("File not found. Starting with an empty task list.");
            tasks = new ArrayList<>();
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            tasks = (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
